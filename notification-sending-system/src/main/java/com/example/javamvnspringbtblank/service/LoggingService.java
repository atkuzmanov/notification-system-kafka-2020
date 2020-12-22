package com.example.javamvnspringbtblank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Arrays;

/**
 * An utility service to facilitate logging by encompassing all common logging related logic.
 */
@Component
public class LoggingService {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingService.class);

    public void logCCRequest(ContentCachingRequestWrapper requestWrapper) {
        LOG.info("Request URI: [" + requestWrapper.getRequestURI() + "] Request contents: " + new String(requestWrapper.getContentAsByteArray()));
    }

    public void logCCResponse(ContentCachingResponseWrapper responseWrapper) {
        LOG.info("Response status code: [" + responseWrapper.getStatus() + "] Response contents: " + new String(responseWrapper.getContentAsByteArray()));
    }

    public static boolean isValidJSON(final String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        boolean valid = true;
        try {
            mapper.readTree(json);
        } catch (JsonProcessingException e) {
            valid = false;
        }
        return valid;
    }

    public void logException(Throwable e) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("Exception in class", e.getStackTrace()[1].getClassName());
        rootNode.put("Exception in method", e.getStackTrace()[1].getMethodName());
        rootNode.put("Exception message", e.getMessage());
        rootNode.put("Exception localized message", e.getLocalizedMessage());
        rootNode.put("Exception cause", String.valueOf(e.getCause()));
        if (LOG.isDebugEnabled()) {
            rootNode.put("Exception stacktrace", Arrays.toString(e.getStackTrace()));
        }
        LOG.error("EXCEPTION_OCCURRED:" + rootNode.toPrettyString());
    }
}
