package com.zain.robot.frontend.service;

import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import com.zain.robot.frontend.integration.RobotBackendIntegration;
import com.zain.robot.frontend.util.constant.RobotFrontendConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@AllArgsConstructor
public class RobotFrontendServiceImpl implements RobotFrontendService {

    private final RobotBackendIntegration robotBackendIntegration;

    public CommandResponseDTO executeCommand(String command, String rowPosition, String colPosition, String facePosition) throws IOException {
        log.info("Entered executePredefinedCommand method to call the backend API and execute commands");
        CommandResponseDTO commandResponseDTOs = robotBackendIntegration.fetchCommandResponse(
                RobotFrontendConstant.ROBOT_BACKEND_EXECUTE_COMMAND_URL,
                command, rowPosition, colPosition, facePosition);

        log.info("Response returned from backend API to execute commands: {}", commandResponseDTOs);
        return commandResponseDTOs;
    }
}
