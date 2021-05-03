package org.kpn.ch14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngineManager;
import javax.swing.*;

public class ListScriptEngines {
//    package com.apress.prospring5.ch14;
//
//import javax.script.ScriptEngineManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//    public class ListScriptEngines {

    private static final Logger logger = LoggerFactory.getLogger(ListScriptEngines.class);

    public static void main(String[] args) {
        ScriptEngineManager mgr = new ScriptEngineManager();
        mgr.getEngineFactories().forEach(factory -> {
            String engineName = factory.getEngineName();
            String languageName = factory.getLanguageName();
            String version = factory.getLanguageVersion();
            logger.info("Engine name: {}, Lang: {}, version: {}", engineName, languageName, version);
        });
    }
//        public static void main(String... args) {
//            ScriptEngineManager mgr = new ScriptEngineManager();
//            mgr.getEngineFactories().forEach(factory -> {
//                String engineName = factory.getEngineName();
//                String languageName = factory.getLanguageName();
//                String version = factory.getLanguageVersion();
//                logger.info("Engine name: " + engineName + " Language: " + languageName +
//                        " Version: " + version);
//            });
//        }
//    }
}
