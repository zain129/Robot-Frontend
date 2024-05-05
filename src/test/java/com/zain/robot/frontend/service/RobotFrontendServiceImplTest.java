package com.zain.robot.frontend.service;

import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import com.zain.robot.frontend.domain.enums.OperationType;
import com.zain.robot.frontend.integration.RobotBackendIntegration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RobotFrontendServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RobotFrontendServiceImplTest {
    @MockBean
    private RobotBackendIntegration robotBackendIntegration;

    @Autowired
    private RobotFrontendServiceImpl robotFrontendServiceImpl;

    @Test
    void testExecuteCommand() throws IOException {
        // Given
        CommandResponseDTO commandResponseDTO = CommandResponseDTO.builder()
                .facePosition("Face Position")
                .movingSteps(1)
                .newColPosition(1L)
                .newRowPosition(1L)
                .operationType(OperationType.POSITION)
                .otherInfo("Other Info")
                .build();
        CommandResponseDTO commandResponseDTO2 = CommandResponseDTO.builder()
                .facePosition("Face Position")
                .movingSteps(1)
                .newColPosition(1L)
                .newRowPosition(1L)
                .operationType(OperationType.POSITION)
                .otherInfo("Other Info")
                .build();
        ResponseEntity<CommandResponseDTO> responseEntity = mock(ResponseEntity.class);

        // When
        when(responseEntity.getBody()).thenReturn(commandResponseDTO);
        when(robotBackendIntegration.fetchCommandResponse(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(commandResponseDTO);
        when(robotFrontendServiceImpl.executeCommand("1", "1", "1", "Face Position"))
                .thenReturn(commandResponseDTO2);

        // Then
        assertEquals(robotBackendIntegration.fetchCommandResponse(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())
                , commandResponseDTO);
        assertEquals(robotFrontendServiceImpl.executeCommand("1", "1", "1", "Face Position")
                , commandResponseDTO2);
        assertNotNull(commandResponseDTO);
        assertEquals(commandResponseDTO.getMovingSteps(), 1L);
        assertNotNull(commandResponseDTO2);
        assertNotNull(commandResponseDTO2.getNewRowPosition());
    }
}
