# Robot Frontend
This repository contains the frontend (UI) implementation for the assignment provided by Idealo.

### Explanation
* The purpose of this project is to facilitate the UI of the robot application. <br>
* It consumes the backend POST API to send commands and receive the response. <br>
* Upon receiving the response successfully, the command execution will be carried out by displaying on the page. <br>
* In case of incorrect commands, no UI change will be made. (Not covered in this assignment) <br>

### How to run the project
* Clone the robot-backend project from  <link>https://github.com/zain129/Robot-Backend/tree/develop</link> and set-up using IntelliJ or any other IDE.
  * Hit the URL <link>http://localhost:8090/swagger-ui/index.html</link> to ensure that the backend is up and running.
* Clone the robot-frontend project from  <link>https://github.com/zain129/Robot-Frontend/tree/develop</link> and set-up using IntelliJ or any other IDE.
  * Goto <link>http://localhost:8091/</link>
  * Click on button "Execute Commands"

### Summary
#### What was expected?
* To create the front of an application which will execute commands to move and render a robot in a grid of 5x5.
* To add test cases in order to ensure that the code written is working as expected.

#### What is done?
* Added an HTML page using thymeleaf to render a grid of 5x5.
* Once the execute command button is clicked, Backend API is consumed to execute the command and get the response.
* Response is then used to render the position of robot on the grid.
* CSS and Bootstrap are added for basic styling of the page.
* Javascript/JQuery is added to dynamically render the position of the robot.
* Test cases are included to ensure proper working of the frontend of the system.

#### What is next?
* Code review will be done and review comments will be resolved (if any).
* A pull-request will be raised to merge code from develop branch into master branch.

<br/>

#### Credits
* https://stackoverflow.com
* https://www.thymeleaf.org/documentation.html
* https://www.baeldung.com/spring-thymeleaf-css-js
* https://docs.diffblue.com/
