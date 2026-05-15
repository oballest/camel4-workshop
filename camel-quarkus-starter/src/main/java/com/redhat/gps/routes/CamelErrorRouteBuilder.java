package com.redhat.gps.routes;

import org.apache.camel.builder.RouteBuilder;

import com.redhat.gps.processors.ErrorProcessor;

public class CamelErrorRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {

        from("direct:verificarNumero")
        .log("Mensaje recibido se verifica ${body}")
        .choice()
          .when().simple("${body} !is 'java.lang.Number'")
            .log("Se lanza Excepción el body no es un numero")
            .throwException(new IllegalStateException("Mensaje recibido no corresponde a un numero"))
          .otherwise()
            .log("Mensaje recibido corresponde a un numero");
        
        from("timer:testErrorTimer?period=10000&includeMetadata=true")
        .setHeader("tipoProducir").simple("${random(3)}")
        .choice()
          .when().simple("${header.tipoProducir} == 0")
            .log("Se produce un numero pequeno")
            .setBody().simple("${random(1,11)}")
            .endChoice()
          .when().simple("${header.tipoProducir} == 1")
            .log("Se produce un numero grade")
            .setBody().simple("${random(100,501)}")
            .endChoice()
          .when().simple("${header.tipoProducir} == 2")
            .log("Se produce una cadena")
            .setBody().simple("cadena-${uuid}")
          .end()  
        .to("direct:procesamientoError");

    }
    
}
