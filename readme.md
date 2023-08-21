# How to Use ultimateconsole

## Table of Contents

- [Basics](https://github.com/TannYuld/ultimateconsole#header-basics)
  
  - [Console Operations](https://github.com/TannYuld/ultimateconsole#header-console-operations)
    
    - [Creating console](https://github.com/TannYuld/ultimateconsole#header-creating-console)
    
    - [Console interactions](https://github.com/TannYuld/ultimateconsole#header-console-interactions)
      
      - [Console input](https://github.com/TannYuld/ultimateconsole#header-input)
      
      - [Console output](https://github.com/TannYuld/ultimateconsole#header-output)
      
      - [Advanced input](https://github.com/TannYuld/ultimateconsole#header-advanced-input)

- [Customizing console](https://github.com/TannYuld/ultimateconsole#header-customizing-console)
  
  - [Window properties](https://github.com/TannYuld/ultimateconsole#header-window-properties2)

- [Class Overview](https://github.com/TannYuld/ultimateconsole#header-class-overview)
  
  - [Console Class](https://github.com/TannYuld/ultimateconsole#header-class-console)
  
  - [Window Class](https://github.com/TannYuld/ultimateconsole#header-class-window)
  
  - [WindowProperties Class](https://github.com/TannYuld/ultimateconsole#header-class-window-properties)

---

## Basics

Introducing UltimateConsole: Simplifying Console Application Development

UltimateConsole is a powerful library designed to 
streamline the process of creating console applications with ease. With 
three essential modules at its core, it empowers developers to build 
user-friendly console applications effortlessly.

1. Console Module: The heart of UltimateConsole, this module 
   serves as the command center, overseeing all console processes. It 
   handles every action related to the console, providing a seamless and 
   efficient user experience.

2. Window Module: The Window class serves as the central hub 
   for all GUI operations in UltimateConsole. It plays a pivotal role in 
   facilitating seamless communication with the WindowProperties class, 
   which is used as an index-parameter. This powerful interaction empowers 
   developers to easily customize the behavior and appearance of the 
   graphical user interface (GUI) within the console application.
   
   By leveraging the dynamic interplay between the Window and
   WindowProperties classes, developers gain fine-grained control over 
   their GUI elements. This allows for easy customization of the console 
   application's appearance, enabling the creation of visually engaging and
   user-friendly interfaces.
   
   With the combination of these two essential elements, the 
   Window class and the WindowProperties class, developers can effortlessly
   create a basic console application that boasts an intuitive and 
   interactive user interface. UltimateConsole provides a simple yet robust
   framework, paving the way for developers to craft impressive console 
   applications without unnecessary complexity.

3. Window Properties: Acting as a versatile container class, 
   the WindowProperties class efficiently stores GUI overlay information. 
   This feature enables developers to present data and interact with users 
   in a visually appealing and organized manner, allowing developers to 
   fully customize and tailor the behavior of the windows in their console 
   application. From appearance to functionality, Window Properties offer 
   fine-grained control over the user interface.

### Console Operations

#### Creating console

To create a console application using UltimateConsole, you can utilize the constructor parameter `new Console(Window window)`.
 This approach allows for seamless integration of the Window class with 
the Console class, forming the foundation of your application.

The Window class, when passed as a parameter to the Console constructor, requires two essential parameters of its own: `new Window(Title String, WindowProperties prop)`.
 By providing the title as a string and configuring the desired 
WindowProperties, you gain the ability to customize the appearance and 
behavior of your console application's graphical user interface.

```java
WindowProperties prop = new WindowProperties(); //Creating window properties
prop.lineWrapping(true); //Changing line wrapping behaviour of this property container

Window window = new Window("Console", prop); //Creating a window titled Console and assigning window properties that is just created

Console console = new Console(window); //Creating console and activate it with window
console.print("Hello World"); //Printing hello world to screen
```

A console can be created even without a window initially. However, to
activate and display the console, a window needs to be assigned later
on.

```java
Console console = new Console(); // Created a console with an empty body.

Window window = new Window("Late Window", new WindowProperties());
console.attachWindow(window); // A new window is attached to the console and shown automatically.
```

One console class is sufficient for 
handling the entire operation; there is no need for a second console 
screen. However, in cases where it is required, multiple windows may be 
used. To switch between windows, utilize the `switchWindow()` method.

```java
WindowProperties prop = new WindowProperties();
Window firstWindow = new Window("First Window", prop);
Window secondWindow = new Window("Second Window", prop);

Console console = new Console(firstWindow); // The first window is displayed.

console.sleepSec(2); // Waited for 2 seconds.

console.switchWindow(secondWindow); // The first window is disposed of, and the second window is displayed.
// The start position of the new window is determined by the startPosition property from WindowProperties.

console.sleep(3000); // Wait for 3 seconds.

console.switchWindowKeepPosition(firstWindow); // Now the first window starts where the second window was before it closed.
```

The combination of the Console and Window 
classes results in effortlessly built console applications that boast an
 interactive and user-friendly experience.

### Console interactions

There are plenty of ways to receive input or display 
information in UltimateConsole. The console window is constructed using 
two different parts: the Input section, which gets activated 
automatically when requesting input, and the Output section, where 
various types of animations or text can be displayed.

### Output

Information can be displayed using the `console.print()` method. This method prints the desired content on the console screen.

```java
// Create a new console with a window titled "Console" and specified properties
Console console = new Console(new Window("Console", new WindowProperties()));

console.print("four"); // Prints 'four' on the screen

console.println(" five"); // Prints 'four' on the screen and the next print operation starts on the next line

console.lnprint("six"); // Moves to the next line, then prints 'six' on the screen

/* Output
four five

six
*/
```

To clear the output text, the `console.clear()` method can be used. This function allows clearing all the text 
displayed on the console, providing a clean slate for new content.

```java
// Creating a new console with a window titled "Console" and specified properties
Console console = new Console(new Window("Console", new WindowProperties()));

// Printing numbers from 0 to 9
for (int i = 0; i < 10; i++) {    console.println(i);}

console.println("This is a sneaky text"); // Printing "This is a sneaky text"
console.clearll(); // Clears the last line, preventing the text above from being displayed
console.sleepSec(2); // Waits for 2 seconds
console.clearll(3); // Clears the last 3 lines

console.println("This is not a sneaky text"); // Printing "This is not a sneaky text"
console.sleep(1500); // Waits for 1500 milliseconds
console.clearLastLine(); // Clears the last line after 1500 milliseconds, allowing the text above to be displayed for 1.5 seconds

console.clear(); // Clears the entire text in the console
```

A basic animation can be created using the `console.clearll();` method. This method allows for the generation of simple animations by 
clearing the last line of the console display, creating a sense of 
movement or dynamic content.

```java
// Define animation frames
String[] animation = {    "-",    "--",    " --",    "  --",    "   --",    "    -",    "     "
};

// Create a new console with a window titled "Console" and specified properties
Console console = new Console(new Window("Console", new WindowProperties()));

// Print "Loading..." to the console
console.print("Loading...");

// Set animation duration and frame rate
int animationDurationInMili = 3000;
int animationRatePerMili = 50;

// Calculate the number of animation frames and operations
int animationFrameCount = animation.length;
int animationOperationCount = animationDurationInMili / (animationRatePerMili * animationFrameCount);

// Perform the animation
for (int i = 0; i < animationOperationCount; i++) {    for (int ii = 0; ii < animationFrameCount; ii++) {        // Print the current animation frame
        console.lnprint(animation[ii]);                // Wait for a specific duration to create the animation effect
        console.sleep(animationRatePerMili);                // Clear the last line to update the animation frame
        console.clearll();    }}

// Move to a new line after the animation
console.println();
console.println("Loading was complete!");
```

### Input

There are five different input types available for primitive types.

| Type   | String            | Integer         | Float             | Double             | Boolean             |
| ------ | ----------------- | --------------- | ----------------- | ------------------ | ------------------- |
| Method | `getInputPlain()` | `getInputInt()` | `getInputFloat()` | `getInputDouble()` | `getInputBoolean()` |

```java
// Create a new console with a window titled "Console" and specified properties
Console console = new Console(new Window("Console", new WindowProperties()));

// Prompt the user to enter their name
console.println("What is your name:");
String name = console.getInputPlain();

// Prompt the user to enter their age (integer)
Integer age = -1;
do {    console.println("What is your age:");    try {        age = console.getInputInt();        break;    } catch (Exception e) {        console.println("Enter a digit!");    }} while (age == -1);

// Prompt the user to enter their height (floating-point number)
Float height = -1f;
do {    console.println("What is your height:");    try {        height = console.getInputFloat();        break;    } catch (Exception e) {        console.println("Enter a valid input!");    }} while (height == -1);

// Prompt the user to answer a yes/no question (boolean)
console.println("Do you have children [yes/no]:");
boolean hasChild = console.getInputBoolean();
```

The method `getInputBoolean()` 
presents a True/False question, allowing users to determine custom 
string values for True and False. By default, the values "Yes" and "No" 
(not case sensitive) are used. This means that the code will loop until 
the user types either "Yes" or "No" to respond to the question.

```java
// Create a new console with a window titled "Console" and specified properties
Console console = new Console(new Window("Console", new WindowProperties()));

// Prompt the user to answer a True/False question using default values ("Yes" for True and "No" for False)
boolean firstValue = console.getInputBoolean();

// Set custom True and False values ("Si" for True and "Nee" for False) for the following question
console.setTrueFalseForm("Si", "Nee");

// Prompt the user to answer a True/False question using the new custom values
boolean secondValue = console.getInputBoolean();
```

### Advanced Input

To request input for non-primitive types, you can use the `askInput(UserInput input)` method. This method accepts a `UserInput` object as a parameter, allowing you to handle complex input scenarios beyond primitive types.

```java
// Create a new console with a window titled "Console" and specified properties
Console console = new Console(new Window("Console", new WindowProperties()));

// Create a new UserInput object of type Selection
UserInput question = new UserInput(UserInputTypes.Selection);

// Add two possible answers to the question
question.addQuestion("Answer 1").addQuestion("Answer 2");

// Ask the user to input their selection using the created UserInput object
console.askInput(question);

// Get the result of the user's selection, which will be an Integer value (0 for the first answer, 1 for the second answer)
Integer answer = question.getResult();
```

Questions with boolean answers (Yes/No) can also be requested using the `askInput()` method. This method allows you to prompt the user for a Yes or No response and handle the input accordingly.

```java
// Create a new console with a window titled "Console" and specified properties
Console console = new Console(new Window("Console", new WindowProperties()));

// Prompt the user with the question "Are you sure?"
console.println("Are you sure?");

// Create a new UserInput object of type YesNo
UserInput question = new UserInput(UserInputTypes.YesNo);

// Add two possible answers to the question: "Yep!" for Yes and "Nahhh" for No
question.addQuestion("Yep!").addQuestion("Nahhh");

// Ask the user to input their response using the created UserInput object
boolean answer = question.getBooleanResult();
```

To wait until any key is pressed, the method `waitForAnyKey()` can be used. This function allows the program to pause its execution until the user presses any key on the keyboard.

```java
// Create a new console with a window titled "Console" and specified properties
Console console = new Console(new Window("Console", new WindowProperties()));

// Prompt the user to enter their age
console.println("Type enter your age: ");

boolean answerValid = false;
do {    try {        // Get the user's input as an Integer
        Integer answer = console.getInputInt();        // Mark the answer as valid and exit the loop
        answerValid = true;        break;    } catch (Exception e) {        // Handle the case when the user enters non-digit input
        console.println("Please enter a digit");        console.println("\tPress any key to continue");                // Wait for the user to press any key to continue
        console.waitForAnyKey();        // Clear the last two lines to prepare for the next input attempt
        console.clearll();        console.clearll();    }

// Repeat the loop until a valid input is obtained
} while (!answerValid);
```

Input Stack Requests are used to create 
custom keyListener events in the active console. These requests are 
window-independent, meaning that switching between windows will not 
affect the functioning of any Input Stack Request. Thus, developers can 
implement custom keyListener functionality that remains consistent 
across different console windows.

```java
// Declare static boolean variables to track if the 'T' and 'U' keys are pressed
static boolean pressedT = false;
static boolean pressedU = false;

public static void main(String[] args) {    // Create a new console with a window titled "Console" and specified properties (lineWrapping enabled)
    Console console = new Console(new Window("Console", new WindowProperties().lineWrapping(true)));    // Display a message to inform the user about the key press behavior
    console.println("You can press T infinitely, but you can press U only once");    // Add an InputRequestStack for the 'U' key with FinalState.REMOVE behavior
    console.addInputRequestStack(new InputRequestStack('u', FinalState.REMOVE) {        @Override
        public void keyReleased() {            // Empty implementation for keyReleased method
        }        @Override
        public void keyPressed() {            // Set the flag to indicate that 'U' key is pressed
            pressedU = true;            if (pressedT) {                // Clear two lines and display the message if 'T' key is also pressed
                console.clearll(2);                console.println("Pressed U");                console.println();                console.println("Pressing T");            } else {                // Display the message if only 'U' key is pressed
                console.println();                console.println("Pressed U");            }        }        @Override
        public void keyHold() {            // Empty implementation for keyHold method
        }    });    // Add an InputRequestStack for the 'T' key with FinalState.DO_NOTHING behavior
    console.addInputRequestStack(new InputRequestStack('t', FinalState.DO_NOTHING) {        @Override
        public void keyReleased() {            if (pressedT) {                // Reset the flag and clear the appropriate number of lines on key release
                pressedT = false;                if (pressedU) {                    console.clearll(2);                } else {                    console.clearll(3);                }            }        }        @Override
        public void keyPressed() {            if (!pressedT) {                // Set the flag and display the appropriate message on key press
                pressedT = true;                if (pressedU) {                    console.println();                    console.println("Pressing T");                } else {                    console.println("\n");                    console.lnprint("Pressing T");                }            }        }        @Override
        public void keyHold() {            // Empty implementation for keyHold method
        }    });}
```

## Customizing Console

### Window Properties

Window properties serve as a container that holds all the 
information about the window's behavior and appearance. When these 
properties are changed, they are automatically applied to the attached 
window, ensuring that any updates to the properties take effect 
immediately. This allows for seamless customization and dynamic 
adjustments to the window's features without the need for manual 
intervention or explicit updates.

Here is an example showcasing the difference between the default layout and a custom layout:

[![WindowBehaviour2](https://user-images.githubusercontent.com/86157542/256907417-c50df861-a33d-4550-a00d-568551b93c15.png)](https://user-images.githubusercontent.com/86157542/256907417-c50df861-a33d-4550-a00d-568551b93c15.png)

[![WindowBehaviour1](https://user-images.githubusercontent.com/86157542/256907435-951c0078-90b0-4f73-aee2-df2a5c9333dd.png)](https://user-images.githubusercontent.com/86157542/256907435-951c0078-90b0-4f73-aee2-df2a5c9333dd.png)

The code for a customized window is similar to the one provided below.

```java
// Create a new WindowProperties object to customize the window appearance
WindowProperties prop = new WindowProperties();

// Create a new Window with the specified properties
Window window1 = new Window("Window", prop);

// Create a new Console instance and associate it with the window1
Console console = new Console(window1);

// Customize the output text background color to blue
prop.outputTextBackground(Color.blue);

// Customize the output text foreground color to red
prop.outputTextForeground(Color.red);

// Create a new Font object with the specified font family, style, and size
Font font = new Font("Courier", Font.PLAIN, 20);

// Customize the output text font to the specified font
prop.outputTextFont(font);

// Customize the input text background color to white
prop.inputTextBackground(Color.white);

// Customize the input text foreground color to black
prop.inputTextForeground(Color.black);

// Customize the input text caret color to dark gray
prop.inputTextCaretColor(Color.darkGray);

// Customize the input selected text color to magenta
prop.inputSelectedTextColor(Color.magenta);

console.println("Hello World");
String string = console.getInputPlain();
```

## Class Overview

### Class: Console

| Method                                                | Explanation                                                                                                     |
| ----------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- |
| `Console()`                                           | Constructor for the Console class. Initializes the input request stack handler thread.                          |
| `Console(Window window)`                              | Constructor that creates a new Console instance with the specified window.                                      |
| `Console(Window window, boolean selfActivation)`      | Constructor that creates a new Console instance with the specified window and optionally activates the console. |
| `activateConsole()`                                   | Activates the console by making it visible, setting focus, and adding key listeners to the active window.       |
| `terminateAllConsoles()`                              | Terminates all consoles by exiting the system.                                                                  |
| `terminateConsole()`                                  | Terminates the current console, disposing the active window and removing key listeners.                         |
| `switchWindow(Window window)`                         | Switches to a new window, terminating the current console and activating the new window.                        |
| `switchWindowKeepPosition(Window window)`             | Switches to a new window while keeping the previous window's position.                                          |
| `attachWindow(Window newWindow)`                      | Attaches a new window to the current console, replacing the active window.                                      |
| `setTrueFalseForm(String trueForm, String falseForm)` | Sets custom true and false forms for `getInputBoolean()` method.                                                |
| `setTrueForm(String trueForm)`                        | Sets a custom true form for `getInputBoolean()` method.                                                         |
| `setFalseForm(String falseForm)`                      | Sets a custom false form for `getInputBoolean()` method.                                                        |
| `clear()`                                             | Clears the output text in the active window.                                                                    |
| `clearll()`                                           | Clears the last line of the output text in the active window.                                                   |
| `clearll(int arg)`                                    | Clears multiple lines (specified by `arg`) of the output text in the active window.                             |
| `clearLastLine()`                                     | Clears the last line of the output text in the active window.                                                   |
| `print(T arg)`                                        | Prints the specified argument to the output text in the active window.                                          |
| `println()`                                           | Appends a newline to the output text in the active window.                                                      |
| `println(T arg)`                                      | Prints the specified argument followed by a newline to the output text in the active window.                    |
| `lnprint(T arg)`                                      | Appends a newline and the specified argument to the output text in the active window.                           |
| `sleep(long millis)`                                  | Pauses the program execution for the specified number of milliseconds.                                          |
| `sleepSec(int seconds)`                               | Pauses the program execution for the specified number of seconds.                                               |
| `sleepNano(long nanos)`                               | Pauses the program execution for the specified number of nanoseconds.                                           |
| `getInputPlain()`                                     | Requests input from the user as a String and returns the user's input.                                          |
| `getInputInt()`                                       | Requests input from the user as an integer and returns the user's input.                                        |
| `getInputDouble()`                                    | Requests input from the user as a double and returns the user's input.                                          |
| `getInputFloat()`                                     | Requests input from the user as a float and returns the user's input.                                           |
| `getInputBoolean()`                                   | Requests input from the user as a boolean (True/False) and returns the user's input.                            |
| `askInput(UserInput userInput)`                       | Asks the user multiple-choice questions using the provided UserInput object.                                    |
| `waitForAnyKey()`                                     | Pauses the program execution and waits for the user to press any key.                                           |
| `addInputRequestStack(InputRequestStack arg)`         | Adds an InputRequestStack to the console's input request stack handler.                                         |
| `removeInputStackRequest(InputRequestStack arg)`      | Removes an InputRequestStack from the console's input request stack handler.                                    |
| `getOutputLineCount()`                                | Returns the number of lines in the output text of the active window.                                            |
| `getActiveWindow()`                                   | Returns the active window of the console.                                                                       |

---

### Class: Window

| Method                                       | Explanation                                                                                                                                                                                                                                  |
| -------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `Window(String title,WindowProperties prop)` | Constructor for creating a new Window object with the specified title and properties.                                                                                                                                                        |
| `Window(Window anotherWindow)`               | Constructor for creating a new Window object based on another Window object, copying its properties.                                                                                                                                         |
| `setProperty(WindowProperties prop)`         | Sets the properties of the window based on the WindowProperties object passed as an argument.                                                                                                                                                |
| `getOutputTextArea()`                        | Retrieves the JTextArea used for displaying output in the window.                                                                                                                                                                            |
| `getInputTextArea()`                         | Retrieves the JTextField used for receiving input in the window.                                                                                                                                                                             |
| `getDrawPanel()`                             | Retrieves the DrawPanel used for custom drawing in the window.                                                                                                                                                                               |
| `getProperties()`                            | Retrieves the WindowProperties object associated with the window, <br>containing various properties and settings for the window appearance and<br> behavior.                                                                                 |
| `getRealSize()`                              | Retrieves the real size of the window, represented by a <br>DrawDimension object. This size includes the entire window, including <br>any borders or decorations. It is useful for drawing custom graphics <br>within the window boundaries. |

---

### Class: WindowProperties

| Method                                     | Explanation                                                                                         |
| ------------------------------------------ | --------------------------------------------------------------------------------------------------- |
| `windowHeight(int arg)`                    | Sets the height of the window to the specified value (arg).                                         |
| `windowWidth(int arg)`                     | Sets the width of the window to the specified value (arg).                                          |
| `windowPosX(int arg)`                      | Sets the X position of the window to the specified value (arg).                                     |
| `windowPosY(int arg)`                      | Sets the Y position of the window to the specified value (arg).                                     |
| `canResize(boolean arg)`                   | Sets whether the window can be resized (arg = true) or not (arg = false).                           |
| `alwaysOnTop(boolean arg)`                 | Sets whether the window should always be on top of other windows (arg = true) or not (arg = false). |
| `outputTextMargin(Insets arg)`             | Sets the margin of the output text in the window to the specified Insets (arg).                     |
| `outputTextFont(Font arg)`                 | Sets the font for the output text in the window to the specified Font (arg).                        |
| `inputTextFont(Font arg)`                  | Sets the font for the input text in the window to the specified Font (arg).                         |
| `inputTextForeground(Color arg)`           | Sets the foreground color of the input text in the window to the specified Color (arg).             |
| `outputTextForeground(Color arg)`          | Sets the foreground color of the output text in the window to the specified Color (arg).            |
| `inputTextBackground(Color arg)`           | Sets the background color of the input text in the window to the specified Color (arg).             |
| `outputTextBackground(Color arg)`          | Sets the background color of the output text in the window to the specified Color (arg).            |
| `inputSelectedTextColor(Color arg)`        | Sets the text color of the selected input text in the window to the specified Color (arg).          |
| `outputSelectedColor(Color arg)`           | Sets the text color of the selected output text in the window to the specified Color (arg).         |
| `inputTextCaretColor(Color arg)`           | Sets the caret color of the input text in the window to the specified Color (arg).                  |
| `isFullscreen(Boolean arg)`                | Sets whether the window should be in fullscreen mode (arg = true) or not (arg = false).             |
| `lineWrapping(Boolean arg)`                | Sets whether line wrapping is enabled for the window (arg = true) or not (arg = false).             |
| `closeOperation(CloseButtonOperation arg)` | Sets the default close button operation of the window to the specified CloseButtonOperation (arg).  |
