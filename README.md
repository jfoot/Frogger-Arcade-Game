# Frogger Arcade Game - psyjpf
### Tested On: Lab Machines and personal laptop (running Java JDK 10.0.1 with FX)
### Build Scripts: Maven (infomation inside pom file)
## Summary Report (500 words):
### Key Refactoring’s
-	Restructured program into packages, with a resource and test folder
-	Removed all constant values, (e.g. water level); so you only need to change a value once.
-	Renamed classes to be more intuitive (animal to player)
-	Pulled up all methods in the Log, Obstacle, Turtle and Wet Turtle, abstracting the logic to create a new class called “PanningActor”. This is because, they all shared the same functionality of needing to move in the X direction across the screen looping back around continuously.
-	Separated out collusion code into separate methods and reworked it, if the collision is with a “safe” object (eg log) we move with it, else we die. To promote reuse of methods and single responsibility.
-	Removed Act from Actor, making it an interface, to stop classes inheriting unneeded functions enforcing Liskovs Substitution Principal.
-	Restructured main, MyStage and world to better distribute single responsibilities. In doing so created a “Game” class, which is responsible for the setting up and playing of the level.
-	Put the level generation code into its own class, sub-classed from “GenerateLevel” to allow for more levels to be added to the game with ease.
-	Reworked turtle Act method, to use the value of the division as the index for the animation frame.
-	End goals generation abstracted into a function, you pass the number of ends you want, and it will equally space them out for you; allowing for levels with a different number of ends.
-	Fixed Background image.
### Design Patterns
-	MVC- added into the Scoreboard feature, as this allowed easy testing of the backend logic of reading and writing scores, decoupled from the view.
-	Observer – The Player object needs to tell the Game when it dies or gets a new score, so it can check for the end of the level or update the GUI. The observer pattern allows the Player to not need to understand the exact implementation of Game, only that it needs to be notified of change. Originally the program would check every animation tick updating the score, this was unneeded.
-	State – The Player class was doing too much. To adhere to single responsibility, I made the death and walking logic its own class. Death and Walking fitted into clear states: alive, death by car or water and forwards, backwards, left and right walk. The state pattern allowed for the Players walking or health logic to change at run time, with it being contained within a specific state for the class; allowing for easier extension in future and easier understanding.
-	Singleton – MyStage contains the level and stage/window. The user can only ever play one level at one point in time, a singleton ensures that there is never more than one instance.
### New Addition
-	Levels – two new levels added.
-	Permeant High Scores – Scores are saved and parsed as JSON files to remember the values between program instances.
-	Lives – Lives count can be controlled by user to alter difficulty, when run out, game over.
-	Start Screen
-	Help Screen
-	Music Toggle
-	JUnit Tests

