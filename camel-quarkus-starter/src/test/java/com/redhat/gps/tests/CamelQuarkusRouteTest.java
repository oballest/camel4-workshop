package com.redhat.gps.tests;

import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.quarkus.test.CamelQuarkusTestSupport;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CamelQuarkusRouteTest extends CamelQuarkusTestSupport{
    
    @Test
    void testRutaIntegración() throws InterruptedException{
        MockEndpoint mockInicio = getMockEndpoint("mock:ingreso-ruta");
        mockInicio.expectedBodiesReceived("Prueba Unitaria");
        mockInicio.expectedMessageCount(1);

        MockEndpoint mockFin = getMockEndpoint("mock:salida-ruta");
        mockFin.expectedBodiesReceived("Prueba Unitaria-transformacion");
        mockFin.expectedMessageCount(1);

        template.sendBody("direct:ruta-prueba", "Prueba Unitaria");

        mockInicio.assertIsSatisfied();
        mockFin.assertIsSatisfied();
    }

}
