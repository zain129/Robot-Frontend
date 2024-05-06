package com.zain.robot.frontend.integration;

import com.zain.robot.frontend.domain.dto.CommandRequestDTO;
import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {RobotBackendIntegration.class})
@ExtendWith(SpringExtension.class)
class RobotBackendIntegrationTest {
    @Autowired
    private RobotBackendIntegration robotBackendIntegration;

    @MockBean(name = "robotBackendWebClient")
    private WebClient webClient;

    @Test
    void testFetchCommandResponse() {
        // Arrange
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        Mono<ResponseEntity<CommandResponseDTO>> justResult = Mono
                .just(mock(ResponseEntity.class));
        when(responseSpec.toEntity(Mockito.<Class<CommandResponseDTO>>any())).thenReturn(justResult);
        WebClient.RequestHeadersSpec<WebClient.RequestBodySpec> requestHeadersSpec = mock(
                WebClient.RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        Mockito.<WebClient.RequestHeadersSpec<?>>when(requestBodySpec.bodyValue(Mockito.any()))
                .thenReturn(requestHeadersSpec);
        WebClient.RequestBodySpec requestBodySpec2 = mock(WebClient.RequestBodySpec.class);
        when(requestBodySpec2.contentType(Mockito.any())).thenReturn(requestBodySpec);
        WebClient.RequestBodyUriSpec requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.any(), isA(Object[].class))).thenReturn(requestBodySpec2);
        when(webClient.post()).thenReturn(requestBodyUriSpec);

        // Act
        robotBackendIntegration.fetchCommandResponse(new CommandRequestDTO());

        // Assert
        verify(webClient).post();
        verify(requestBodySpec).bodyValue(isA(Object.class));
        verify(requestBodySpec2).contentType(isA(MediaType.class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).toEntity(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("/execute-commands"), isA(Object[].class));
    }

    @Test
    void testFetchListOfCommandResponses() {
        // Arrange
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        Mono<ResponseEntity<List<CommandResponseDTO>>> justResult = Mono
                .just(mock(ResponseEntity.class));
        when(responseSpec.toEntityList(Mockito.<Class<CommandResponseDTO>>any())).thenReturn(justResult);
        WebClient.RequestHeadersSpec<WebClient.RequestBodySpec> requestHeadersSpec = mock(
                WebClient.RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        Mockito.<WebClient.RequestHeadersSpec<?>>when(requestBodySpec.bodyValue(Mockito.any()))
                .thenReturn(requestHeadersSpec);
        WebClient.RequestBodySpec requestBodySpec2 = mock(WebClient.RequestBodySpec.class);
        when(requestBodySpec2.contentType(Mockito.any())).thenReturn(requestBodySpec);
        WebClient.RequestBodyUriSpec requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.any(), isA(Object[].class))).thenReturn(requestBodySpec2);
        when(webClient.post()).thenReturn(requestBodyUriSpec);

        // Act
        robotBackendIntegration.fetchListOfCommandResponses(new ArrayList<>());

        // Assert
        verify(webClient).post();
        verify(requestBodySpec).bodyValue(isA(Object.class));
        verify(requestBodySpec2).contentType(isA(MediaType.class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).toEntityList(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("/execute-all-commands"), isA(Object[].class));
    }

    @Test
    void testFetchListOfCommandResponses2() {
        // Arrange
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        Mono<ResponseEntity<List<CommandResponseDTO>>> justResult = Mono
                .just(mock(ResponseEntity.class));
        when(responseSpec.toEntityList(Mockito.<Class<CommandResponseDTO>>any())).thenReturn(justResult);
        WebClient.RequestHeadersSpec<WebClient.RequestBodySpec> requestHeadersSpec = mock(
                WebClient.RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        Mockito.<WebClient.RequestHeadersSpec<?>>when(requestBodySpec.bodyValue(Mockito.any()))
                .thenReturn(requestHeadersSpec);
        WebClient.RequestBodySpec requestBodySpec2 = mock(WebClient.RequestBodySpec.class);
        when(requestBodySpec2.contentType(Mockito.any())).thenReturn(requestBodySpec);
        WebClient.RequestBodyUriSpec requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.any(), isA(Object[].class))).thenReturn(requestBodySpec2);
        when(webClient.post()).thenReturn(requestBodyUriSpec);

        ArrayList<CommandRequestDTO> commandRequestDTO = new ArrayList<>();
        CommandRequestDTO buildResult = CommandRequestDTO.builder()
                .currentColPosition(1L)
                .currentRowPosition(1L)
                .facePosition("Face Position")
                .stringCommand("String Command")
                .build();
        commandRequestDTO.add(buildResult);

        // Act
        robotBackendIntegration.fetchListOfCommandResponses(commandRequestDTO);

        // Assert
        verify(webClient).post();
        verify(requestBodySpec).bodyValue(isA(Object.class));
        verify(requestBodySpec2).contentType(isA(MediaType.class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).toEntityList(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("/execute-all-commands"), isA(Object[].class));
    }

    @Test
    void testFetchListOfCommandResponses3() {
        // Arrange
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
        Mono<ResponseEntity<List<CommandResponseDTO>>> justResult = Mono
                .just(mock(ResponseEntity.class));
        when(responseSpec.toEntityList(Mockito.<Class<CommandResponseDTO>>any())).thenReturn(justResult);
        WebClient.RequestHeadersSpec<WebClient.RequestBodySpec> requestHeadersSpec = mock(
                WebClient.RequestHeadersSpec.class);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
        Mockito.<WebClient.RequestHeadersSpec<?>>when(requestBodySpec.bodyValue(Mockito.any()))
                .thenReturn(requestHeadersSpec);
        WebClient.RequestBodySpec requestBodySpec2 = mock(WebClient.RequestBodySpec.class);
        when(requestBodySpec2.contentType(Mockito.any())).thenReturn(requestBodySpec);
        WebClient.RequestBodyUriSpec requestBodyUriSpec = mock(WebClient.RequestBodyUriSpec.class);
        when(requestBodyUriSpec.uri(Mockito.any(), isA(Object[].class))).thenReturn(requestBodySpec2);
        when(webClient.post()).thenReturn(requestBodyUriSpec);

        ArrayList<CommandRequestDTO> commandRequestDTO = new ArrayList<>();
        CommandRequestDTO buildResult = CommandRequestDTO.builder()
                .currentColPosition(1L)
                .currentRowPosition(1L)
                .facePosition("Face Position")
                .stringCommand("String Command")
                .build();
        commandRequestDTO.add(buildResult);
        CommandRequestDTO buildResult2 = CommandRequestDTO.builder()
                .currentColPosition(1L)
                .currentRowPosition(1L)
                .facePosition("Face Position")
                .stringCommand("String Command")
                .build();
        commandRequestDTO.add(buildResult2);

        // Act
        robotBackendIntegration.fetchListOfCommandResponses(commandRequestDTO);

        // Assert
        verify(webClient).post();
        verify(requestBodySpec).bodyValue(isA(Object.class));
        verify(requestBodySpec2).contentType(isA(MediaType.class));
        verify(requestHeadersSpec).retrieve();
        verify(responseSpec).toEntityList(isA(Class.class));
        verify(requestBodyUriSpec).uri(eq("/execute-all-commands"), isA(Object[].class));
    }
}
