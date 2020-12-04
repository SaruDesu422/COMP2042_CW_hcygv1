# **Software Maintenance Course Work**
## *Frogger game maintenance*
![Image] (https://github.com/SaruDesu422/COMP2042.CW.hcygv1/tree/master/media/images/logo.png)

## **How to compile and run**
1. Visual Studio Code
* Open the project folder in Visual Studio Code
* Open terminal and type "mvnw.cmd clean install" to compile
* Type "mvnw.cmd exec:java" to run
2. Eclipse
* Import the project in Eclipse
* Click the Run drop down menu and click Run Configurations
* Click Maven Build and then click New launch configuration
* Fill in Base directory as "${project_loc:COMP2042.CW.hcygv1}"
* Fill in Goals as "clean install"
* Create another new launch configuration and fill in Base directory same as above
* Fill in GOlas as "exec:java"
*Note: The first launch configuration is for compiling and the next one is for running the program. Make sure you have maven extension installed in eclipse*