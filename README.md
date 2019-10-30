# SeleniumFramework
The comprehensive prototype of automation test framework. 
This framework is a baseline and can be easily extended to automate any industry level software project.

The framework uses technologies like Selenium WebDriver with Java, TestNG test framework.
Build tool: Maven.
Integrated with Jenkins.
Captures test results, screenshots of failed tests.

Can be extended to include Jenkins reports which is of great advantage for stakeholders.

The main advantage of the framework is it has a master excel sheet which contains all the test case names with their tagged group names.
This gives great benefits to the end users who want to run the automated tests.
- They can easily schedule the test group which they want (ex: smoke tests, regression tests).
- They can re-run failed tests easily.

The TestNG xml file is dynamically generated and hence it avoids any QA to dig into the code or technical aspects of the scripts.
This helps any manual tester or any other non technical person to easily manipulate the master excel sheet and schedule the tests which they want to run.
