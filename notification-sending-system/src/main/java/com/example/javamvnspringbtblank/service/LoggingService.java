package com.example.javamvnspringbtblank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;
import static net.logstash.logback.argument.StructuredArguments.kv;

@Component
public class LoggingService {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingService.class);

    public void logCCRequest(ContentCachingRequestWrapper requestWrapper) {
        System.out.printf("=== REQUEST%n%s%n=== end request%n",
                new String(requestWrapper.getContentAsByteArray()));

    }

    public void logCCResponse(ContentCachingResponseWrapper responseWrapper) {
        System.out.printf("=== RESPONSE%n%s%n=== end response%n",
                new String(responseWrapper.getContentAsByteArray()));
    }

//    public void logContentCachingResponse(ContentCachingResponseWrapper responseWrapper, String originClass, String originMethod) {
//        ObjectMapper mapper = new ObjectMapper();
//
//        HttpStatus responseStatus = HttpStatus.valueOf(responseWrapper.getStatus());
//        HttpHeaders responseHeaders = new HttpHeaders();
//        for (String headerName : responseWrapper.getHeaderNames()) {
//            responseHeaders.add(headerName, responseWrapper.getHeader(headerName));
//        }
//
//        String responseBody = null;
//        try {
//            responseBody = IOUtils.toString(responseWrapper.getContentInputStream(), UTF_8);
//
//            if (!LOG.isDebugEnabled() && isValidJSON(responseBody)) {
//                JsonNode jNode = mapper.readTree(responseBody);
//                if (jNode.has("message")) {
//                    responseBody = jNode.path("message").asText();
//                }
//            } else {
//                responseBody = mapper.writeValueAsString(responseBody);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        ResponseDetailsBuilder rdb = ResponseDetails.builder()
////                .status(responseStatus.value())
////                .originClass(originClass)
////                .originMethod(originMethod)
////                .headers(responseHeaders)
////                .responseBody(responseBody);
//
////        LOG.info("OUTGOING_RESPONSE", kv("responseDetails", rdb.build()));
//        LOG.info("OUTGOING_RESPONSE" + );
//    }

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
        if (LOG.isDebugEnabled()) {
            rootNode.put("Exception stacktrace", Arrays.toString(e.getStackTrace()));
        }
        LOG.error("EXCEPTION_OCCURRED:" + rootNode.toPrettyString());
    }
}
