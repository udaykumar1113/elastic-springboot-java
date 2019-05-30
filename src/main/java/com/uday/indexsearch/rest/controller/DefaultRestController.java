package com.uday.indexsearch.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/rest-api")
public class DefaultRestController {

    @GetMapping(path = "/header-one")
    public String headerByAnnotation(@RequestHeader(name="User-Agent") String userAgent){
        return "User-Agent: "+userAgent;
    }

    @GetMapping(path="/header-servlet")
    public String headerByServlet(HttpServletRequest httpServlet){
        return httpServlet.getHeader("User-Agent");
    }

    @GetMapping(path="/header-all-servlet")
    public Map<String, String> headerAllServlet(@RequestHeader HttpHeaders httpHeaders){
        return httpHeaders.toSingleValueMap();
    }
}
