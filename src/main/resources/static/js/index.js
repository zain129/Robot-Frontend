
function moveRobot() {
    // Get current row and column index
    var currentRowIndex = rowPosition - 1;
    var currentColIndex = colPosition - 1;

    if (executionCommand != null) {
        clearPreviousRobotLocation();

        var currentCell = $('#item-cell-' + currentRowIndex + '-' + currentColIndex);
        currentCell.addClass("robot");

        var cellClass = "";
        if (facePosition == "UP") {
            cellClass = "robot-up";
        } else if (facePosition == "RIGHT") {
            cellClass = "robot-left";
        } else if (facePosition == "LEFT") {
            cellClass = "robot-right";
        }
        currentCell.addClass(cellClass);

        $('#currentState').text(executionCommand.operationType);
        if (executionCommand != null) console.log("commandResponse: " + JSON.stringify(executionCommand));

        var commandScriptIndexNumberVal = parseInt($('#commandScriptIndexNumber').val());
        if (commandScriptIndexNumberVal < 6) {
            $('#commandScriptIndexNumber').val(commandScriptIndexNumberVal+1);
            $('#rowPosition').val(rowPosition);
            $('#colPosition').val(colPosition);
            $('#facePosition').val(facePosition);

            $('#commandForm').delay(2000).queue(function(next) {
                $(this).submit();
                next();
            });
        }

    }
}

function clearPreviousRobotLocation() {
    // Remove robot icon from all cells
    ['.robot', '.robot-up', '.robot-left', '.robot-right'].forEach(
        function(className) {
            $(className).remove();
        });
}

function setInitialValues() {
    // Set initial values for robot's position
    $('#commandScriptIndexNumber').val(0);
    $('#rowPosition').val(1);
    $('#colPosition').val(1);
    $('#facePosition').val("DOWN");
}


$(document).ready(function() {
    moveRobot();
});

