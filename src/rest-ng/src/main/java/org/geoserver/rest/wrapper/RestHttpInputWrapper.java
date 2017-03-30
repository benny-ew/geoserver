package org.geoserver.rest.wrapper;

import org.geoserver.config.util.XStreamPersister;
import org.geoserver.rest.RestBaseController;
import org.geoserver.rest.converters.XStreamMessageConverter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Wrapper around {@link HttpInputMessage} used by {@link XStreamMessageConverter} to configure the persister before
 * XStream objects get read.
 */
public class RestHttpInputWrapper implements HttpInputMessage {

    HttpInputMessage message;
    RestBaseController controller;

    public RestHttpInputWrapper(HttpInputMessage message, RestBaseController controller) {
        this.message = message;
        this.controller = controller;
    }

    /**
     * Apply configuration to the XStreamPersister based on the converter
     *
     * @param persister The XStream persister
     * @param xStreamMessageConverter The XStream converter
     */
    public void configurePersister(XStreamPersister persister, XStreamMessageConverter xStreamMessageConverter) {
        controller.configurePersister(persister, xStreamMessageConverter);
    }

    @Override
    public InputStream getBody() throws IOException {
        return message.getBody();
    }

    @Override
    public HttpHeaders getHeaders() {
        return message.getHeaders();
    }
}