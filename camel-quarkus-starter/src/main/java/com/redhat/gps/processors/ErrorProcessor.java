package com.redhat.gps.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ErrorProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        throw new UnsupportedOperationException("Metodo Pendiente de Implementación");
    }
    
}
