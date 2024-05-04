package com.zain.robot.frontend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RequestMapping("/robot-frontend-default")
public interface RobotFrontendController {

    @GetMapping("/")
    ModelAndView viewHomePage();

    @PostMapping("/execute-command")
    ModelAndView executeCommand(@ModelAttribute(value = "command") String command
            , @ModelAttribute(value = "rowPosition") String rowPosition
            , @ModelAttribute(value = "colPosition") String colPosition
            , @ModelAttribute(value = "facePosition") String facePosition) throws IOException;
}
