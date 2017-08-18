# Project Title

This is a platform service application written in Spring Boot. The application provides API which when called from the client application keeps a copy of the data in a history server. 
For example you have a person entity and before updating a person a copy of the old data needs to be preserved. This can be simply done by calling something like 
	auditService.createAuditLog(id.toString(), "Person", Person);
The application provides a jar that needs to be included in the client application. (Will be available as a Maven repository soon)

## Getting Started
To set up this project locally.
This project can run on MongoDB or PostgreSql as Database. 
First do a git clone of the project from https://github.com/accionlabs/msa_spikes.git
This will download the reference implementation also.
before you can start and test there are some changes that needs to be done.
If you are using MongoDB: Open the application.yml file and 
a) Put your URI  
	uri: mongodb://username:password@your-mongo-conn/mongo_database 
	If you are running on mlabs it will be something like  uri: mongodb://DC:Pass23@ds048966.mlab.com:49496/audit
b) Make sure the profile is set like below: 
		profiles:
	      active: mongo
	
If you are using MongoDB: Open the application.yml file and 
a) Put your URI  
	uri: mongodb://username:password@your-mongo-conn/mongo_database 
	If you are running on mlabs it will be something like  uri: mongodb://DC:Pass23@ds048966.mlab.com:49496/audit
b) Make sure the profile is set like below: 
		profiles:
	      active: mongo

If you are using PostgresSQL: Open the application.yml file and 
a) Configure your datasource with your postgres credentials  
datasource:
    url: jdbc:postgresql://your_host:your_port/your_database 
    username : your_username
    password: your_password
    driver-class-name: org.postgresql.Driver
b) Make sure the profile is set like below: 
		profiles:
	      active: postgres
	

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
http://localhost:9000/audit/history/12/Test 
You should see the audit data created by the test program


Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With



## Versioning


## Authors

* **DC** - *Initial work* 



## Acknowledgments

* Guruprasad A
* Bharat Gupta


