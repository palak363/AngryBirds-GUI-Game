# Angry Birds Game

## Project Overview
The second submission is a static GUI implementation of the **Angry Birds** game using **libGDX**. It features birds, pigs, and materials typically found in the game but focuses on static game screens and does not yet include dynamic gameplay elements.

## Birds
The game includes a variety of birds with different characteristics:
1. **Red Bird** – no special ability (default characterstic)
2. **Blue Bird** – Freezes upon clicking
3. **Black Bird** – Explodes upon impact
4. **Pink Bird** – Expands like bubble upon hitting
5. **Yellow Bird** – Splits into multiple upon clicking
6. **Green Bird** – Boomerang effect upon clicking

## Pigs
Different sizes and types of pigs act as the enemies:
1. **Small Pig** – weakest enemy
2. **Medium Pig** – moderate challenge
3. **Large Pig** – Stronger than small and medium pigs
4. **King Pig** – The strongest enemy

## Materials
The game objects are built using the following materials:
1. **Wood** – Weak, breaks easily
2. **Glass** – Shatters quickly but lightweight
3. **Rock** – The toughest material, hard to destroy

## How to Run the Game
The main class that launches the game is `Lwjgl3Launcher`. To run the game:
1. Open your preferred IDE (such as IntelliJ or Eclipse).
2. Ensure the **libGDX** dependencies are correctly set up.
3. Run the `Lwjgl3Launcher` class to launch the game.


## OOP Concepts:

## 1. Encapsulation:

All member variables, such as background, back, level1, spriteBatch, and game, are declared as private, limiting their visibility to within the class. Only methods within each class (render, dispose, etc.) have direct access to the encapsulated data, providing an interface to interact with the underlying details.

## 2. Abstraction(through Interface): 

Screen Interface Implementation: All classes (SelectLevelScreen, StartScreen, WinScreen) implement the Screen interface, hiding specific implementation details of each screen type. Users of these classes only need to know they can call render, resize, show, hide, pause, resume, and dispose without needing to understand each screen's internal workings.

Game Screen Transitions: The game.setScreen(new MenuScreen(game)); or game.setScreen(new SelectLevelScreen(game)); calls abstract the details of screen transitions, allowing the main game to switch screens without knowing their specifics.


## 3. Inheritance: 

Screen Interface: The SelectLevelScreen, StartScreen, and WinScreen classes inherit from the Screen interface, gaining access to a set of expected methods (render, resize, etc.) that each screen must define. This provides a uniform structure for all screens in the game.
Use of OrthographicCamera and Texture Classes: The game also inherits basic rendering and positioning functionalities through OrthographicCamera and Texture, though they are not explicitly extended. 

All types of bird inherit from Bird class, All types of Blocks inherit froom Block class and all types of Pigs inherit from Pig class.

## 4. Composition: 

AngryBirds Game Reference: Each screen class (SelectLevelScreen, StartScreen, WinScreen) has a reference to the AngryBirds game instance, allowing each screen to interact with the core game state and resources.
Textures and Sounds: Textures (e.g., background, mainMenu, exit) and sounds (e.g., buttonSound) are composed within each screen, illustrating the "has-a" relationship where each screen "has" its own textures and sounds.

Camera and SpriteBatch: Each screen includes its OrthographicCamera and SpriteBatch objects, allowing for independent rendering and camera management for each screen without interdependence.

## 5. Method overriding: 

Screen Interface Methods: The render, resize, show, hide, pause, resume, and dispose methods in SelectLevelScreen, StartScreen, and WinScreen override the methods defined in the Screen interface. Each screen provides unique functionality for these methods.

## 6. Polymorphism:

Screen Interface Polymorphism: By implementing the Screen interface, each screen class (SelectLevelScreen, StartScreen, WinScreen) provides its specific implementation of render, dispose, and other methods. This is achieved through method overriding.

Game Screen Switching: The setScreen call demonstrate polymorphism as the setScreen method in the AngryBirds game object can accept any object implementing Screen, allowing flexible screen transitions without altering the game’s logic.


Resources 
--------------------------------------------------
https://libgdx.com/wiki/start/a-simple-game
https://libgdx.com/wiki/graphics/2d/scene2d/scene2d
https://libgdx.com/wiki/extensions/physics/box2d
https://github.com/encryptedcation/TankStars/tree/master

# Group members 
Palak Yadav: 2023363
Umang Aggarwal: 2023567

#GITHUB
https://github.com/palak363/AngryBirds

