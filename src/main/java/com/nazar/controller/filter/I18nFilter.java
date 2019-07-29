package com.nazar.controller.filter;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class I18nFilter implements Filter {
    private final Logger logger = Logger.getLogger(I18nFilter.class);
    private static final String LOCALE = "locale";
    private static final String BUNDLE = "bundle";
    private String bundle;
    private String locale;

    @Override
    public void init(FilterConfig filterConfig) {
        bundle = filterConfig.getInitParameter(BUNDLE);
        locale = filterConfig.getInitParameter(LOCALE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String localeParameter = request.getParameter(LOCALE);
        locale = localeParameter != null
                ? localeParameter
                : httpRequest.getSession().getAttribute(LOCALE) != null
                ? (String) httpRequest.getSession().getAttribute(LOCALE)
                : this.locale;
        logger.debug("Locale is " + locale);
        httpRequest.getSession().setAttribute(LOCALE, locale);
        httpRequest.getSession().setAttribute(BUNDLE, bundle);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
