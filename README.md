# AngryBirds

## **Project Overview**

This project is an implementation of a 2D physics-based game inspired by the popular *Angry Birds* franchise. The game was developed using **libGDX**, a Java game development framework, to deliver high-performance graphics, smooth animations, and a responsive gaming experience. The game follows **object-oriented programming (OOP)** principles, ensuring that the code is modular, scalable, and easy to maintain. 

The primary goal of this project is to replicate the core mechanics of *Angry Birds* while introducing additional features, such as multiple levels with varying difficulties, a variety of birds with different abilities, and an engaging physics-based environment that interacts with the player's actions. The game also includes a **persistence system** that allows players to pause and resume their progress, making it more convenient and user-friendly.

Key highlights of the game:
- **Multiple Levels**: Each level has its own unique layout of blocks and pigs, requiring players to adapt their strategies.
- **Variety of Bird Types**: Different types of birds with varying abilities, such as explosive impacts or splitting into smaller birds, add complexity and strategic depth.
- **Physics-Based Gameplay**: The core of the gameplay is based on realistic projectile motion, where birds interact with various types of blocks (wood, stone, etc.), causing them to collapse, break, or react in realistic ways.
- **Score Tracking**: Players accumulate points based on their performance, with higher scores for more efficient use of birds.
- **Pause and Resume**: Players can save their progress and pick up where they left off later, making the game experience more flexible and enjoyable.

### **Key Features**

- **Multiple Levels**: The game includes a variety of levels with increasing complexity and difficulty. Each level has its own set of obstacles and challenges, keeping the game fresh and exciting. Players must adapt their strategies to progress.
  
- **Bird Types**: There are different types of birds, each with its own unique abilities. Some birds may explode on impact, while others may split into smaller projectiles after being launched. The player must choose the right bird for the right situation.
  
- **Physics-Based Gameplay**: The game simulates realistic physics using **libGDX’s** physics engine. Birds fly in arcs, collide with structures, and react with realistic forces. Blocks of different materials (wood, stone, etc.) respond differently when hit.
  
- **Score Tracking**: The game tracks the player's score based on the number of birds used and the destruction caused. Higher scores are achieved by using fewer birds or by causing massive destruction. The score is updated live as the player progresses through the game.
  
- **Pause and Resume**: The game allows players to pause and resume their progress. When the game is paused, the current level, bird positions, and any destroyed objects are saved. The player can then return to the game later and pick up where they left off.

### **Technologies Used**

- **Java**: The core programming language used for implementing the game logic, handling user input, updating game states, and rendering the graphics. Java offers platform independence, which ensures that the game can run on different systems without modification.

- **libGDX**: A powerful, open-source game development framework that simplifies the process of rendering graphics, handling input, playing sounds, and managing assets. libGDX provides a comprehensive suite of tools to help developers create rich, interactive 2D and 3D games.

- **JUnit**: A testing framework used for writing unit tests to ensure the correctness of game logic and mechanics. Tests are written for key functionalities, such as bird movement, collision detection, and score calculation, to ensure that the game operates as expected.

- **Gradle**: A build automation tool that is used to manage dependencies, compile code, and package the game. Gradle simplifies the development process by automating repetitive tasks, such as downloading dependencies and building the project.

### **Gameplay Instructions**

- **Objective**: The primary goal of the game is to defeat the pigs by launching birds at their structures. Players must use a catapult to launch the birds at various blocks, causing them to collapse and defeat the pigs hiding inside or on top of the structures.

- **Launching Birds**: Players drag to aim and release to launch a bird from a catapult. The bird follows a projectile path and collides with the environment, interacting with blocks and pigs. Players need to carefully choose the angle and force of the launch for maximum effect.

- **Controls**: The game is controlled via mouse or touch input. On a touch device, players drag their finger to aim and release to launch birds. On a desktop, players click and drag the mouse to aim and click to release the bird.

- **Game Features**: 
  - Birds can be launched from the catapult, one at a time.
  - Structures built from different materials (wood, stone, etc.) react differently when hit by birds.
  - The game provides feedback with sound effects when birds hit targets or collide with blocks.

### **Project Structure**

The project is organized into several main classes and modules, each handling a specific part of the game:

- **AngryBirds**: The main class responsible for initializing the game and managing different screens. It handles transitions between screens, such as switching from the start screen to the level screen and from one level to the next.

- **Level1, Level2, Level3**: These are different levels in the game, each having a unique setup with various obstacles and pigs. The levels are structured as `Screen` classes in libGDX, with each level containing its own blocks, birds, and physics objects.

- **Bird**: Represents the bird objects in the game. The `Bird` class is the parent class for all bird types, and it contains methods for movement, collision detection, and interaction with the environment. Different bird types (e.g., red, yellow, or explosive birds) inherit from the `Bird` class.

- **Pig**: Represents the pig enemies. Pigs are placed on top or inside structures, and their objective is to avoid being hit by birds. When a pig is hit, it is removed from the game, and the player earns points.

- **Block**: Represents the destructible blocks that make up the environment. These blocks are of different types (e.g., wood, stone, ice), and they react differently when hit by birds. Blocks can be destroyed when impacted by a bird, and their state is updated during the game.

- **Catapult**: The mechanism used to launch the birds. The `Catapult` class handles the bird-launching logic, including calculating the trajectory and applying force to the bird when it is released.



---


A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

### Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

### Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).


## **Setup Instructions**

1. **Clone the Repository:**
   First, clone the repository to your local machine:
   ```bash
   git clone <repository-url>
   cd <project-directory>
   ```

2. **Setup Gradle Wrapper:**
   The project includes a Gradle wrapper, so you don't need to install Gradle manually.

   For **Windows**:
   ```bash
   gradlew build
   ```

   For **Linux/Mac**:
   ```bash
   ./gradlew build
   ```

3. **Run the Game:**
   Once Gradle is set up, run the game using the following command:
   ```bash
   ./gradlew lwjgl3:run
   ```

4. **Build the Game (optional):**
   To create a JAR file, use:
   ```bash
   ./gradlew lwjgl3:jar
   ```

   Run the JAR file:
   ```bash
   java -jar lwjgl3/build/libs/your-jar-file.jar
   ```

5. **Testing:**
   To run unit tests, execute:
   ```bash
   ./gradlew test
   ```

6. **Clean (optional):**
   To remove build artifacts, use:
   ```bash
   ./gradlew clean
   ```

### **Additional Setup for IDE (optional)**

- **For Eclipse**:
   ```bash
   ./gradlew eclipse
   ```

- **For IntelliJ IDEA**:
   ```bash
   ./gradlew idea
   ```


---


### Online Sources Referenced

- libGDX Documentation: https://libgdx.badlogicgames.com/
- Angry Birds Wiki: https://angrybirds.fandom.com/wiki/Angry_Birds_Wiki
- Unit Testing in Java: https://junit.org/junit5/
- Stack Overflow discussions (for resolving technical issues)
