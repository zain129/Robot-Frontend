function moveRobot() {

    console.log(JSON.stringify(executionCommand));
    var commandResponsesSize = parseInt($('#commandResponsesSize').val());

    for (var i=0; i<commandResponsesSize; i++) {
        clearPreviousRobotLocation();
        $('#currentState').text(executionCommand[i].operationType);

        var currentRowIndex = parseInt(executionCommand[i].newRowPosition) - 1;
        var currentColIndex = parseInt(executionCommand[i].newColPosition) - 1;

        var currentCell = $('#item-cell-' + currentRowIndex + '-' + currentColIndex);
        currentCell.addClass("robot");

        var cellClass = "";
        if (executionCommand[i].facePosition == "UP") {
            cellClass = "robot-up";
        } else if (executionCommand[i].facePosition == "RIGHT") {
            cellClass = "robot-left";
        } else if (executionCommand[i].facePosition == "LEFT") {
            cellClass = "robot-right";
        }
        currentCell.addClass(cellClass);

    }

}

function clearPreviousRobotLocation() {
    // Remove robot icon from all cells
    ['.robot', '.robot-up', '.robot-left', '.robot-right'].forEach(
        function(className) {
            $(className).removeClass();
        });
}

$(document).ready(function() {
    moveRobot();
});

