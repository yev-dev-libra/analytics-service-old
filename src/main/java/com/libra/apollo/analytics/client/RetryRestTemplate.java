package com.libra.apollo.analytics.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * Extension for RestTemplate to support retries transparently.
 */
public class RetryRestTemplate extends RestTemplate {

    private Logger LOG = (Logger) LoggerFactory.getLogger(RetryRestTemplate.class);

    private RetryTemplate retryTemplate;

    @Override
    public <T> T execute(String url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Map<String, ?> urlVariables) throws RestClientException {
        LOG.trace("Injecting execute(String, HttpMethod, RequestCallback, ResponseExtractor, Map) method. Applying retry template.");
        final long start = System.currentTimeMillis();
        T t = retryTemplate.execute(retryContext -> super.execute(url, method, requestCallback, responseExtractor, urlVariables));
        LOG.info("[API]:" + url + " took\t" + (System.currentTimeMillis() - start) + "ms");
        return t;
    }

    @Override
    public <T> T execute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        LOG.trace("Injecting execute(URI, HttpMethod, RequestCallback, ResponseExtractor) method. Applying retry template.");
        final long start = System.currentTimeMillis();
        T t = retryTemplate.execute(retryContext -> super.execute(url, method, requestCallback, responseExtractor));
        LOG.info("[API]:" + url + " took\t" + (System.currentTimeMillis() - start) + "ms");
        return t;
    }

    @Override
    public <T> T execute(String url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor, Object... urlVariables) throws RestClientException {
        LOG.trace("Injecting execute(String, HttpMethod, RequestCallback, ResponseExtractor, Object) method. Applying retry template.");
        final long start = System.currentTimeMillis();
        T t = retryTemplate.execute(retryContext -> super.execute(url, method, requestCallback, responseExtractor, urlVariables));
        LOG.info("[API]:" + url + " took\t" + (System.currentTimeMillis() - start) + "ms");
        return t;
    }

    public void setRetryTemplate(final RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }
}