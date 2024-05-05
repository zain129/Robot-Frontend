package com.zain.robot.frontend.domain.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class CommandRequestDTO {
    private List<String> listOfCommands;
}
