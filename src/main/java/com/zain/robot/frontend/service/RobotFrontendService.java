package com.zain.robot.frontend.service;

import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import com.zain.robot.frontend.integration.RobotBackendIntegration;
import com.zain.robot.frontend.util.constant.RobotFrontendConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RobotFrontendService {

    private final RobotBackendIntegration robotBackendIntegration;

    public List<CommandResponseDTO> executePredefinedCommand() throws IOException {
        log.info("Entered executePredefinedCommand method to call the backend API and execute commands");
        List<CommandResponseDTO> commandResponseDTOs = robotBackendIntegration.fetchCommandResponse(
                RobotFrontendConstant.ROBOT_BACKEND_EXECUTE_COMMAND_URL,
                RobotFrontendConstant.PRE_DEFINED_COMMANDS);
        log.info("Response returned from backend API to execute commands: {}", commandResponseDTOs);
        return commandResponseDTOs;
    }
}
