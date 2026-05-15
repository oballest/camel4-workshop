package com.redhat.gps.routes;

import org.apache.camel.builder.RouteBuilder;

public class CamelRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        
        /*from("file:../files/?includeExt=csv")
        .to("log:com.redhat.gps.routes.filereader?showAll=true")
        .log("${body}")
        .setBody(simple("${body}\n${header.CamelFileName}"))
        .log("${body}")
        .log("Fecha de modificación ${header.CamelFileLastModified} Ubicación del archivo ${header.CamelFilePath}")
        .setHeader("CamelFileName").simple("${header.CamelFileName}-${header.CamelMessageTimestamp}")
        .log("${header.CamelFileName}")
        .to("file:out/");*/

    }
    
}
