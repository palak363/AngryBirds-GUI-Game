# AngryBirds_AP

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

---

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

These steps should guide you from setting up the project to running the game and verifying it with tests.

### Online Sources Referenced

- libGDX Documentation
- Serialization in Java
- JUnit 5 User Guide
- Stack Overflow discussions for resolving technical issues
