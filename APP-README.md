**START**

- Hi everyone.
- The following tips should aid the testing and running of my drone dispatch applciation.
- I used the in-memory h2-Database as specified in the task.

### Login Details:
I encrypted my h2-Database username and password, so you will not be able to access it with the details visible on the properties file.

*username: root
*password: pword
*JDBC URL: jdbc:h2:mem:dronedb

*port: 9119

*OpenAPI: http://localhost:9119/swagger-ui.html
*Database UI: http://localhost:9119/h2-console


## Notable Implemented Features:
- The endpoints are quite descriptive and I implemented Asynchronous logging for easy monitoring of the process.
- I had to create an extra service method to get the current load on a specified drone as it was necessary.
- I also implemented a Smart algorithm to optimize the loading of the drones such that, no matter the number of medications with different weights passed for loading, my algorithm will ensure to load the maximum possible load without exceeding the weight limit of the drone.
- I wrote unit tests for all the service methods available.
- I created a dockerfile that can be found in the application path.

### Log file Location:
musala_drone_app.log will be created in the "logs" folder in the application. This is to show the Asynchronous logging of the application.

I am available for any clarifications required. Thank you.

**END**