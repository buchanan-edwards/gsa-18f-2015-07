Agile Practices
===============

_Note: while we have split these brief descriptions of our practices into separate sections, these distinctions are made here more for organizational clarity than to be truly descriptive or representative of the way we think about software development, as no one piece could function properly without the other(s). Design, development, and quality assurance are all important pieces of our process throughout the entirety of a project's life-cycle._

## Software Development Practices

We employed **Agile Software Development Methodology**, which is an incremental software development that places the emphasis on empowering people to collaborate and make team decisions in addition to continuous planning, continuous testing, and continuous integrations. We used Story Writing, Kanban, Test Driven Development, and Pair Programming during the course of the software development to ensure an extremely high rate of project success and customer satisfaction.

We employed **Kanban** boards to manage our work with an emphasis on just-in-time delivery without overloading the team members. The process, from definition of a task to its delivery to the customer, was visually displayed in the team area (using physical tack-boards or TV-displayed electronic Kanban boards, such as Trello) for participants to see and for team members to pull work from a queue. 

At daily **stand-ups** (short team meetings - which you can see in the [video](https://github.com/dwafler/gsa_2015/blob/master/docs/gsa-team-day-in-the-life-day-5.mp4)), the team gave status updates on the work in development, which is clearly displayed on the Kanban board, and discusses any barriers they may have so that they can be solved by the team as a whole. In most projects, the team participates in weekly or bi-weekly retrospectives, where the team reflects on what happened in the previous iteration (a week's or two week's worth of work) and identifies actions for improvement going forward. For this project, we had a [mini-retrospective](https://github.com/dwafler/gsa_2015/blob/master/docs/retrospective.md) instead, which better aligned with the needs and constraints of the project. 

Further detail about some of our development strategies and methods can be found below:

### Pair Programming

We utilized the practice of pair programming. **Pair programming** is an agile software development technique in which two programmers work as a pair together on one workstation.  One, the driver, writes code while the other, the observer, pointer, or navigator, reviews each line of code as it is typed in. This approach has significant benefits to both quality and maintainable code base by providing real-time code review and an extra set of eyes on any given coding problem.

### Test-driven Development (Unit Testing)

We created tests to validate whether each discrete unit of code will work as intended. **Test-driven Development** is a software development process that relies on the repetition of a very short development cycle: first one developer in the pair writes an (initially failing) automated test case that defines a desired improvement or new function, then the pair works together to produce the minimum amount of code to pass that test. After there is enough code/functionality to make it practicable, the developers then refactor the new code to acceptable code standards. 

### Version/Source Control

We used software version/source control (Git via GitHub) to facilitate better collaboration as well as transparency into the development of the project over time, enabling us to backtrack if necessary to identify the source of any problems that may have arisen. Git allows us to have multiple pairs working on the same code base at the same time, after which we "merged" their changes back into the master version. This extra step provided another layer of code review, where the developers who did the merge had the opportunity to take yet another look at the code to make sure it meets its requirements for both functionality and coding standards.

Quality Assurance Practices
===========================

The quality assurance practices employed for this project (as well as all our project engagements) are designed to prevent bugs, not just find and fix them. Our developers practice "pair programming" to catch errors at the keyboard and ensure  a uniform, maintainable approach to architecture and software coding standards. We embed QA personnel, whom we call "quality advocates" (because their job is to advocate for quality throughout the life-cycle of the project, not just as "assurance" at the end), into each of our development teams so that they can help facilitate quality throughout the project. For this project, one of the Writers/Content Designers/Content Strategists help serve in the QA role.

Further detail about some of our QA strategies and methods can be found below:

### Automated Testing

We employed automated tests to confirm immediately that any new code did not adversely impact features already completed. The tests were written around specific areas of functionality to ensure that they both met the acceptance criteria for user stories as well as function as a whole. 


### Continuous Integration 

Continuous integration, when combined with automated testing, ensures that a system works exactly as it’s designed to work after each and every change. Customer acceptance tests not only guarantee the code works properly, but also verify that the individual business functions of the system work correctly. By continuously performing integration testing and continuously building the applications, we were able to both ensure quality as well as provide our pseudo-users with live, working software (i.e., current-working-state versions) at virtually any time during the project.


## User Experience/Design Practices

Our success with delivering the best possible functional design and user experience (UX) stems from two primary areas: skilled designers and environment. Each member of our design/user experience team (including the Visual Designer who worked on this project) goes through a stringent certification program for usability analysis and interaction design. Each designer is responsible for introducing elements of the user experience process such as card sorting, task flow analysis, prototyping, and usability testing to every project. 

The Visual Designer for this project was embedded into the project team, which ensured that good design and user experience were woven into the fabric of the application from start to finish. We generally employ any number of UX strategies and methods, such as the [user-interviews](https://github.com/dwafler/gsa_2015/blob/master/docs/ux/user-research.md) and [usability testing](https://github.com/dwafler/gsa_2015/tree/master/docs/ux/usability-testing), [wireframes](https://github.com/dwafler/gsa_2015/tree/master/docs/ux/wireframes), the [task-flow diagram](https://github.com/dwafler/gsa_2015/blob/master/docs/ux/task-flow.pdf), and application [style guide](https://github.com/dwafler/gsa_2015/blob/master/docs/ux/style-guide.png) for this project, to ensure that our designs are not only functional and easy to use, but also meet customer and user expectations and needs.