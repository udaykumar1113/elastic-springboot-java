package com.uday.indexsearch.rest.controller;

import com.uday.indexsearch.exception.IllegalApiParamException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exception")
public class ExceptionHandleController {

    @RequestMapping(path="/error/{color}/{brand}")
    public String getErrorResponse(@PathVariable String color, @PathVariable String brand){
        if(StringUtils.isNumeric(color) || StringUtils.isNumeric(brand)){
            throw new IllegalApiParamException("Exception from controller advice, numeric color");
        }
        return "success";
    }
}
