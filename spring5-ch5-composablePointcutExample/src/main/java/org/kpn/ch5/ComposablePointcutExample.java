package org.kpn.ch5;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;
import ork.kpn.ch5.GrammyGuitarist;
import ork.kpn.ch5.Guitar;

import java.lang.reflect.Method;

public class ComposablePointcutExample {
    public static void main(String[] args) {
        GrammyGuitarist target = new GrammyGuitarist();
        ComposablePointcut pc = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());

        System.out.println("Test 1 >>");
        GrammyGuitarist proxy = getProxy(pc, target);
        testInvoke(proxy);
        System.out.println("");

        System.out.println("Text 2 >>");
        pc.union(new TalkMethodMatcher());
        proxy = getProxy(pc, target);
        testInvoke(proxy);
        System.out.println("");

        System.out.println("Test 3 >>");
        pc.intersection(new RestMethodMatcher());
        proxy = getProxy(pc, target);
        testInvoke(proxy);
        System.out.println("");
    }

    private static GrammyGuitarist getProxy(ComposablePointcut pc, GrammyGuitarist target){
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);

        return (GrammyGuitarist) pf.getProxy();
    }

    private static void testInvoke(GrammyGuitarist proxy){
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.talk();
        proxy.rest();
    }

    private static class SingMethodMatcher extends StaticMethodMatcher{
        @Override
        public boolean matches(Method method, Class<?> aClass) {
            return method.getName().startsWith("si");
        }
    }

    private static class TalkMethodMatcher extends StaticMethodMatcher{
        @Override
        public boolean matches(Method method, Class<?> aClass) {
            return "talk".equals(method.getName());
        }
    }

    private static class RestMethodMatcher extends StaticMethodMatcher{
        @Override
        public boolean matches(Method method, Class<?> aClass) {
            return method.getName().endsWith("st");
        }
    }
}
