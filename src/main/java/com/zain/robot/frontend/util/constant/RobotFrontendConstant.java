package com.zain.robot.frontend.util.constant;

import java.util.List;

public class RobotFrontendConstant {
    public static final String ROBOT_BACKEND_EXECUTE_COMMAND_URL = "/execute-commands";
    public static final List<String> PRE_DEFINED_COMMANDS = List.of(
            "POSITION 1 3 EAST",
            "FORWARD 3",
            "WAIT",
            "TURNAROUND",
            "FORWARD 1",
            "RIGHT",
            "FORWARD 2");
}
