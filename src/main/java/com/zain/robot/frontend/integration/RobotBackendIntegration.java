package com.zain.robot.frontend.integration;

import com.zain.robot.frontend.domain.dto.CommandRequestDTO;
import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import com.zain.robot.frontend.exception.NoResponseFoundException;
import com.zain.robot.frontend.util.CommonRestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

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

    public List<CommandResponseDTO> fetchCommandResponse(String endpoint, List<String> listOfCommands) throws IOException {
        String uri = robotBackendRootUrl + endpoint;
        log.info("Entered fetchCommandResponse method to call the endpoint: {}", endpoint);

        CommandRequestDTO commandRequestDTO = CommandRequestDTO.builder()
                .listOfCommands(listOfCommands)
                .build();
        ResponseEntity<List<CommandResponseDTO>> listResponseEntity = restTemplate.exchange(uri, HttpMethod.POST,
                CommonRestUtil.buildHttpEntityRequest(commandRequestDTO, MediaType.APPLICATION_JSON),
                new ParameterizedTypeReference<>() {
                });

        if (listResponseEntity.hasBody()) {
            log.info("Endpoint {} returned response {}", endpoint, listResponseEntity);
            return listResponseEntity.getBody();
        } else {
            log.error("No or empty response received by Endpoint {}", endpoint);
            throw new NoResponseFoundException("No response found from backend for the provided commands list");
        }
    }
}
