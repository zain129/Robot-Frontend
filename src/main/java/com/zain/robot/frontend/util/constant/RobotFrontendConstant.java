package com.zain.robot.frontend.util.constant;

import java.util.List;

public class RobotFrontendConstant {
    public static final String ROBOT_BACKEND_EXECUTE_COMMAND_URL = "/execute-commands";
    public static final String ROBOT_BACKEND_EXECUTE_ALL_COMMAND_URL = "/execute-all-commands";
    public static final List<String> PRE_DEFINED_COMMANDS = List.of(
            "POSITION 1 3 EAST",
            "FORWARD 3",
            "WAIT",
            "TURNAROUND",
            "FORWARD 1",
            "RIGHT",
            "FORWARD 2");

    /**
     * Face Positions
     */
    public static final String FACE_RIGHT = "RIGHT";
    public static final String FACE_LEFT = "LEFT";
    public static final String FACE_UP = "UP";
    public static final String FACE_DOWN = "DOWN";

    /**
     * Commands
     */
    public static final String POSITION = "POSITION";
    public static final String TURNAROUND = "TURNAROUND";
    public static final String RIGHT = "RIGHT";
}
