package com.zain.robot.frontend.controller;

import com.zain.robot.frontend.service.RobotFrontendService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/robot-frontend")
public class RobotFrontendControllerImpl implements RobotFrontendController {

    private final RobotFrontendService robotFrontendService;

    @Override
    @GetMapping("/")
    public ModelAndView viewHomePage() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("commands", null);
        modelAndView.addObject("rowPosition", 1);
        modelAndView.addObject("colPosition", 1);

        return modelAndView;
    }

    @Override
    @PostMapping("/execute-command")
    public ModelAndView executeCommand(@ModelAttribute(value = "command") String command
            , @ModelAttribute(value = "rowPosition") String rowPosition
            , @ModelAttribute(value = "colPosition") String colPosition
            , @ModelAttribute(value = "facePosition") String facePosition) throws IOException {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("commands", robotFrontendService.executeCommand(command, rowPosition, colPosition, facePosition));

        return modelAndView;
    }

}
