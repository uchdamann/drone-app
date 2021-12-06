**START**

- Hi everyone.
- The following tips should aid the testing and running of my drone dispatch applciation.
- I used the in-memory h2-Database as specified in the task.

### Login Details:
I encrypted my h2-Database username and password, so you will not be able to access it with the details visible on the properties file. I also ensured to allow remote access to the database for the convenience of testing from multiple personnel.
*username: root
*password: pword
*JDBC URL: jdbc:h2:mem:dronedb

*port: 9119

*swagger: http://localhost:9119/swagger-ui.html
*Database UI: http://localhost:9119/h2-console

The endpoints are quite descriptive and I implemented Asynchronous logging for easy monitoring of the process.
### Log file Location:
musala_drone_app.log will be created in the "logs" folder in the application. This is not an ideal location as it increases the size of the application, thereby making it heavy but I just did this in order to show the Asynchronous logging of the application in the test case scenario.

I am available for any clarifications required. Thank you.

**END**