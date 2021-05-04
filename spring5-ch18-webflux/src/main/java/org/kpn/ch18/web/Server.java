package org.kpn.ch18.web;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.ipc.netty.http.server.HttpServer;

public class Server {
//package com.apress.prospring5.ch18.web;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.LifecycleException;
//import org.apache.catalina.startup.Tomcat;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.server.reactive.HttpHandler;
//import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
//import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerResponse;

//
//import static org.springframework.web.reactive.function.BodyInserters.fromObject;
//import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
//import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
//import static org.springframework.web.reactive.function.server.RouterFunctions.route;
//import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;
//import static org.springframework.web.reactive.function.server.ServerResponse.ok;
//
//    /**
//     * Created by iuliana.cosmina on 8/6/17.
//     */
//    public class Server {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    @Autowired
    SingerHandler singerHandler;

    public RouterFunction<ServerResponse> routingFunction(){
        return RouterFunctions.route(RequestPredicates.GET("/test"), serverRequest -> ServerResponse.ok().body(BodyInserters.fromObject("works!")))
                .andRoute(RequestPredicates.GET("/singers"), singerHandler.list)
                .andRoute(RequestPredicates.GET("/singers/{id}"), singerHandler::show)
                .andRoute(RequestPredicates.POST("/singers"), singerHandler.save)
                .filter((request, next) -> {
                    logger.info("Before handler invocation: {}", request.path());
                    return next.handle(request);
                });
    }

    public void startReactorServer(){
        RouterFunction<ServerResponse> route = routingFunction();
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);

        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer server = HttpServer.create(HOST, PORT);
        server.newHandler(adapter).block();
    }

    public void startTomcatServer() throws LifecycleException {
        RouterFunction<ServerResponse> route = routingFunction();
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);

        Tomcat tomcatServer = new Tomcat();
        tomcatServer.setHostname(HOST);
        tomcatServer.setPort(PORT);
        Context rootContext = tomcatServer.addContext("", System.getProperty("java.io.tmpdir"));
        ServletHttpHandlerAdapter servlet = new ServletHttpHandlerAdapter(httpHandler);
        Tomcat.addServlet(rootContext, "httpHandlerServlet", servlet);
        rootContext.addServletMappingDecoded("/", "httpHandlerServlet");
        tomcatServer.start();
        logger.info("Embedded Tomcat server started...");
    }
}
