package com.redhat.gps.routes;

import org.apache.camel.builder.RouteBuilder;

public class TimerRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("timer:testTimer?delay=10000&includeMetadata=true&period=10000")
        .log("numero de ejecución ${exchangeProperty.CamelTimerCounter}")
        .setBody().constant("Body Timer")
        .to("log:timerRoute?showAll=true")
        .log("Mensaje recibido ${body}").
        to("direct:test-route");
    }
    
}
