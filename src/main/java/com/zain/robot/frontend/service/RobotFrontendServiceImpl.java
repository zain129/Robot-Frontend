package com.zain.robot.frontend.service;

import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import com.zain.robot.frontend.integration.RobotBackendIntegration;
import com.zain.robot.frontend.util.constant.RobotFrontendConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.zain.robot.frontend.util.constant.RobotFrontendConstant.*;

@Service
@Slf4j
@AllArgsConstructor
public class RobotFrontendServiceImpl implements RobotFrontendService {

    private final RobotBackendIntegration robotBackendIntegration;

    public CommandResponseDTO executeCommand(String commandScriptIndexNumber, String rowPosition, String colPosition, String facePosition) throws IOException {
        log.info("Entered executePredefinedCommand method to call the backend API and execute commands");
        String command = RobotFrontendConstant.PRE_DEFINED_COMMANDS.get(Integer.parseInt(commandScriptIndexNumber));
        facePosition = deduceFacePosition(facePosition, command);

        CommandResponseDTO commandResponseDTO = robotBackendIntegration.fetchCommandResponse(
                ROBOT_BACKEND_EXECUTE_COMMAND_URL, command, rowPosition, colPosition, facePosition);

        commandResponseDTO.setFacePosition(facePosition);
        log.info("Response returned from backend API to execute commands: {}", commandResponseDTO);
        return commandResponseDTO;
    }

    private String deduceFacePosition(String facePosition, String command) {
        String[] subCommands = command.split(" ");
        switch (subCommands[0]) {
            case POSITION:
                return FACE_DOWN;
            case TURNAROUND:
                switch (facePosition) {
                    case FACE_LEFT:
                        return FACE_RIGHT;
                    case FACE_RIGHT:
                        return FACE_LEFT;
                    case FACE_UP:
                        return FACE_DOWN;
                    case FACE_DOWN:
                        return FACE_UP;
                }
                break;
            case RIGHT:
                switch (facePosition) {
                    case FACE_LEFT:
                        return FACE_UP;
                    case FACE_RIGHT:
                        return FACE_DOWN;
                    case FACE_UP:
                        return FACE_RIGHT;
                    case FACE_DOWN:
                        return FACE_LEFT;
                }
                break;
        }
        return facePosition;
    }
}
