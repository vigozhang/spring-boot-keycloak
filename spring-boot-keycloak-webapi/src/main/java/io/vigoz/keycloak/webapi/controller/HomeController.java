package io.vigoz.keycloak.webapi.controller;

import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/customer", method = {RequestMethod.GET, RequestMethod.POST})
    public String customer(HttpServletRequest request) {
        KeycloakSecurityContext keycloak = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        return "customer :: " + keycloak.getTokenString();
    }

    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
    public String admin(HttpServletRequest request) {
        KeycloakSecurityContext keycloak = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        return "admin :: " + keycloak.getTokenString();
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpServletRequest request) {
        try {
            request.logout();
            return "logout success";
        } catch (ServletException e) {
            LOGGER.error("keycloak logout error", e);
            return "logout fail";
        }

    }
}
