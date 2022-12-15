# P3-Repo

Backend of team 1's project 3. Uses Spring Boot and can be deployed on an AWS EC2.

<h3>Specifications:</h3>
<ul>
  <li>Spring Framework 2.7.1</li>
  <li>Java 11</li>
  <li>Generic Embedded H2 database</li>
</ul>
<h3>Endpoints:</h3>
<ul>
  <li>/about<ol>
    <li>/about-info
      <br><em>> Saves new or updated "about me" text entered by a logged in user.</em></li>
    <li>/get-info
      <br><em>> Returns "about me" text of a logged in user.</em></li>
    <li>/get-info/{id}
      <br><em>> Returns "about me" text of a user, given their id.</em></li>
  </ol></li>
  <li>/auth<ol>
    <li>/login
      <br><em>> Initiates the session of a user when they successfully log in.</em></li>
    <li>/logout
      <br><em>> Terminates the session of a user when they log out.</em></li>
    <li>/register
      <br><em>> Adds a new user to the database when they successfully register.</em></li>
    <li>/change-password
      <br><em>> Changes the password of the currently logged in user.</em></li>
    <li>/user
      <br><em>> Returns the current logged in user.</em></li>
  </ol></li>
  <li>/error<ol>
    <li>(from new model and view ->) forward:/
      <br><em>> Used to redirect to the page being reloaded when it improperly reloads.</em></li>
  </ol></li>
  <li>/likes<ol>
    <li>/addlike
      <br><em>> Saves a new like made by a logged in user.</em></li>
    <li>/getlikes/{postid}
      <br><em>> Returns a list of all likes, given a user's id.</em></li>
  </ol></li>
  <li>/post<ol>
    <li>/
      <br><em>> Returns a list of all posts (posts contain lists of their comments).</em></li>
    <li>/(with post in body)
      <br><em>> Adds a new post or comment to the database.</em></li>
    <li>/(with post in body and HTTP Session)
      <br><em>> Deletes a post of a logged in user.</em></li>
    <li>/comment
      <br><em>> Deletes a comment of a logged in user.</em></li>
    <li>/updatePost
      <br><em>> Updates a post or comment.</em></li>
  </ol></li>
  <li>/user<ol>
    <li>/(with HTTP Session)
      <br><em>> Returns a logged in user.</em></li>
    <li>/(with user in body and HTTP session)
      <br><em>> Returns true/false for the existence of a user.</em></li>
    <li>/friend/{email}
      <br><em>> Returns a user, given their email.</em></li>
  </ol></li>
</ul>
