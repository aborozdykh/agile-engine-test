package me.aborozdykh.agileenginetest.controller;

import me.aborozdykh.agileenginetest.util.AuthUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Andrii Borozdykh
 */
@Controller
@RequestMapping("/")
public class MainController {
    private final AuthUtil authUtil;

    public MainController(AuthUtil authUtil) {
        this.authUtil = authUtil;
    }

    @GetMapping
    public String getBearerToken(@RequestParam("apiKey") String apiKey) {
        return authUtil.getBearerToken(apiKey);
    }
}
