# Angry Birds

## Project Overview
This project is an implementation of a 2D physics-based game called *Angry Birds*. Developed using **libGDX**, a Java game development framework, the game follows object-oriented programming (OOP) principles, ensuring modular and maintainable code.

It includes **serialization** to enable saving and resuming the game state. Serialization stores the current state of a level, including object positions, scores, and other relevant data, which can later be deserialized to resume gameplay seamlessly.

The primary goal is to replicate the core mechanics of Angry Birds at a basic level while introducing features such as:
- Multiple levels with varying difficulties
- A variety of birds with different abilities
- An engaging physics-based environment that interacts dynamically with the player's actions

---

## Key Features

- **Multiple Levels:**  
  Various levels with increasing complexity and difficulty. Each level presents unique obstacles and challenges to keep the gameplay fresh and exciting.

- **Bird Types:**  
  Different types of birds are provided at every level

- **Physics-Based Gameplay:**  
  Simulates realistic physics using libGDX’s physics engine. Birds fly in arcs, collide with structures, and react to realistic forces.

- **Pause and Resume:**  
  Save and resume game progress. The current level, bird positions, and destroyed objects are saved when paused, allowing players to continue where they left off.

---

## Gameplay Instructions

- **Objective:**  
  Defeat the pigs by launching birds at their structures. Use a catapult to collapse blocks and eliminate pigs inside or on top of structures.

- **Launching Birds:**  
  Drag to aim and release to launch a bird from the catapult. The bird follows a projectile path and interacts with blocks and pigs. Carefully choose the angle and force for maximum impact.

---

## Project Structure

- **`AngryBirds`**  
  The main class responsible for initializing the game and managing screens. It handles transitions between screens, such as the start screen and different levels.

- **`Level1`, `Level2`, `Level3`**  
  These classes represent different levels, each with unique setups of obstacles and pigs. They extend libGDX's Screen class.

- **`Bird`**  
  The parent class for all bird types, containing methods for movement, collision detection, and environmental interaction. Specific birds (red, yellow, explosive) inherit from this class.

- **`Pig`**  
  Represents pig enemies placed within structures. When hit, pigs are removed, and the player earns points.

- **`Block`**  
  Destructible environment blocks (wood, stone, glass). Blocks are destroyed upon impact and update their state accordingly.

- **`Catapult`**  
  Launching mechanism for birds. Handles trajectory calculation and force application during launch.

---

## How to Run

1. Navigate to the `Lwjgl3Launcher.java` file.
2. Run the file to start the game.

**Path:**  
`C:\Users\L2\IdeaProjects\bitbrawlers\lwjgl3\src\main\java\com\bitbrawlers\angrybirds\lwjgl3\Lwjgl3Launcher.java`

---

## Online Sources Referenced

-https://libgdx.badlogicgames.com/

-https://angrybirds.fandom.com/wiki/Angry_Birds_Wiki

-https://junit.org/junit5/

-https://libgdx.com/wiki/start/project-generation

---

## GitHub Link
https://github.com/palak363/AngryBirds_Deadline3/tree/main
