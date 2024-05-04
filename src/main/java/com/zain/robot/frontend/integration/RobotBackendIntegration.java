package com.zain.robot.frontend.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zain.robot.frontend.domain.dto.CommandRequestDTO;
import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import com.zain.robot.frontend.exception.NoResponseFoundException;
import com.zain.robot.frontend.util.CommonRestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RobotBackendIntegration {

    private final RestTemplate restTemplate;
    private final String robotBackendRootUrl;

    public RobotBackendIntegration(@Qualifier("genericRestTemplate") RestTemplate restTemplate,
                                   @Value("${robot.backend.root_url}") String robotBackendRootUrl) {
        this.restTemplate = restTemplate;
        this.robotBackendRootUrl = robotBackendRootUrl;
    }

    public CommandResponseDTO fetchCommandResponse(String endpoint, String command, String rowPosition
            , String colPosition, String facePosition) throws JsonProcessingException {
        String uri = robotBackendRootUrl + endpoint;
        log.info("Entered fetchCommandResponse method to call the endpoint: {}", endpoint);

        CommandRequestDTO commandRequestDTO = CommandRequestDTO.builder()
                .stringCommand(command)
                .currentRowPosition(Long.valueOf(rowPosition))
                .currentColPosition(Long.valueOf(colPosition))
                .facePosition(facePosition)
                .build();

        ResponseEntity<CommandResponseDTO> responseEntity = restTemplate.exchange(uri, HttpMethod.POST,
                CommonRestUtil.buildHttpEntityRequest(commandRequestDTO, MediaType.APPLICATION_JSON), CommandResponseDTO.class);

        if (responseEntity.hasBody()) {
            log.info("Endpoint {} returned response {}", endpoint, responseEntity);
            return responseEntity.getBody();
        } else {
            log.error("No or empty response received by Endpoint {}", endpoint);
            throw new NoResponseFoundException("No response found from backend for the provided commands list");
        }
    }
}
