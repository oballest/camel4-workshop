package com.redhat.gps.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.redhat.gps.model.Persona;

public class CamelSplitRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        BindyCsvDataFormat bindy = new BindyCsvDataFormat(Persona.class);

        from("file:../files/")
        .log("Body del mensaje ${body}")
        .split().tokenize("\n")
        .log("nuevo body ${body}")
        .unmarshal(bindy)
        .log("Body en objetos ${body}")
        .to("log:com.redhat.gps.routes.transform")
        .marshal().json(JsonLibrary.Jackson)
        .to("log:com.redhat.gps.routes.transform?showExchangeId=true")
        .wireTap("direct:logArchivos")
        .filter().jsonpath("$.[?(@.ciudad == 'Bogota' || @.ciudad == 'Medellin')]")
        .log("Procesado mensaje filtrado por ciudad  ${body}")
        .choice()
            .when().jsonpath("$.[?(@.ciudad == 'Bogota')]")
              .log("Mensaje Para Bogota")
              .setHeader(Exchange.FILE_NAME).simple("${jsonpath($.nombre)}-${jsonpath($.apellido)}.json")
              .to("file:out/Bogota/")
            .when().jsonpath("$.[?(@.ciudad == 'Medellin')]")
              .log("Mensaje para Medellin")
              .setHeader(Exchange.FILE_NAME).simple("${jsonpath($.nombre)}-${jsonpath($.apellido)}.json")
              .to("file:out/Medellin/")
            .otherwise()
              .log("Mensaje no reconocido");

    }
    
}
