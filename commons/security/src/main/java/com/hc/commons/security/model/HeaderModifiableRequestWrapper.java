package com.hc.commons.security.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * @author a1234
 * @description
 * @create 2025-08-08 11:24
 */
public class HeaderModifiableRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, String> customHeaders = new HashMap<>();

    public HeaderModifiableRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public void putHeader(String name, String value) {
        customHeaders.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        return customHeaders.containsKey(name) ? customHeaders.get(name) : super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        Set<String> names = new HashSet<>(Collections.list(super.getHeaderNames()));
        names.addAll(customHeaders.keySet());
        return Collections.enumeration(names);
    }

}
