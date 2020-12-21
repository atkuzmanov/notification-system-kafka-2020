package com.example.javamvnspringbtblank.webrest;

import com.example.javamvnspringbtblank.service.LoggingService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;

/**
 * A servlet level filter to capture and log all incoming REST requests and outgoing REST responses.
 * It makes use of `com.example.javamvnspringbtblank.service.LoggingService`.
 */
@Component
@Configuration
public class LoggingFilter extends OncePerRequestFilter {

    private final LoggingService logServ = new LoggingService();

    @Bean
    public FilterRegistrationBean<LoggingFilter> initFilter() {
        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoggingFilter());

        // Make sure you sett all dispatcher types if you want the filter to log upon.
        registrationBean.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));

        return registrationBean;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);

        /**
         * The line below is very important!
         * RE: Difficulty in ContentCachingResponseWrapper Produces Empty Response
         * - <https://stackoverflow.com/questions/39935190/contentcachingresponsewrapper-produces-empty-response>
         */
        filterChain.doFilter(requestWrapper, responseWrapper);

        logServ.logCCRequest(requestWrapper);
        logServ.logCCResponse(responseWrapper);

        /**
         * The line below is very important!
         * RE: Difficulty in ContentCachingResponseWrapper Produces Empty Response
         * - <https://stackoverflow.com/questions/39935190/contentcachingresponsewrapper-produces-empty-response>
         */
        responseWrapper.copyBodyToResponse();
    }
}