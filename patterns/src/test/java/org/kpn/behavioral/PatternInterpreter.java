package org.kpn.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

@DisplayName("Interpreter")
public class PatternInterpreter {

    private interface BooleanExp{
        boolean evaluate(Context context);
        BooleanExp replace(String str, BooleanExp exp);
        BooleanExp copy();
    }

    private static class VariableExp implements BooleanExp{

        private final String name;

        public VariableExp(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean evaluate(Context context) {
            return context.lookup(name);
        }

        @Override
        public BooleanExp replace(String str, BooleanExp exp) {
            return this.name.equals(str) ? exp.copy() : copy();
        }

        @Override
        public BooleanExp copy() {
            return new VariableExp(name);
        }
    }

    private static class Context{
        private final Map<String, Boolean> vars = new HashMap<>();

        boolean lookup(String name){
            return vars.get(name) != null && vars.get(name);
        }

        void assign(VariableExp exp, boolean val){
            vars.put(exp.getName(), val);
        }
    }

    private static class Constant implements BooleanExp{

        private final boolean constant;

        public Constant(boolean constant) {
            this.constant = constant;
        }

        @Override
        public boolean evaluate(Context context) {
            return constant;
        }

        @Override
        public BooleanExp replace(String str, BooleanExp exp) {
            return copy();
        }

        @Override
        public BooleanExp copy() {
            return new Constant(constant);
        }
    }

    private static class AndExp implements BooleanExp{

        private final BooleanExp operand1;
        private final BooleanExp operand2;

        public AndExp(BooleanExp operand1, BooleanExp operand2) {
            this.operand1 = operand1;
            this.operand2 = operand2;
        }

        @Override
        public boolean evaluate(Context context) {
            return operand1.evaluate(context) && operand2.evaluate(context);
        }

        @Override
        public BooleanExp replace(String str, BooleanExp exp) {
            return new AndExp(operand1.replace(str, exp), operand2.replace(str, exp));
        }

        @Override
        public BooleanExp copy() {
            return new AndExp(operand1.copy(), operand2.copy());
        }
    }

    private static class OrExp implements BooleanExp{

        private final BooleanExp operand1;
        private final BooleanExp operand2;

        public OrExp(BooleanExp operand1, BooleanExp operand2) {
            this.operand1 = operand1;
            this.operand2 = operand2;
        }

        @Override
        public boolean evaluate(Context context) {
            return operand1.evaluate(context) || operand2.evaluate(context);
        }

        @Override
        public BooleanExp replace(String str, BooleanExp exp) {
            return new OrExp(operand1.replace(str, exp), operand2.replace(str, exp));
        }

        @Override
        public BooleanExp copy() {
            return new OrExp(operand1.copy(), operand2.copy());
        }
    }

    private static class NotExp implements BooleanExp{

        private final BooleanExp operand;

        public NotExp(BooleanExp operand) {
            this.operand = operand;
        }

        @Override
        public boolean evaluate(Context context) {
            return !operand.evaluate(context);
        }

        @Override
        public BooleanExp replace(String str, BooleanExp exp) {
            return new NotExp(operand.replace(str, exp));
        }

        @Override
        public BooleanExp copy() {
            return new NotExp(operand.copy());
        }
    }

    @Test
    void test1(){
        Context context = new Context();
        VariableExp varX = new VariableExp("X");
        VariableExp varY = new VariableExp("Y");
        BooleanExp expression = new OrExp(
                new AndExp(new Constant(true), varX),
                new AndExp(varY, new NotExp(varX))
        );
        context.assign(varX, false);
        context.assign(varY, true);

        boolean result = expression.evaluate(context);
        System.out.println(result);
    }
}
