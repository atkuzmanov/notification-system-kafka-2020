package com.example.javamvnspringbtblank.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LoggingService {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingService.class);

    public void logException(Throwable e) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        rootNode.put("Exception in class", e.getStackTrace()[1].getClassName());
        rootNode.put("Exception in method", e.getStackTrace()[1].getMethodName());
        rootNode.put("Exception message", e.getMessage());
        if (LOG.isDebugEnabled()) {
            rootNode.put("Exception stacktrace", Arrays.toString(e.getStackTrace()));
        }
        LOG.error("Exception occurred:" + rootNode.toPrettyString());
    }
}
