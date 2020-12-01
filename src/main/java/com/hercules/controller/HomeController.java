package com.hercules.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping
    public String inex() {
        return "create-case-modal";
    }


    @GetMapping(value="/modal")
    public String index() {
        return "create-case-modal";
    }

}
