package com.zain.robot.frontend.integration;

import com.zain.robot.frontend.domain.dto.CommandRequestDTO;
import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import com.zain.robot.frontend.util.constant.RobotFrontendConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
public class RobotBackendIntegration {

    private final WebClient webClient;

    public RobotBackendIntegration(@Qualifier("robotBackendWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseEntity<CommandResponseDTO> fetchCommandResponse(CommandRequestDTO commandRequestDTO) {
        return webClient.post()
                .uri(RobotFrontendConstant.ROBOT_BACKEND_EXECUTE_COMMAND_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(commandRequestDTO)
                .retrieve()
                .toEntity(CommandResponseDTO.class)
                .block();
    }

    public ResponseEntity<List<CommandResponseDTO>> fetchListOfCommandResponses(List<CommandRequestDTO> commandRequestDTO) {
        return webClient.post()
                .uri(RobotFrontendConstant.ROBOT_BACKEND_EXECUTE_ALL_COMMAND_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(commandRequestDTO)
                .retrieve()
                .toEntityList(CommandResponseDTO.class)
                .block();
    }
}
