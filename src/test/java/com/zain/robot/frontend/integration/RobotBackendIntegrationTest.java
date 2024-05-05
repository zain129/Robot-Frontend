package com.zain.robot.frontend.integration;

import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import com.zain.robot.frontend.domain.enums.OperationType;
import com.zain.robot.frontend.exception.NoResponseFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {RobotBackendIntegration.class, String.class})
@ExtendWith(SpringExtension.class)
class RobotBackendIntegrationTest {
    @MockBean(name = "genericRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    private RobotBackendIntegration robotBackendIntegration;

    @Test
    void testFetchCommandResponse() throws RestClientException {
        // Given
        ResponseEntity<Object> responseEntity = mock(ResponseEntity.class);
        CommandResponseDTO commandResponseDTO = CommandResponseDTO.builder()
                .facePosition("Face Position")
                .movingSteps(1)
                .newColPosition(1L)
                .newRowPosition(1L)
                .operationType(OperationType.POSITION)
                .otherInfo("Other Info")
                .build();

        // When
        when(responseEntity.getBody()).thenReturn(commandResponseDTO);
        when(responseEntity.hasBody()).thenReturn(true);

        // Then
        assertTrue(responseEntity.hasBody());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    void testFetchCommandResponse2() throws RestClientException {
        // Given
        RestTemplate restTemplate = mock(RestTemplate.class);
        ResponseEntity<Object> responseEntity = mock(ResponseEntity.class);

        // When
        when(restTemplate.exchange(Mockito.any(), Mockito.any(), Mockito.<HttpEntity<Object>>any(),
                Mockito.<Class<Object>>any(), isA(Object[].class))).thenReturn(responseEntity);
        when(responseEntity.hasBody()).thenReturn(false);

        // Then
        assertThrows(NoResponseFoundException.class,
                () -> (new RobotBackendIntegration(restTemplate, "https://example.org/example"))
                        .fetchCommandResponse("/test-endpoint", "Command", "42", "42", "Face Position"));
        verify(restTemplate).exchange(eq("https://example.org/example/test-endpoint"),
                isA(HttpMethod.class), isA(HttpEntity.class), isA(Class.class), isA(Object[].class));
        verify(responseEntity).hasBody();
    }
}
