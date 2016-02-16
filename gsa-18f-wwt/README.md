GSA Prototype: FDA.Ninja
========================

Check out our working prototype at http://fda.ninja/.

## Description

[FDA.ninja](http://fda.ninja/) allows users to view medication information by name and manufacturer providing results such as adverse reactions, warnings, usage and indications, and potential side effects. 

## Project Approach

World Wide Technology Asynchrony Labs assembled a team to respond to the GSA RFQ and build a prototype application using our proven [Agile methodologies](https://github.com/dwafler/gsa_2015/blob/master/docs/agile-practices.md), in line with the [U.S. Digital Services Playbook](https://playbook.cio.gov/). We assigned a Product Manager to lead the team and be accountable for the quality of our delivered prototype, as well as two Front-End Web Developers, a Back-end Web Developer, a Dev Ops Engineer, a Visual Designer, a Writer/Content Designer/Content Strategist, an Agile Coach, and a pseudo "Product Owner." 

To help us understand what people need or could use from our prototype, we performed an "iteration zero" [Kick-off Ideation Session](https://github.com/dwafler/gsa_2015/tree/master/docs/pictures/kick-off-ideation-session), where we identified our target users and a primary [use case](https://github.com/dwafler/gsa_2015/tree/master/docs/ux/use-case.md), planned an initial feature set, and did some preliminary wireframing. From there, we wrote out stories, set up our development and testing environment, and began development. 

Throughout the course of the project, our team used human-centered design techniques and tools to create a usable, simple, and intuitive system. This included performing [user interviews](https://github.com/dwafler/gsa_2015/blob/master/docs/ux/user-research.md) and [usability testing](https://github.com/dwafler/gsa_2015/tree/master/docs/ux/usability-testing), as well as creating [wireframes](https://github.com/dwafler/gsa_2015/tree/master/docs/ux/wireframes), a [persona](https://github.com/dwafler/gsa_2015/blob/master/docs/ux/persona.pdf), a [task flow diagram](https://github.com/dwafler/gsa_2015/blob/master/docs/ux/task-flow.pdf), and an application [style guide](https://github.com/dwafler/gsa_2015/blob/master/docs/ux/style-guide.png).

When we learned about the extended timeframe for the project, we decided it would be appropriate to take a little time to have a [mini-retrospective](https://github.com/dwafler/gsa_2015/blob/master/docs/retrospective.md). We used this meeting to talk about what was and wasn't going well on the project, and then transitioned into a second ideation/planning session.

Throughout our development process, we used [iterative software development techniques](https://github.com/dwafler/gsa_2015/blob/master/docs/iteration-example.md), and both a [physical](https://github.com/dwafler/gsa_2015/tree/master/docs/pictures/kanban-physical) and [electronic Kanban](https://github.com/dwafler/gsa_2015/tree/master/docs/pictures/kanban-trello) (Trello, which mirrored our physical board) to track work. Because we practice "sustainable pace," we worked normal, Monday through Friday business hours. We did not work nights or weekends to complete our prototype. We did, however, use pair programming, [unit testing](https://github.com/dwafler/gsa_2015/tree/master/spec), test-driven development, automated testing, and continuous integration. You can watch a normal "day in the life" in a time-lapse video [here](https://github.com/dwafler/gsa_2015/tree/master/docs/gsa-team-day-in-the-life-day-5.mp4).

## Technical Details

Our prototype is written in Ruby. As per instructions, it is provided as open-source (under the MIT License) and is built on open source technologies such as Docker, Rails, Jenkins, Foundation, JQuery, and Font Awesome (for a more complete list, see the [Gemfile](https://github.com/dwafler/gsa_2015/blob/master/Gemfile)). We designed it using a responsive layout so that it works on multiple devices and form-factors (we've tested it on PCs, tablets, and phones). 

We deployed our prototype using Amazon Web Services (AWS), which also supplies some [monitoring](https://github.com/dwafler/gsa_2015/blob/master/docs/monitoring/status-check-alarm-example.txt) (AWS Cloud Watch). For continuous security monitoring, we used [Nessus](https://github.com/dwafler/gsa_2015/blob/master/docs/monitoring/nessus-continuous-monitoring.md). For continuous integration, automation, and metrics, we used [Jenkins](https://github.com/dwafler/gsa_2015/tree/master/docs/jenkins), while we used [Docker](https://registry.hub.docker.com/u/jasonmayer/drug1/) for our configuration management and container deployment. We leveraged the Labeling (`api.fda.gov/drug/label`) and Adverse events (`api.fda.gov/device/event`) APIs in our prototype.

## Installation and Local Deployment

Instructions for pulling down the repo and running the app locally on your box can be found in [docs/install-run.txt](https://github.com/dwafler/gsa_2015/blob/master/docs/install-run.txt).





