<h1>E-Shop Webapp</h1>
<h2>Introduction</h2>
<p>The main purpose of this project is to showcase my abilities in creating back-end systems in Java. I decided to create a webapp for an e-shop as it requires me to consider a wide variety of issues, such as data security, relational databases and needs of users. Although the project is functional, there are many improvements and features I would like to implement and these will be documented below. Firstly I will explain how to run the application in IntelliJ.</p>
<h2>Running the Application</h2>
<h3>Step 1- Setup</h3>
<p>Having cloned the project into IntelliJ there are a few brief things that need to be setup before running. You can either run the application with an in memory database or the pre-made example database. The steps for setting up each will be listed below.</p>
<h4>In Memory Database</h4>
<p>For the in memory database simply comment out the current spring.datasource.url in the application.properties file and uncomment the in memory database url. You will then need to comment out the spring.jpa.hibernate.dll-auto property and uncomment labelled property. The application will then be ready to run, although it will be empty except for  2 user accounts.
<h4>Pre-made Database</h4>
<p>This setup requires a few more steps to setup, but will load the application with example products, orders and users. The steps are as follows: </p>
<ol>
  <li>In the application.properties file enter the directory you have saved the project to. You can do this by right clicking the root directory of the project and selecting "Copy Path" and pasting it into the marked area.</li>
  <li>Within the root-directory you will find a jar file called h2-1.4.197.jar. This is the jar file used to run an instance of the H2 database on your system. You can either run this in IntelliJ or simply double click the file in explorer. This should bring up a H2 database console in your browser.</li>
  <li>In the H2 Database console copy the full URL from the spring.datasource.url and paste it into the JDBC Url section. Clicking connect will connect you to the database. The database should be setup for the application.</li> 
</ol>
<h3>Step 2 - Starting the Application</h3>
<p>To run the application simply select the bootRun option in Gradle. This will build the application and start running it. To view the application running simply open up your web browser and enter the URL <a href"localhost:8080">localhost:8080</a>. There are preset user account loaded into the database and the login details for these accounts are as follows:</p>
<ul>
  <li>Admin Account: Email = "admin", Password = "password"</li>
  <li>Customer 1: Email = "test@test.com", Password = "password"</li>
  <li>Customer 2 (Pre-made Database Only): Email = "test2@test.com", Password = "password"</li>
</ul>
<h2>Improvements/Optimisations</h2>
<p>Whilst making this project I found that I would regularly think of features and optimisations that I would like to add to this project. my main priority was to produce a working example and I decided to press on towards this goal rather than go back and implement new features or improvements until I had a working application. Below I will list the features and improvements I hope to make to the project over the coming weeks that will improve the performance and expand the capabilities of the application.</p>
<ul>
  <li>Add server side validation for the data received from forms.</li>
  <li>Change the structure of the shopping cart object to a symbol table.</li>
  <li>Add flash messages to better communicate to the user.</li>
  <li>Add basic browser side validation.</li>
  <li>Add mechanism to reset a users password.</li>
  <li>Log a tally of views/sales of products so the most popular products can be displayed more prominently.</li>
  <li>Create a RESTApi to access the admin data.</li>
  <li>Handle money in a better way so that it is always correctly rounded.</li>
</ul>
