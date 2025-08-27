# Project 9

Project 9 serves as an introduction to the high level java-like `Jack` programming language which will be a core part in later projects in which we create a fully operational compiler. The objective of this project was to create an application: `Paddle Ball` in the jack programming language. Paddle Ball is a game in which there is a paddle on the bottom edge of the `hack computer`. This paddle can be moved left or right by the player using A or D. There is also a ball which is constantly moving. The goal is to keep the ball in the screen for as long as possible by deflecting it using the paddle. The length of time for which the game may be played is up to the player. Termination of the game occurs in the two following cases only:

- The player presses Q - **_Quit_**
- The ball reaches and passes the lower edge of the screen - **_GAME OVER_**

All source code for the `Paddle Ball` game is located in `projects/9/PaddleBall`. All other files in `projects/9` are example files obtained from the nand2tetris software suit and can be used to aid understanding of the solution presented. This markdown file is separated into sections describing each of the `.jack` files:

- **_Main_**
- **_Paddle_**
- **_Ball_**

## Main

### (I)
Much like java, main.jack is the `entry point` into the program. It is the means by which the user interacts with the program. It starts by positioning the paddle at the bottom centre of the screen and begins moving the ball upon running of the program. The simulation of the ball continues until termination of the program.

### (II)
Using the user input the program decides upon the next potential position(s) of the ball and paddle. Before the paddle and/or ball are drawn at their next position, the program checks for any conflicts. Conflicts include the ball reaching the edge of the screen, the ball touching the paddle, or the paddle being at either extremes of the screen. In the case of any of these conflicts, the ball is reflected from the appropriate surface and/or the paddle is prevented from exceeding the edged of the screen. Once any conflicts have been resolved, rendering occurs.

### (III)
Rendering occurs in three steps. First, the current paddle and ball position - which is represented by a series of pixels shaded black - is erased by coloring the pixels white - background color. The next position is calculated as described above `(II)`,  following which the paddle and ball are drawn at their next positions by shading specific pixels. As jack is a high level programming language, its methods are often drawn from classes. One such class is the Screen class and it includes methods which handle the process of shading pixels by accessing registers. The specific registers accessed are the `8K Screen registers in the HACK computer memory map`.

### (IV)
Steps **(I) - (III)** continue until either the player quits by pressing `Q`, or the players loses the game. For the player to lose the game, the ball must exceed the bottom edge of the screen.

## Ball

The `Ball.java` class is responsible for housing methods which draw, erase and return the balls characteristics. Characteristics of the ball include its position _given as coordinates_, its size and its velocity. Methods from this class are called on by `Main.jack` where need be in the program flow.

## Paddle

The `Paddle.java` class is similar to the `Ball.java` class in that it simply specifies methods which draw, erase and return the x and y values of the edges of the paddle to detect collision with the ball. Additionally it also houses methods which move the paddle left or right depending on user input from the `Main.java` class. As in the `Ball.java` class, these methods may be called at any time during running of the program.