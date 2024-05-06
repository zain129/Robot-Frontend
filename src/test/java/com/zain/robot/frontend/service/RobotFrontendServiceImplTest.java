package com.zain.robot.frontend.service;

import com.zain.robot.frontend.domain.dto.CommandRequestDTO;
import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import com.zain.robot.frontend.domain.enums.OperationType;
import com.zain.robot.frontend.exception.NoResponseFoundException;
import com.zain.robot.frontend.integration.RobotBackendIntegration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {RobotFrontendServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class RobotFrontendServiceImplTest {
    @MockBean
    private RobotBackendIntegration robotBackendIntegration;

    @Autowired
    private RobotFrontendServiceImpl robotFrontendServiceImpl;

    /**
     * Method under test:
     * {@link RobotFrontendServiceImpl#executeCommand(String, String, String, String)}
     */
    @Test
    void testExecuteCommand() {
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
        when(responseEntity.hasBody()).thenReturn(true);
        when(robotBackendIntegration.fetchCommandResponse(Mockito.<CommandRequestDTO>any()))
                .thenReturn(responseEntity);

        // Then
        assertEquals(robotBackendIntegration.fetchCommandResponse(Mockito.<CommandRequestDTO>any())
                , responseEntity);
        assertNotNull(commandResponseDTO);
        assertEquals(commandResponseDTO.getMovingSteps(), 1L);
        assertNotNull(commandResponseDTO2);
        assertNotNull(commandResponseDTO2.getNewRowPosition());
    }

    /**
     * Method under test: {@link RobotFrontendServiceImpl#executeAllCommand(String)}
     */
    @Test
    void testExecuteAllCommand() throws IOException {
        // Arrange
        ResponseEntity<List<CommandResponseDTO>> responseEntity = mock(ResponseEntity.class);
        ArrayList<CommandResponseDTO> commandResponseDTOList = new ArrayList<>();
        when(responseEntity.getBody()).thenReturn(commandResponseDTOList);
        when(responseEntity.hasBody()).thenReturn(true);
        when(robotBackendIntegration.fetchListOfCommandResponses(Mockito.<List<CommandRequestDTO>>any()))
                .thenReturn(responseEntity);

        // Act
        List<CommandResponseDTO> actualExecuteAllCommandResult = robotFrontendServiceImpl
                .executeAllCommand("Command String");

        // Assert
        verify(robotBackendIntegration).fetchListOfCommandResponses(isA(List.class));
        verify(responseEntity).getBody();
        verify(responseEntity).hasBody();
        assertTrue(actualExecuteAllCommandResult.isEmpty());
        assertSame(commandResponseDTOList, actualExecuteAllCommandResult);
    }

    /**
     * Method under test: {@link RobotFrontendServiceImpl#executeAllCommand(String)}
     */
    @Test
    void testExecuteAllCommand2() throws IOException {
        // Arrange
        ResponseEntity<List<CommandResponseDTO>> responseEntity = mock(ResponseEntity.class);
        when(responseEntity.hasBody()).thenReturn(false);
        when(robotBackendIntegration.fetchListOfCommandResponses(Mockito.<List<CommandRequestDTO>>any()))
                .thenReturn(responseEntity);

        // Act and Assert
        assertThrows(NoResponseFoundException.class, () -> robotFrontendServiceImpl.executeAllCommand("Command String"));
        verify(robotBackendIntegration).fetchListOfCommandResponses(isA(List.class));
        verify(responseEntity).hasBody();
    }
}
