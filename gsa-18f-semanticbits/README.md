http://ec2-54-175-144-58.compute-1.amazonaws.com:8080/LABEL/

The above URL is a hyperlink to the publicly available instance of our prototype.

# Description #

SemanticBits leveraged an Agile, iterative approach to implementing a prototype for the GSA Agile BPA.  Our first step during the Inception Phase was to identify a key need of our user community.  Here, the Product Manager identified that the key issue is the ability to quickly and accurately find information about drug labels through a government site.  In particular, users should be able to either search by name/keyword or upload a photo of a drug label from their smartphone to initiate a search.  

After Inception, we began the Elaboration Phase where we focused on getting the project team mobilized, implementing the baseline architecture, and coming up with the initial user experience.  Once the problem statement was defined, reviewed, and approved, our User Interaction Designer created wireframes with Balsamiq for a web-based, mobile-enabled application to perform this function.  In parallel, the DevOps Engineer identified the target environment and wrote the appropriate Vagrant and Chef scripts for standing up the various environments.  Our Business Analyst also began creating Epics and User Stories in Jira, as well as User Acceptance Tests (UATs).  The Usability Engineer identified an overall design approach and exchanged the design guide/style patterns with the team.  The Architect put together a baseline architectural approach using the concurrent 4+1 view model.  During this process, the Delivery Manager set up daily scrums, the code repository on GitHub, Jira for capturing Epics/User Stories/Tasks/Bugs, Confluence for capturing documentation, and Crucible for code reviews.

The wireframes were presented to users from the community, we gathered feedback, and that feedback was incorporated into the wireframes.  These wireframes were linked to User Stories.  During this time, elaboration phase development was commencing, where the baseline architecture was implemented and proven.  Here, we were remixing data from the label portion of the OpenFDA APIs. We stood up a basic REST-like service that provides our business-specific queries.  The riskiest aspect of the architecture was parsing an image of a barcode, so this was prototyped using ZXing.  In addition, the baseline pages were implemented using Grails and Bootstrap to perform some of these queries.  We prototyped an HTML5 component leveraging CSS3 for capturing an image from a smartphone to ensure that this critical feature is possible.  The Business Analyst began fleshing out named User Stories by writing their text and adding scenarios.

The environment was decided early on - towards the end of inception and beginning of elaboration.  Amazon Web Services (AWS) was decided upon because of its low cost, ease of use, and our extensive experience with it.  The DevOps Engineer implemented Chef and Vagrant scripts to stand up our environments: development (DEV), continuous integration (CI), and quality assurance (QA).  Jenkins was incorporated into the CI environment scripts, and automated deployment and testing was initiated using Ant, Vagrant, Chef, Geb, and Selenium.  Everyone on the team received emails when CI failed, and the issue was fixed immediately.  We automated unit and integration tests using JUnit and Geb/Selenium.  When a bug is encountered, a test is written that fails to validate the bug, and then the issue is resolved.  Manual tests were performed against functionality on the DEV instance before sprint end and the QA instance after sprint end.  Typically we document tests in Jira using Zephyr and link them to the User Stories for which they cover.  Ad hoc test cycles are used for validating functionality within a sprint before a User Story is marked as resolved, and a test cycle for each sprint captures tests cumulatively to perform regression testing.

As the Construction Phase began, we moved into daily mini-sprints to demonstrate our iterative approach.  Here we ended each day with a sprint meeting that combined the scrum update, the sprint retrospective, and sprint planning meetings.  User stories were iteratively fleshed out, approved, and implemented.  Mockups were created by the Visual Designer from wireframes and presented to the Product Manager.  After two rounds of feedback, the Usability Tester presented the mockups to our user community for feedback.  This was documented and incorporated into a final revision of mockups.  These mockups were linked to User Stories, and the Visual Designer designed images and CSS to be incorporated by the developers.  The Architect refined the architectural approach as technical challenges were uncovered, such as the ZXing not returning specific error codes when decoding failed.  Developers iterated over the web interface and backend REST-like services.  Query functionality was enhanced, the result pages better formulated, and the label details laid out.  

The Delivery Manager worked closely with the Product Manager to prioritize features.  Certain features were down-prioritized and a subset of those were dropped altogether.  For example, advanced search functionality through entering a hashtag (pound symbol) had a low priority but made it into the release.  On the other hand, supporting pages like “About”, “Contact”, and “Help” were dropped because they were deemed to add the least value.  In other cases, tradeoffs were made in effort versus functionality.  The user experience design called for type-ahead after a hashtag is entered for advanced search.  This proved to be cost-prohibitive (high effort) to implement, so an auto-completer was incorporated instead.

Three releases were made: alpha, beta, and production.  The alpha release was an internal release designed as a milestone to make sure the team was on track and the features were meeting the expectations of the Product Manager.  The beta release went through a usability test administered by our Usability Engineer.  Each user in our beta user group was provided the application, and the Usability Engineer observed the users as they used the application.  We noted down any issues that the users ran into, as well as suggestions that the users provided.  These were then reviewed by the Interaction Designer and Visual Designer, who along with the Delivery Manager and Business Analyst made a recommendation to the Product Manager.  The Product Manager approved a subset of the recommended changes, which were incorporated into the final release.  User Acceptance Testing (UAT) is an ongoing process from the first release.  As more functionality is rolled out in the last half of the project, UAT takes a more central focus.  Once we had a release, our users were able to put their hands on the application and report issues.

The Transition Phase typically involves handover of all work product to the customer, including production deployment, user training, handover of documentation, and kickoff of operations and maintenance.  For this project, the focus of Transition was moving the application to production, polishing off documentation, and finalizing evidence for the Agile BPA submission.

# Evidence #

All documentation can be found in [our github pages](http://semanticbits.github.io/label-priv/).

* a. [One leader](http://semanticbits.github.io/label-priv/Leader.html)
* b. [Multidisciplinary team](http://semanticbits.github.io/label-priv/Resources.html)
* c. [What people need](http://semanticbits.github.io/label-priv/Users.html)
* d. [Human centered design](http://semanticbits.github.io/label-priv/Human-centered%2BDesign.html)
* e. [Style guide](http://semanticbits.github.io/label-priv/UI%2BStyle%2BGuide.html)
* f. [Usability tests](http://semanticbits.github.io/label-priv/Users.html)
* g. [Iterative approach](http://semanticbits.github.io/label-priv/Schedule.html)
* h. [Responsive prototype](http://ec2-54-175-144-58.compute-1.amazonaws.com:8080/LABEL/)
* i. [Modern open-source technologies](http://semanticbits.github.io/label-priv/Configuration%2BManagement.html#ConfigurationManagement-Third-PartySoftware)
* j. [IaaS](http://semanticbits.github.io/label-priv/Architecture.html#Architecture-PhysicalView)
* k. [Unit tests](http://semanticbits.github.io/label-priv/Testing%2BProcess.html#TestingProcess-Unit)
* l. [Contiuous integration](http://semanticbits.github.io/label-priv/Environments.html#Environments-ContinuousIntegration(CI))
* m. [Configuration management](http://semanticbits.github.io/label-priv/Configuration%2BManagement.html)
* n. [Continuous Monitoring](http://semanticbits.github.io/label-priv/Environments.html#Environments-ContinuousMonitoring)
* o. [Deployed to container](http://semanticbits.github.io/label-priv/Environments.html#Environments-AmazonWebServices)
* p. [Installation documentation](http://semanticbits.github.io/label-priv/Installation%2BGuide.html)
* q. [Licensing](http://semanticbits.github.io/label-priv/Configuration%2BManagement.html#ConfigurationManagement-Third-PartySoftware)

# Known Issues #

* OpenFDA label API contains only a few UPC codes of known products and they don't always match to the UPC codes found on over the counter labels
* Clicking on navigation links on label details page doesn't always take user to the correct location in the document
* Mobile interface of label details does not have a sticky header
* Scrolling a small amount on label details shows text between label title and header
