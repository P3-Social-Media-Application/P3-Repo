# Startup

The backend of this project can be run either locally or remotely.

<h3>To run locally:</h3>
<ol type="1">
  <li>Be sure the latest Java Runtime Environment (JRE) is installed.</li>
  <li>Run the <code>SocialMediaApplication.java</code> file in <code>social-media-spring-main/main/java/com/revature/</code>. This can be accomplished by:
    <ul>
      <li>Running <code>javac SocialMediaApplication.java</code> and then <code>java SocialMediaApplication.class</code> in the command line at the folder's location.</li>
      <li>Opening this project in Spring Tool Suite and running the project as a Spring Boot App.
    </ul>
  </li>
  <li>This will start the App and an embedded H2 database.
    <ul>
      <li>The App will be running on <a href="http://localhost:8081/">http://localhost:8081/</a>.</li>
      <li>The H2 database can be logged into at <a href="http://localhost:8081/h2-console/login.jsp">http://localhost:8081/h2-console/login.jsp</a> with:
        <dl>
          <dt>Driver Class:</dt>
          <dd>org.h2.Driver</dd>
          <dt>JDBC URL:</dt>
          <dd>jdbc:h2:mem:memdb</dd>
          <dt>User Name:</dt>
          <dd>sa</dd>
          <dt>Password:</dt>
          <dd>password</dd>
</ol>

<h3>To run remotely:</h3>
<ol type="1">
<li>Set up your Amazon EC2 Instance.
<li>Set up your Jenkins pipeline.
<li>Take note of where Jenkins places SpringBoot project's .jar file after the build completes.
<li>Create a simple systemd service that will tell the EC2 to run the .jar file.
<ul>
<li>Below is an example of the simple service you will need to create.
<li><img src="Spring-Service.jpg">
</ul>
<li>Incorporate the stopping and starting of this simple service into your Jenkins build.  This is done in post build actions.