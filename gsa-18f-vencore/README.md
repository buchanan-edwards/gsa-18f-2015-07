**Vencore Prototype URL**:  http://agiledemo.vencore.com  

#Vencore’s Approach – Pool 3

##Prototype Summary
**Product Vision</b>**:  _“Inform consumers about product recalls in their area to increase public safety and awareness”_. 

Consumers who purchase food, devices, and drugs may be impacted by product recalls. In an effort to increase consumer’s awareness of product recalls distributed in the consumer’s geographical area of interest, our product delivers an easy and intuitive UI for multiple device types while delivering valuable public safety information about product recalls. 

Our application is hosted at http://agiledemo.vencore.com and is accessible via desktop browser, tablet or mobile devices.  Additionally Vencore delivered a RESTful API service that aggregates data from the OpenFDA API for our application to consume, and an Android application so the user can query on the go.

##Narrative for Vencore’s Approach

Vencore is well-positioned to contribute to 18F’s Agile initiative. For over 18 years, we have supported GSA PBS with innovative software development solutions, and we have invested heavily over the past 3 years to improve and optimize our approach.  Vencore management is confident this approach is well suited for [RFQ BPA for Agile Delivery Services - 4QTFHS150004](https://pages.18f.gov/ads-bpa/assets/ADS_RFQ_Final.pdf) and allocated a set amount of funds to invest in responding ([Play5](https://playbook.cio.gov/#play5)). 

We have assembled an experienced multi-disciplinary agile team for this initiative and assigned the Product Manager to be accountable for the prototype ([Play6](https://playbook.cio.gov/#play6), [Play7](https://playbook.cio.gov/#play7)).  Our Product  Manager worked with representative consumers of the OpenFDA API data during the inspiration phase of our human-centered design approach to generate innovative ideas and create an intuitive and valuable product to meet their needs ([Play1](https://playbook.cio.gov/#play1), [Play3](https://playbook.cio.gov/#play3)).  

The Agile Coach, also serving as the Scrum Master (Delivery Manager), led the team in creating a narrative using a story mapping technique that laid out a horizontal flow of user activities and features for the application.  The team then broke the features down into smaller stories through successive vertical slices directly underneath the features in preparation for Sprints.  Both functional and non-functional stories, acceptance criteria and dependencies were identified, created and prioritized in the product backlog.  From this depth, horizontal slices of the story map were created to realize specific goals and create a Minimal Viable Product (MVP) ([Play2](https://playbook.cio.gov/#play2)).   All goals and user stories were captured in our Agile Application Lifecycle Management tool (Microsoft Team Foundation Server). 

Concurrent with the Product Manager defining the product vision and key success factors, the delivery team (developers, testers, analysts, DevOps engineer, and architect) began the process of familiarizing themselves with the OpenFDA APIs, identifying modern open-source technologies and setting up a collaborative workspace and development environment for the prototype ([Play8](https://playbook.cio.gov/#play8), [Play13](https://playbook.cio.gov/#play13)). The team successfully installed the architecture and tools required ([Node.js](https://nodejs.org/), [Express](http://expressjs.com/), [JSON](http://json.org/), [AngularJS](https://angular.io/), [HTML5](http://www.w3.org/TR/html5/), [Bootstrap](http://getbootstrap.com/), [Google Charts](https://developers.google.com/chart/interactive/docs/gallery/geochart), [NodeJsScan](http://opensecurity.in/nodejsscan/) for security, [ACCVerify](http://warc.calpoly.edu/accessibility/accverify.html)) ([Play11](https://playbook.cio.gov/#play11)) in the [DevOps environment](https://github.com/vencoreinc/18FAGILEPROTOTYPE/tree/master/development).

Our DevOps engineer extended our existing DevOps environment that uses Git for source control, [Jenkins](https://jenkins-ci.org/) for Continuous Integration server, [Grunt](http://gruntjs.com/) for builds and invoking unit tests, Rest API unit test cases are written using [Mocha](http://mochajs.org/) framework, [Docker](https://www.docker.com/) for containerization, and [Protractor](https://angular.github.io/protractor/) framework built on [Selenium](http://www.seleniumhq.org/) for integration testing, and [PhantomJS](http://phantomjs.org/) a headless browser used to support integration testing ([Play10](https://playbook.cio.gov/#play10)).  We use the [AWS](http://aws.amazon.com/) cloud EC2 (IaaS) to deploy a docker container based on a Dockerfile ([Play9] (https://playbook.cio.gov/#play9), [Play11](https://playbook.cio.gov/#play11)).  Once deployed, the necessary source code is copied and the server is started exposing the application on a given port.  Our team leverages the AWS provided APIs for automation and monitoring, an approach which enables rapid provisioning and deployment. 

Vencore’s Agile process for this prototype challenge was based on ScrumXP and Lean practices. We applied Work in Progress (WIP) limits on work queues and resources to maximize throughput and reduce bottlenecks.  Our proven software development methodology incorporates the flexibility and agility of Scrum techniques and concepts’, including continuous software builds and automated testing while allowing for continual reassessment of Product Manager needs and priorities. 

We delivered the prototype release through multiple one-day Sprint iterations ([Play4](https://playbook.cio.gov/#play4)). These Sprints included all necessary development and testing tasks required to complete the story and meet the Definition of Done (DoD) as defined by the team.  The DoD exit criteria established included “working software”, i.e., code that was integrated, fully tested and potentially shippable.  During the Sprints, we held morning and afternoon 5 minute stand-ups to ensure we were on track and address impediments.  At the end of the day there was a Sprint review, retrospective and a planning meeting for the next Sprint.

During ideation, our human factors expert used Vencore’s design guidelines and lean UI/UX practices working with our Product Manager to protosketch the layouts and visual representation of our target website and mobile app.   This was an iterative process as subsequent feedback was gathered from our representative consumer group, the UI and flow was refined and modified ([Play12](https://playbook.cio.gov/#play12)).  Vencore ran the prototype through [ACCVerify](http://warc.calpoly.edu/accessibility/accverify.html) for 508 compliance.

Our agile approach is built on the assumption that changes (e.g., new requests, discovery, and feedback) are a fact of development that necessitate adjustments and require the team to learn and pivot.   During the prototype effort, such changes were required.  Having small independent stories enabled us to discover and refine early and often, giving us the flexibility to quickly adapt and respond to changes and prioritization. 

A discovery made when aggregating data from three separate OpenFDA recall API calls for drugs, devices, and food was the counts for recalls were not consistent.  Our application allows the consumer to filter results on a single page by product type, date range, search term, and product distribution states.  Our limited focus group for the prototype liked our product concept and implementation, including the UI/UX.  But before investing more time and money to solve the backend logic for the counts, we decided to get feedback from a larger target audience, since approximately 70% of software developed is rarely or never used.  That costs money, time and lost opportunity to create more valuable products.  By taking the prototype to a larger audience, we can validate the products value by soliciting feedback through surveys, logging and analytics.  Our process encourages experimenting, learning, fail fast, and then pivot or preserve.

##Evidence for Vencore’s Approach  
a. [Assigned one leader and gave that person authority and responsibility and held that person accountable for the quality of the prototype submitted.](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-a)  
b. [Assembled a multidisciplinary and collaborative team that includes at a minimum five of the labor categories limited to the Design Pool, Development Pool categories to the full stack (i.e., Design and Development) as quoted in Attachment C. The quoter’s proposed mix of labor categories and level of effort for its working prototype, as reflected in Attachment C, shall be evaluated to assess the quoter’s understanding and capability to supply agile delivery services.](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-b)  
c. [Understand what people need, by including people in the prototype development and design process](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-c)  
d. [Used at least three “human-centered design” techniques or tools](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-d)  
e. [Created or used a design style guide and/or a pattern library](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-e)  
f. [Performed usability tests with people](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-f)  
g. [Used an iterative approach, where feedback informed subsequent work or versions of the prototype](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-g)  
h. [Created a prototype that works on multiple devices, and presents a responsive design](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-h)  
i. [Used at least five modern and open-source technologies, regardless of architectural layer (frontend, backend, etc)](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-i)  
j. [Deployed the prototype on an Infrastructure as a Service (IaaS) or Platform as Service (PaaS) provider, and indicated which provider they used.](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-j)  
k. [Wrote unit tests for their code](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-k)  
l. [Setup or used a continuous integration system to automate the running of tests and continuously deployed their code to their IaaS or PaaS provider.](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-l)  
m. [Setup or used configuration management](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-m)  
n. [Setup or used continuous monitoring](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-n)  
o. [Deploy their software in a container (i.e., utilized operating-system-level virtualization)](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-o)  
p. [Provided sufficient documentation to install and run their prototype on another machine](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-p)  
q. [Prototype and underlying platforms used to create and run the prototype are openly licensed and free of charge](https://github.com/vencoreinc/18FAGILEPROTOTYPE/wiki/Evidence-q)    

##U.S. Digital Services Playbook which Vencore applied in our approach 

1.	[Understand what people need] (https://playbook.cio.gov/#play1)
2.	[Address the whole experience, from start to finish](https://playbook.cio.gov/#play2)
3.	[Make it simple and intuitive](https://playbook.cio.gov/#play3)
4.	[Build the service using agile and iterative practices](https://playbook.cio.gov/#play4)
5.	[Structure budgets and contracts to support delivery](https://playbook.cio.gov/#play5)
6.	[Assign one leader and hold that person accountable](https://playbook.cio.gov/#play6)
7.	[Bring in experienced teams](https://playbook.cio.gov/#play7)
8.	[Choose a modern technology stack](https://playbook.cio.gov/#play8)
9.	[Deploy in a flexible hosting environment](https://playbook.cio.gov/#play9)
10.	[Automate testing and deployments](https://playbook.cio.gov/#play10)
11.	[Manage security and privacy through reusable processes](https://playbook.cio.gov/#play11)
12.	[Use data to drive decisions](https://playbook.cio.gov/#play12)
13.	[Default to open](https://playbook.cio.gov/#play13)


