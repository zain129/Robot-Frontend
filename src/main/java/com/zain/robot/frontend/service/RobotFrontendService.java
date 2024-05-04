package com.zain.robot.frontend.service;

import com.zain.robot.frontend.domain.dto.CommandResponseDTO;

import java.io.IOException;

public interface RobotFrontendService {
    CommandResponseDTO executeCommand(String command, String rowPosition, String colPosition, String facePosition) throws IOException;
}
