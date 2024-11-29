#AngryBirds

##Project Overview
This project is an implementation of a 2D physics-based game called Angry Birds. The game was developed using libGDX, a Java game development framework. The game follows object-oriented programming (OOP) principles, ensuring that the code is modular and easy to maintain.
This project implements serialization to enable saving and resuming the game state. Serialization allows the game to store the current state of a level, including the positions of objects, scores, and other relevant data, in a file. This data can later be deserialized to resume the game from the saved state.

The primary goal of this project is to replicate the core mechanics of Angry Birds at a very basic level, while introducing features, such as multiple levels with varying difficulties, a variety of birds with different abilities, and an engaging physics-based environment that interacts with the player's actions.


###Key Features
**Multiple Levels**: The game includes a variety of levels with increasing complexity and difficulty. Each level has its own set of obstacles and challenges, keeping the game fresh and exciting.

**Bird Types**: There are different types of birds provided at every level.

**Physics-Based Gameplay**: The game simulates realistic physics using libGDX’s physics engine. Birds fly in arcs, collide with structures, and react with realistic forces.

**Pause and Resume**: The game allows players to pause and resume their progress. When the game is paused, the current level, bird positions, and any destroyed objects are saved. The player can then return to the game later and pick up where they left off.

###Gameplay Instructions
**Objective**: The primary goal of the game is to defeat the pigs by launching birds at their structures. Players must use a catapult to launch the birds at various blocks, causing them to collapse and defeat the pigs hiding inside or on top of the structures.

**Launching Birds**: Players drag to aim and release to launch a bird from a catapult. The bird follows a projectile path and collides with the environment, interacting with blocks and pigs. Players need to carefully choose the angle and force of the launch for maximum effect.

###Project Structure
**AngryBirds**: The main class responsible for initializing the game and managing different screens. It handles transitions between screens, such as switching from the start screen to the level screen and from one level to the next.

**Level1, Level2, Level3**: These are different levels in the game, each having a unique setup with various obstacles and pigs. The levels are structured as Screen classes in libGDX, with each level containing its own blocks, birds, and physics objects.

**Bird**: Represents the bird objects in the game. The Bird class is the parent class for all bird types, and it contains methods for movement, collision detection, and interaction with the environment. Different bird types (e.g., red, yellow, or explosive birds) inherit from the Bird class.

**Pig**: Represents the pig enemies. Pigs are placed on top or inside structures, and their objective is to avoid being hit by birds. When a pig is hit, it is removed from the game, and the player earns points.

**Block**: Represents the destructible blocks that make up the environment. These blocks are of different types (e.g., wood, stone, glass). Blocks can be destroyed when impacted by a bird, and their state is updated during the game.

**Catapult**: The mechanism used to launch the birds. The Catapult class handles the bird-launching logic, including calculating the trajectory and applying force to the bird when it is released.

###How to RUN
Run the Lwjgl3Launcher.java file and enjoy playing the game

**PATH**:  C:\Users\L2\IdeaProjects\bitbrawlers\lwjgl3\src\main\java\com\bitbrawlers\angrybirds\lwjgl3\Lwjgl3Launcher.java

###Online Sources Referenced
-https://libgdx.badlogicgames.com/ 
-https://angrybirds.fandom.com/wiki/Angry_Birds_Wiki 
-https://junit.org/junit5/ 
-https://libgdx.com/wiki/start/project-generation

###GITHUB LINK
https://github.com/palak363/AngryBirds_Deadline3/tree/main
