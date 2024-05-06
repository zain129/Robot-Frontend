package com.zain.robot.frontend.service;

import com.zain.robot.frontend.domain.dto.CommandResponseDTO;

import java.io.IOException;
import java.util.List;

public interface RobotFrontendService {
    CommandResponseDTO executeCommand(String commandScriptIndexNumber, String rowPosition
            , String colPosition, String facePosition) throws IOException;

    List<CommandResponseDTO> executeAllCommand(String commands) throws IOException;
}
