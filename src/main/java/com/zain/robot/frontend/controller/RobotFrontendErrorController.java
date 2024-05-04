package com.zain.robot.frontend.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RobotFrontendErrorController implements ErrorController {

    @RequestMapping("/error")
    public String getError() {
        return "errorPage";
    }
}
