package com.zain.robot.frontend.controller;

import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import com.zain.robot.frontend.service.RobotFrontendService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        modelAndView.addObject("rowPosition", 0);
        modelAndView.addObject("colPosition", 0);
        modelAndView.addObject("commandScriptIndexNumber", -1);

        return modelAndView;
    }

    @PostMapping("/execute-command")
    public ModelAndView executeCommand(
            @ModelAttribute(value = "commandScriptIndexNumber") String commandScriptIndexNumber
            , @ModelAttribute(value = "rowPosition") String rowPosition
            , @ModelAttribute(value = "colPosition") String colPosition
            , @ModelAttribute(value = "facePosition") String facePosition) throws IOException {
        CommandResponseDTO commandResponseDTO = robotFrontendService.executeCommand(commandScriptIndexNumber
                , rowPosition, colPosition, facePosition);

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("command", commandResponseDTO);
        modelAndView.addObject("rowPosition", commandResponseDTO.getNewRowPosition());
        modelAndView.addObject("colPosition", commandResponseDTO.getNewColPosition());
        modelAndView.addObject("facePosition", commandResponseDTO.getFacePosition());
        modelAndView.addObject("commandScriptIndexNumber", commandScriptIndexNumber);

        return modelAndView;
    }

}
