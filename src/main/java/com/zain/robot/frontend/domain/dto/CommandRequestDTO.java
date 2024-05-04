package com.zain.robot.frontend.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class CommandRequestDTO {
    private String stringCommand;
    private Long currentRowPosition;
    private Long currentColPosition;
    private String facePosition;
}
