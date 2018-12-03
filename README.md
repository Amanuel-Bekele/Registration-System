# Registration-System
A Web Application built using Spring Framework for university registrar.


Steps to run the application.


 Download the .zip file or clone repository in your machine.

 If you download zip file, extract the compressed file and open it in you favorite IDE.

 Download all the dependencies in pom.xml file.

 Add these properties in your application.properties file

spring.thymeleaf.enabled=true
server.port=3600


 Add properties to configure your database. We have used AWS cloud platform to host our database server,  if you want to user that, you can use following credientials.

spring.datasource.url=jdbc:mysql://registrationsystem.cctn4agw5hyx.us-east-2.rds.amazonaws.com:3306/registrationsystem?useSSL=false
spring.datasource.username=root
spring.datasource.password=root2018
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.jpa.hibernate.ddl-auto=update


 Java Mail Service connection properties, you should configure following properties. We have used mailtrap.io for testing purpose.


spring.mail.host=
spring.mail.port=
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true




 Run the application.


 Go to localhost:3600


 You'll be prompted to login page.


 There is one ADMIN in the application. Uee the following credentials to login as admin.
      User name: ashok@mum.edu
      Password: fun123


 You'll be prompted to the admin landing page. (If fails please make sure you loaded the cloud db properly)


 Test all the functionalities.


 To add a student go to Students -> Add New (left side ba), add the details and submit.


 This will send email to the email address you provided with an auto generated password.


 Newly registered student can login to the system using the credentials they received on their email sent by the system with auto generated password.


 Then you can explore different features on student dashboard.


##Group members contributions.
It was a nice team work experience, we learned lots of things.
Below are few of the significant contributions of each member.

Amanuel Bekele

Configured Spring security (login/ logout/ register/ authorize users based url and method)
Provided a REST API for student. which include fetching courses, prerequisites, wavers.
Implemented add preferences functionality.
Other business logics in service layer.


Ashok Adhikari

Analyzed the functional requirements and possible use cases
Identified domain classes and  required UIs for different stakeholders in the system
Added admin module functionalities such as Student Registration, Adding Course, Adding Blocks, Adding Prerequisite Courses
Validated forms using Hibernate Validator and also created custom validation annotation.
Also Implemented features for student user such as displaying available courses, enrolled courses
Implemented internationalization feature in student module in admin dashboard.


Duesdedit Lutwama

Implemented domain objects and relationships.
Implemented admin modules for viewing course, blocks, sections.
Participated in formulating business logic with the API guy through pair programming.
Designed database schema and deployed in cloud and used ORM for the domain entities.
