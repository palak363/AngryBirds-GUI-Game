# AngryBirds_AP

## **Project Overview**

This project is an implementation of a game inspired by the popular Angry Birds franchise. The game is developed using libGDX in Java, following object-oriented programming (OOP) principles. The main goal is to provide a fun and engaging gameplay experience with features such as multiple levels, different bird types, and interactive physics-based environments.

### **Key Features**

- Multiple Levels: The game includes multiple levels with increasing difficulty.
- Bird Types: Different types of birds with unique abilities.
- Physics-Based Gameplay: Realistic projectile motion using physics simulations.
- Score Tracking: Keep track of the player's progress and score across levels.
- Pause and Resume: Ability to pause and resume the game from where it left off.

### **Technologies Used**

- Java: The core programming language used to implement the game logic and gameplay.
- libGDX: A Java game development framework used for rendering the game’s graphics, handling user inputs, and managing assets.
- JUnit: For unit testing different components of the game.
- Gradle: Used for dependency management and project build automation.


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


### **Setup Instructions**

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
