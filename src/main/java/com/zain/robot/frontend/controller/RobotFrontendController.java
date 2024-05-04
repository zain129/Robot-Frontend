package com.zain.robot.frontend.controller;

import com.zain.robot.frontend.service.RobotFrontendService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@Slf4j
@AllArgsConstructor
public class RobotFrontendController {

    private final RobotFrontendService robotFrontendService;

    @GetMapping("/")
    public ModelAndView viewHomePage(Model model) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("commands", null);
        modelAndView.addObject("rowPosition", 0);
        modelAndView.addObject("colPosition", 0);

        return modelAndView;
    }


    @PostMapping("/execute-commands")
    public ModelAndView executeCommands(Model model) throws IOException {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("commands", robotFrontendService.executePredefinedCommand());

        return modelAndView;
    }

}
