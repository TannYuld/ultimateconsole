# UltimateConsole Project

The UltimateConsole project is designed to simplify the process of creating interactive console applications in Java. It provides a set of classes and components that allow developers to quickly build console interfaces with various features, including customizable windows, user input handling, drawing elements, and more.

## Features

- **Window Customization:** UltimateConsole offers customizable window properties, such as dimensions, fonts, colors, and behavior options. Developers can configure the appearance of the console window to suit their application's style.

- **User Input Handling:** The project provides classes to facilitate user input handling, including Yes/No questions, multiple-choice selections, and numeric inputs. Developers can easily create interactive prompts and capture user responses.

- **Drawing Elements:** UltimateConsole includes a drawing panel that can be used to display rectangles, text, and selected squares. This feature can be helpful for visualizing data or interactive elements within the console.

- **Input Stream Management:** The project offers a mechanism for managing input streams, allowing developers to define actions triggered by specific key presses or releases. This is useful for implementing keyboard shortcuts or interactive commands.

- **Easy Integration:** The provided classes are designed to be easily integrated into existing projects. Developers can use these components to enhance their console applications without reinventing the wheel.

## Getting Started

To use the UltimateConsole project in your Java application, follow these steps:

1. Clone or download this repository to your local machine.

2. Include the necessary Java files from the repository in your project's source code.

3. Create instances of `Window`, provide `WindowProperties` to customize the window frame.

4. Provide that instance of `Window` class to create a `Console` class to handle console process. This class is brain of your console.

5. Use the input handling and drawing functionalities of Console to create interactive prompts and visualize data.

6. Run your application and interact with the console interface you've created.

## Additanionl Interaction Tools

To get more complex input's from user `InputStream` or `UserInput` classes can be used.

### InputStream

`InputStream` class used to get (continuous) key input independently from console. Here is a uasge example of it.

```java
Console console = new Console(new Window("Console", new WindowProperties()));

// Add new InputStream class to console, with the T key of the activator.
console.addInputStream(new InputStream('t', FinalAction.DO_NOTHING, true) {

    // This method called when T key is released
    @Override
    public void keyReleased()
    {
        console.clearll();
    }

    // This method called when T key is pressed
    @Override
    public void keyPressed()
    {
        console.println("Pressing T");
    }

    @Override
    public void keyHold() {}
});
```

### UserInput

`UserInput` class is basiaclly a side-function class to create multiple choice question. Here is a simple usage of it.

```java
Console console = new Console(new Window("Console", new WindowProperties()));

// Create a UserInput class to ask
UserInput question = new UserInput(UserInputTypes.Selection)
    //Add answer selection - (With arrow keys answer can be changed in the console screen)
    .addAnswer("London")
    .addAnswer("Amsterdam")
    .addAnswer("Berlin");

// Print question
console.println("Where do you want to go?");

// Ask question to get a input
console.askInput(question);

// Print a phrase according to answer
switch(question.getResult()) 
{
    case 0 -> console.print("I like London too");
    case 1 -> console.print("I went to Amsterdam before");
    case 2 -> console.print("Ich bin COMPUTER");
}
```

More info about this topic can be found in [example projects]() file.

## Example Usage

Here's a simple example of how to create a basic console interface using the UltimateConsole project:

```java
import ultimateconsole.*;

public class ConsoleApp {
    public static void main(String[] args) {
        // Create a window and set properties
        WindowProperties windowProperties = new WindowProperties()
            .windowHeight(400)
            .windowWidth(600)
            .outputTextFont(new Font("Monospaced", Font.PLAIN, 19))
            // ... other properties
            .closeOperation(CloseButtonOperation.EXIT);

        Window consoleWindow = new Window("Console App", windowProperties);

        // Run the console application
        Console console = new Console(consoleWindow);
        // Print 'Hello World' to screen
        console.print("Hello World");
    }
}
```

Defualt values of `WindowProperties` class can be used.

```java
import ultimateconsole.*;

public class ConsoleApp {
    public static void main(String[] args) {
        // Create a window and set properties
        WindowProperties windowProperties = new WindowProperties();

        Window consoleWindow = new Window("Console App", windowProperties);

        // Run the console application
        Console console = new Console(consoleWindow);
        // Print 'Hello World' to screen
        console.print("Hello World");
    }
}
```

For more examples and detailed [usage instructions]() and [example projects](), refer to the provided Java files and their documentation within the repository.

## License

This project is licensed under the [MIT License](https://chat.openai.com/LICENSE).

## Acknowledgements

The UltimateConsole project was inspired by the need for a simplified way to create console applications in Java.

If you have any questions, issues, or suggestions, feel free to open an [issue](https://github.com/yourusername/ultimate-console/issues) on GitHub.
