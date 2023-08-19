package com.argus.alishevspring.Library2Boot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    @GetMapping()
    public String index() {
        return "/index";
    }
}
