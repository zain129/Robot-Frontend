package com.zain.robot.frontend.controller;

import com.zain.robot.frontend.domain.dto.CommandResponseDTO;
import com.zain.robot.frontend.domain.enums.OperationType;
import com.zain.robot.frontend.service.RobotFrontendService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RobotFrontendController.class})
@ExtendWith(SpringExtension.class)
class RobotFrontendControllerTest {
    @Autowired
    private RobotFrontendController robotFrontendController;

    @MockBean
    private RobotFrontendService robotFrontendService;

    @Test
    void testExecuteCommand() throws Exception {
        // Given
        CommandResponseDTO commandResponseDTO = CommandResponseDTO.builder()
                .facePosition("Face Position")
                .movingSteps(1)
                .newColPosition(1L)
                .newRowPosition(1L)
                .operationType(OperationType.POSITION)
                .otherInfo("Other Info")
                .build();

        // When
        when(robotFrontendService.executeCommand(Mockito.any(), Mockito.any(), Mockito.any(),
                Mockito.any())).thenReturn(commandResponseDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/execute-command");

        // Then
        MockMvcBuilders.standaloneSetup(robotFrontendController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(5))
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("colPosition", "command", "commandScriptIndexNumber", "facePosition", "rowPosition"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
    }

    @Test
    void testViewHomePage() throws Exception {
        // Given
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");

        // When and Then
        MockMvcBuilders.standaloneSetup(robotFrontendController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(
                        MockMvcResultMatchers.model().attributeExists("colPosition", "commandScriptIndexNumber", "rowPosition"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
    }

    @Test
    void testViewHomePage2() throws Exception {
        // Given
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        requestBuilder.contentType("https://example.org/example");

        // When and Then
        MockMvcBuilders.standaloneSetup(robotFrontendController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(
                        MockMvcResultMatchers.model().attributeExists("colPosition", "commandScriptIndexNumber", "rowPosition"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("index"));
    }
}
