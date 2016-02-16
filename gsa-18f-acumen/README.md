## Acumen GSA Agile Prototype

### Acumen Solutions's Search Recall Web App
Acumen Solutions Web App, called FDA Food Recalls leverages the openFDA API for the Food data sets. Acumen's team decided to use the Food data sets of the API, instead of the Drug data sets, in order to present a robust solution while demonstrating it's agile capacity in the short period available for prototyping.

### Approach
Since Mobile devices continue to grow in popularity year after year, and current analysis predicts that global mobile data traffic will increase nearly 11-fold between 2013 and 2018 ([http://goo.gl/xlVQfz](http://goo.gl/xlVQfz)). The need to have a responsive web app is becoming increasingly important. The Acumen team looked at foundations and frameworks that support modern Web 3.0 websites that are flexible enough for customization and growth, especially in an Agile environment. Among all the available CSS frameworks out there, Bootstrap has been chosen because of the following reasons:

Reason #1. Platform agnostic: Easy to get started, faster coding

CSS Pre-processing is great and every front end developer should learn it. However not everyone is using it. There are still many designers creating and managing CSS files the same old way. Bootstrap offers LESS files for those who know how to use it, but also provides the plain old CSS file for those who don’t want to use CSS pre-processing.
To take advantage of what Bootstrap has to offer, a developer just has to download the files from Bootstrap on Github and after unzipping, include the files in the head of any HTML document.

Reason #2. Cross browser Compatibility: Great grid system for any device size

Bootstrap is built on responsive 12-column grids, layouts and components. Whether the design calls for a fixed grid or a responsive one, its only matter of a few changes. Offsetting & Nesting of columns is also possible in both fixed and fluid width layouts.
Another useful set of features are the responsive utility classes using which a developer can make a certain block of content appear or hide only on devices based on the size of their screen. This customization possibility is very handy when a developer wants to hide some content based on screen size. Adding a class such as .visible-desktop to an element, will make it visible only for desktop users. There are similar classes for tablets and phones.

Reason #3. Base styling for most HTML elements

A website has many different elements such as headings, lists, tables, buttons, forms, etc. All these fundamental HTML elements have been styled and enhanced with extensible classes. The HTML elements for which styles are provided are:
Typography Code Tables Forms Buttons Images Icons

Reason #4. Extensive list of components

Styling of every single element follows a consistent theme and takes just few minutes. Some of the components pre-styled are:
Dropdowns Button Groups Navigation Bar Breadcrumbs Labels & Badges Alerts Progress Bar And many others.

Reason #5. Bundled Javascript plugins

The components such as drop down menu are made interactive with the numerous JavaScript plugins bundled in the bootstrap package. If a project requires sliders, tabs, accordions, then a developer no longer has to try and test numerous different plugins across the web. Adding these functionalities is just a matter of adding few lines of code.

#### Using Angularjs
Angular is written from the perspective of putting more horsepower under the hood of the HTML code of a web application or site, an approach known in the Angular world as "Directives".

With Angular HTML attributes are automatically connected to functions that perform routine and straightforward actions behind the scenes, thereby eliminating the need for some (or potentially a big chunk) of laborious JS code. 

#### Project Team
Project Leadership for this project was defined to be Adam Horvath, Deepak Gupta and Saurabh Verma. These three Account Level Executives were responsible for producing the response to the Request for Proposal and the integrity of the overall solution.

Jamil Masarweh was identified by Project Leadership as the Product Owner since he holds a technical and business background with over 8 years of experience.

#### Labor Category
- Technical Architect - Sahil Grover
- Usability Design - Austin Fadely  -  Writer Content - Jamil Masarweh
- Frontend Developer - Matt Heim
- Backend Developer - Claude Sutterlin
- Delivery Manager - Girish Ranade 
- Agile Coach - A dedicated resource was not needed for this role, the selected team members are well versed in the tenets of Agile development and daily scrum standup meetings were managed by Girish Ranade, a certified Scrum Master
- Business Analysis - Austin Fadely

Note: In the case of Austin Fadley, he played 2 separate roles as neither was full time and both roles were important to the success of the project.

### Understanding what people need: Human Centric Design
Surveys: Interviews were based on a few short questions with scope for the user to expand on their responses. These surveys were sent to the Project Stakeholders and a few members of the general public. The Survey results helped in identifying and prioritizing requirements. Survey Questions can be found in the Github link:

[https://github.com/AcumenSolutions/acumen-gsa-agile-prototype/raw/master/Agile%20Project%20Artifacts/GSA%20Agile%20Prototype%20Survey.docx](https://github.com/AcumenSolutions/acumen-gsa-agile-prototype/raw/master/Agile%20Project%20Artifacts/GSA%20Agile%20Prototype%20Survey.docx)

Focus Groups: Group participants represented a cross section of the Project Team and Stakeholders - providing a realistic representation of the consumer. With the time limits on the project, these sessions were restricted to short sessions and used brainstorming where all team members were encouraged to add new ideas or add onto a previous one. Examples of ideas from these discussions include:

a) Mobile Application

b) Easy to navigate with minimal keystrokes.

c) Must be intuitive, i.e should not have lots of unnecessary verbiage on the screen 

Scenarios of Use: Allowed team members to provide detailed realistic examples of how users would carry out their tasks with the application. The objective was to provide examples of the use as an aid to understanding and clarifying user requirements and to provide a basis for later usability testing. 

### Design style guide and pattern library
A guide for the web application style and pattern library has been included, and can be found in

### Unit Test and Testing with people
Unit testing has been part of the development effort. Using a test driven development strategy the team wrote test cases to allow testing of each component before releasing the application to the QA team (using $scope). 

AngularJS provides dependency injection for the application XHR requests, which can be mocked, and allowed the developer team to provide abstractions which allows testing the model without having to resort to manipulating the DOM. The test can then assert that the data passed was processed successfully without having to create or look at the state of the DOM or wait for any XHR requests to return data. Functions were tested in isolation using $scope.

The goal was to cover 100% of the developed code with automated tests (using embedded AngularJS scope feature, modular injection and Heroku deployment test. Automated Test here means, testing without 3rd party tools or 3rd party licenses). As code was completed, unit tests were written to validate the functionality of each individual component of the application. 

After unit testing was completed the application was prepared and deployed via Heroku to allow system level or end to end testing by the QA team

Austin Fadely, the lead Usability Designer and Business Analyst, was assigned to develop the testing methodology and approach and then coordinate all testing and application validation activities. 

A test plan, which documents the overall testing strategy was completed and can be found at 

[https://github.com/AcumenSolutions/acumen-gsa-agile-prototype/raw/master/Agile%20Project%20Artifacts/%20Test%20Plan.docx](https://github.com/AcumenSolutions/acumen-gsa-agile-prototype/raw/master/Agile%20Project%20Artifacts/%20Test%20Plan.docx)

Using the acceptance criteria from the user stories, test cases were developed.  From the test cases, detailed step by step test scripts, for the tester team, were created and can be found at : 

[https://github.com/AcumenSolutions/acumen-gsa-agile-prototype/raw/master/Agile%20Project%20Artifacts/%20Test%20Cases%20.xlsx](https://github.com/AcumenSolutions/acumen-gsa-agile-prototype/raw/master/Agile%20Project%20Artifacts/%20Test%20Cases%20.xlsx)

To ensure traceability, each test case was cross-referenced to the User Story ID, which had been assigned in Pivotal Tracker. The testing team was instructed to note any discrepancy whether in the script itself or with the application. A triage process was instituted report items as as following :

a) Is this a duplicate, if so combine 

b) Is this preventing the application from working i.e is this a showstopper 

c) Is this a new request 

d) Is this a "must have" item or a "nice to have" item 

A Link to the GitHub repository listing all the created issues can be found at 

[https://github.com/AcumenSolutions/acumen-gsa-agile-prototype/raw/master/Agile%20Project%20Artifacts/Pivotal_Tracker_Export_of%20all_Issues%20.xlsx](https://github.com/AcumenSolutions/acumen-gsa-agile-prototype/raw/master/Agile%20Project%20Artifacts/Pivotal_Tracker_Export_of%20all_Issues%20.xlsx)

The strategy and agile approach used was to incorporate team feedback and provide a daily deployment - at a minimum - allowing testing and the opportunity for feedback. Depending on the volume of issues or bugs addressed, deployments were sometimes more frequent.

#### Continuous Integration and Monitoring
The build process uses gulp (which is detailed in the open source section above), which is configured to run all tests. Continuous integration was achieved through Heroku, which can automatically deploy the latest build version following a push from the developer's local machine to the remote repository.

Continuous monitoring, which monitors the health of the application and checks for system level and run time errors is a built in feature of Heroku: https://devcenter.heroku.com/articles/production-check#visibility-and-monitoring and also https://devcenter.heroku.com/articles/metrics


### Prerequisites

[Node.js](https://nodejs.org/download/)

[Heroku Toolbelt](https://toolbelt.heroku.com)

[Bower](http://bower.io/)

[Gulp](http://gulpjs.com/)

#### Installation
1. Sign-up for an API key from the [Open FDA API](https://open.fda.gov)
2. Clone the repository `git clone https://github.com/AcumenSolutions/acumen-gsa-agile-prototype.git'
3. Install node dependencies `sudo npm install`
4. Install bower dependencies `bower install`
5. Build all dependencies and static files `gulp build`

#### Local configuration
1. Store your API key locally `export OpenFDAAPIKey=<your api key>`
2. Launch the application `npm start`

#### Heroku configuration
[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

#### Or deploy manually

1. Create a new app on Heroku `heroku create <project name>`
2. Set your API in the Heroku config `heroku config:set OpenFDAAPIKey=<your api key>`
3. Deploy the application to Heroku  `git push heroku master`

### Usage

[Try our hosted demo](https://acumen-gsa-prototype.herokuapp.com)

1. In a web browser navigate to the application url ([http://localhost:3000](http://localhost:3000) or [http://`<project name>`.herokuapp.com](http://`<project name>`.herokuapp.com))
2. Select a recall category
3. Choose a state for which you'd like to view recalls
4. (Optional) Enter product keywords to find a particular recall.
5. Click Search
6. Select a result to view detailed information about the recall

### Runing Unit Tests

Run the command `gulp test` to run both client and server side tests.
If you want to run just the client tests, run `gulp test:client`, and for just server tests run `gulp test:server`.

### Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request

### License

The MIT license 

Copyright (c) 2015 Acumen Solutions, Inc