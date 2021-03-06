package com.javaschool.logistic.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * class for redirect users from login page
 */
@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {



    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        String url;

        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();

        List<String> roles = new ArrayList<>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if (isDriver(roles)) {
            url = "/driver";
        } else if (isAdmin(roles)) {
            url = "/admin";
        } else if (isManager(roles)) {
            url = "/manager_";
        } else {
            url="/access_denied";
        }

        return url;
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    private boolean isManager(List<String> roles) {
        return roles.contains("ROLE_MANAGER");
    }

    private boolean isAdmin(List<String> roles) { return roles.contains("ROLE_ADMIN"); }

    private boolean isDriver(List<String> roles) {
        return roles.contains("ROLE_DRIVER");
    }


}
