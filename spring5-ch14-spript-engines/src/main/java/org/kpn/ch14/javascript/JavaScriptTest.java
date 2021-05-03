package org.kpn.ch14.javascript;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptTest {

    private static final Logger logger = LoggerFactory.getLogger(JavaScriptTest.class);

    public static void main(String[] args) {

        logger.info("++");

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
        try{
            jsEngine.eval("print('Hello JavaScript in Java')");
        } catch (ScriptException ex){
            logger.error("JS expression can't be evaluated: {}", ex.getMessage());
        }
    }
}
