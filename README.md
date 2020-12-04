# **Software Maintenance Course Work**
*Frogger game maintenance*

![logo](https://user-images.githubusercontent.com/51990168/101153998-b87a2780-3657-11eb-9875-6c445ad04f40.png)

## **Improvements made**
The code are fully restructured according to MVC, where the panes are located in the *view* package, the actors in the *model* package and the button controllers in the *controller* package. The level and highscore information are located in the *data* folder, and the images and audio in the *media* folder. 

**Changes made:**
* Moved the *key events* from *Animal.java* to *AnimalController.java*.
* Stored all level information into csv files.
* Added panes for *scoreBoard, MainMenu and Info pages* and interactive buttons to direct the pages.
* Added longer levels, which will scroll according to the y position of the frog.
* Improved graphics and animation on every actors and background.
* Improved efficiency in the *setNumber* method to prevent lag.
* Made it into a maven project and integrated maven wrapper, dismissing the requirement to install maven in the user's computer.

## **How to compile and run**
1. Visual Studio Code
* Open the project folder in Visual Studio Code.
* Open terminal and type "mvnw.cmd clean install" to compile.
* Type "mvnw.cmd exec:java" to run.
2. Eclipse
* Import the project in Eclipse.
* Click the Run drop down menu and click Run Configurations.
* Click Maven Build and then click New launch configuration.
* Fill in Base directory as "${project_loc:COMP2042.CW.hcygv1}".
* Fill in Goals as "clean install".
* Create another new launch configuration and fill in Base directory same as above.
* Fill in Goals as "exec:java".

*Note: The first launch configuration is for compiling and the next one is for running the program. Make sure you have maven extension installed in eclipse.*

***Student ID: 20209463***
