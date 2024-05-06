package com.zain.robot.frontend.service;

import com.zain.robot.frontend.domain.dto.CommandRequestDTO;
import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import com.zain.robot.frontend.exception.NoResponseFoundException;
import com.zain.robot.frontend.integration.RobotBackendIntegration;
import com.zain.robot.frontend.util.constant.RobotFrontendConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.zain.robot.frontend.util.constant.RobotFrontendConstant.*;

@Service
@Slf4j
@AllArgsConstructor
public class RobotFrontendServiceImpl implements RobotFrontendService {

    private final RobotBackendIntegration robotBackendIntegration;

    public CommandResponseDTO executeCommand(String commandScriptIndexNumber, String rowPosition, String colPosition, String facePosition) {
        log.info("Entered executePredefinedCommand method to call the backend API and execute commands");
        String command = RobotFrontendConstant.PRE_DEFINED_COMMANDS.get(Integer.parseInt(commandScriptIndexNumber));
        facePosition = deduceFacePosition(facePosition, command);

        CommandRequestDTO commandRequestDTO = CommandRequestDTO.builder()
                .stringCommand(command)
                .currentRowPosition(Long.valueOf(rowPosition))
                .currentColPosition(Long.valueOf(colPosition))
                .facePosition(facePosition)
                .build();

        CommandResponseDTO commandResponseDTO;
        ResponseEntity<CommandResponseDTO> commandResponseDTOResponseEntity = robotBackendIntegration.fetchCommandResponse(commandRequestDTO);
        if (commandResponseDTOResponseEntity.hasBody()) {
            commandResponseDTO = commandResponseDTOResponseEntity.getBody();
            assert commandResponseDTO != null;
            commandResponseDTO.setFacePosition(facePosition);
        } else {
            log.error("No or empty response received by Backend");
            throw new NoResponseFoundException("No response found from backend for the provided commands list");
        }

        log.info("Response returned from backend API to execute commands: {}", commandResponseDTO);
        return commandResponseDTO;
    }

    @Override
    public List<CommandResponseDTO> executeAllCommand(String commandString) {
        String[] commands = commandString.split(";");
        List<CommandRequestDTO> commandRequestDTOList = new ArrayList<>();
        for (String command : commands) {
            commandRequestDTOList.add(CommandRequestDTO.builder().stringCommand(command.trim()).build());
        }

        List<CommandResponseDTO> commandResponseDTOList;
        ResponseEntity<List<CommandResponseDTO>> listResponseEntity = robotBackendIntegration.fetchListOfCommandResponses(commandRequestDTOList);
        if (listResponseEntity.hasBody()) {
            commandResponseDTOList = listResponseEntity.getBody();
        } else {
            log.error("No or empty response list received by Backend");
            throw new NoResponseFoundException("No response found from backend for the provided commands list");
        }
        return commandResponseDTOList;

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
