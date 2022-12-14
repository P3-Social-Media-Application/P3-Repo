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
