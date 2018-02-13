# Enterprise Audit Service

This is a platform service application written in Spring Boot. The application provides API which when called from the client application keeps a copy of the data in a history server. 
For example you have a person entity and before updating a person a copy of the old data needs to be preserved. This can be simply done by calling something like 
	auditService.createAuditLog(id.toString(), "Person", Person);
The application provides a jar that needs to be included in the client application. (Will be available as a Maven repository soon)

## Getting Started
To set up this project locally.
This project can run on MongoDB or PostgreSql as Database. 
First do a git clone of the project from https://github.com/dwai1714/enterprise-audit-service.git
This will download the reference implementation also.
before you can start and test there are some changes that needs to be done.
If you are using MongoDB: Open the application.yml file and 
a) Put your URI  
	uri: mongodb://username:password@your-mongo-conn/mongo_database 
	If you are running on mlabs it will be something like  uri: mongodb://DC:Pass23@ds048966.mlab.com:49496/audit
b) Make sure the profile is set like below: 
		spring:
		  profiles:
             include: mongo 
c)   spring:
        autoconfigure:
          exclude: org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration, org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration

If you are using PostgresSQL: Open the application.yml file and 
a) Configure your datasource with your postgres credentials  
datasource:
    url: jdbc:postgresql://your_host:your_port/your_database 
    username : your_username
    password: your_password
    driver-class-name: org.postgresql.Driver
b) Make sure the profile is set like below: 
		spring:
		  profiles:
             include: postgres 
c) Make sure you remove the exclude if you are switching from mongo	

### Prerequisites

This is a Spring Boot app and needs java 8 to be installed in the system.
```

### Installing

Go to the directory where the source code is downloaded  and do a 

mvn clean install
If the build is successful then you will get the following 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 01:31 min
[INFO] Finished at: 2017-07-30T18:45:41+05:30
[INFO] Final Memory: 41M/400M

Go to target folder and  run
java -jar AuditServiceProject-0.0.1-SNAPSHOT.jar
On your browser 
http://localhost:8000/audit/history/12/Test 
You should see the audit data created by the test program


### End to end tests

Integration tests added to this program inserts items to the audit_info table and returns them at get

## Deployment

To Deploy to a production system please add a application-prod.yml
Specify the port where you want this to run. In AWS elastic Beanstalk go to the SERVER_PORT and add the port.
Please go to Swagger documentation to know more about the end points.

## Using it in the Program where you want to Audit Data

To use the logger in your application you will need to write a simple wrapper program.
The Java version of the program is available at  
https://github.com/dwai1714/audit-client.git 
Either copy the jar in your class path or just copy the two files in your project.
In your service where any CRUD is called just add:
a) If you want the entity to be saved even if the logger is down or throws error:
auditClient.createAuditLog(false, yourObject.getPrimaryKey().toString(), ENTITY_NAME_OF_YOUR_OBJECT, yourObject);
b) If you want the entity NOT to be saved even if the logger is down or throws error:
auditClient.createAuditLog(yourObject.getPrimaryKey().toString(), ENTITY_NAME_OF_YOUR_OBJECT, yourObject);
In the application yaml (For Spring). Please use config file of your language specific 
add the following 
audit.serverUrl=http://localhost:8000/api (Replace with the actual URL of the server)
audit.createdBy=createUser (The audit service calls the created By user as createdBy. Please map this with what is it called in your application. In this case the Program  calls the createdBy field as createUser)
@Column(name="create_user",nullable=false, length=50)
    public String getCreateUser() {
        return this.createUser;
    }
audit.lastModifiedBy=updateUser (This is for the modified or update user)
audit.createdDate=createTs (Create timeStamp)
audit.lastModifiedDate=updateTs  (Update timeStamp)

To see a reference implementation please download the java application from this location
https://github.com/dwai1714/spring-boot-ref-impl.git


## Built With
Spring Boot


## Versioning


## Authors

* **DC** - *Initial work* 



## Acknowledgments

* Guruprasad A
* Bharat Gupta


