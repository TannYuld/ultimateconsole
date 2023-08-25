# UltimateConsole Examples

Welcome to the UltimateConsole Example Projects documentation! This resource is designed to provide a collection of practical examples that demonstrate the capabilities of the UltimateConsole project. Whether you're a developer looking to create interactive console applications or simply curious about what this project can do, you'll find a examples here that showcase different features and functionalities.

The UltimateConsole project aims to simplify the process of creating rich and interactive console applications in Java.  You can create dynamic text-based interfaces, handle user inputs, display graphics, and more, all within the familiar console environment. The project comes equipped with components like customizable windows, keyboard input handlers, and user input prompts, enabling you to build engaging and user-friendly console applications.

In this documentation, you'll find a range of example projects that cover different aspects of the UltimateConsole project:

- **Basic Interactive Prompts:** Learn how to create simple yes/no prompts, multiple-choice questions, and numeric input prompts. Understand the process of handling user responses and making decisions based on their input.

- **Customizing Window Properties:** Understand how to tailor the appearance and behavior of the console window to match your application's requirements. Adjust fonts, colors, dimensions, and more.

These examples are designed to be informative and hands-on. Each example includes detailed instructions and code snippets that guide you through the implementation process. Feel free to explore, experiment, and adapt these examples to suit your specific needs.

## Examples

### Sneaky text

In this example, the demonstration involves showcasing the console's print and clear methods.

```java
// Creating a new console with a window titled "Console" and specified properties
Console console = new Console(new Window("Console", new WindowProperties()));

do
{
    // Printing numbers from 0 to 7
    for (int i = 0; i < 8; i++)
    {
        console.println(i);
        console.sleep(250);
    }

    console.sleepSec(1);

    // Deleting numbers from 7 to 3
    for(int i = 7; i > 2; i--) 
    {
        console.clearll();
        console.sleep(250);
    }

    console.sleepSec(1);

    // Print numbers from 3 to 10 FAST
    for(int i = 2; i < 11; i++) 
    {
        console.println(i);            
        console.sleep(100);
    }

    console.println("This is a sneaky text"); // Printing "This is a sneaky text"
    console.clearll(); // Clears the last line, preventing the text above from being displayed
    console.sleepSec(2); // Waits for 2 seconds

    console.clearll(3); // Clears the last 3 lines
    console.println("This is not a sneaky text"); // Printing "This is not a sneaky text"
    console.sleep(1500); // Waits for 1500 milliseconds

    console.clearLastLine(); // Clears the last line after 1500 milliseconds, allowing the text above to be displayed for 1.5 seconds
    console.clear(); // Clears the entire text in the console

    console.sleepSec(2); // Waits 2 seconds than before loop restarts
} while (true);
```

---

### Progressbar animation

In this example, an activation key-enabled progress bar is generated, and its interactivity is enhanced through the animation of that progressbar.

```java
// Define animation frames
static String[] animation = {"-","--"," --","  --","   --","    -","     "};
static String[] tresholdStages = {"▰","▱"};

// Define variables for Button press threshold animation
static Integer percentage = 0; // Percentage that determines how long should user press the button
static Boolean pressedKey = false; // Boolean value that determines whether specified key pressed or not

public static void main(String[] args) 
{
    // Create a new console with a window titled "Console" and specified properties
    Console console = new Console(new Window("Console", new WindowProperties()));

    /*
        * Button Press Threshold
        */

    // Adding InputStream with SPACEBAR to console to check whether key pressed or not
    console.addInputStream(new InputStream(' ',FinalAction.DO_NOTHING, true) {

        @Override
        public void keyReleased()
        {
            pressedKey = false;
        }

        @Override
        public void keyPressed()
        {
            pressedKey = true;
        }

        @Override
        public void keyHold() {}
    });

    // Creating thread to assign the percentage
    Thread percentageCalculationThread = new Thread() 
    {
        @Override
        public void run() 
        {
            // Run this thread until percentage reaches 100
            while(percentage <= 100) 
            {
                if(pressedKey) 
                {
                    percentage += 2; // If SPACEBAR pressed increase percentage
                }else if(percentage > 0)
                {
                    percentage -= 2; // If SPACEBAR released decrease percentage
                }

                // Calculate percentage 20 times per second
                console.sleep(50);
            }
        }
    };

    // Start the thread
    percentageCalculationThread.start();

    // Print message
    console.println("Hold SPACEBAR for 5 seconds\n");

    // Run until percentage reaches 100        
    while(percentage < 101)
    {
        console.clearll();    // Clear the progress bar screen to repaint
        console.print(" "); // Add padding to progress bar

        // Add progress bar frames
        for(int i = 1; i<=9; i++) 
        {
            if( (i*10) <= percentage) 
            {
                // If percentage is large enough print full frame
                console.print(tresholdStages[0]);
            }else {
                // If percentage is NOT large enough print outline frame
                console.print(tresholdStages[1]);
            }
        }
        // Provide the user with precise amount of percentage.
        console.println("\t"+percentage+"%");
        // Repaint that progress bar 20 times per second
        console.sleep(50);
    }

    // Wait 1.5 seconds until next animation starts
    console.sleep(1500);

    /*
        * Loading Animation
        */

    // Print "Loading..." to the console
    console.println("Loading...");

    // Set animation duration and frame rate
    int animationDurationInMili = 3000;
    int animationRatePerMili = 50;

    // Calculate the number of animation frames and operations
    int animationFrameCount = animation.length;
    int animationOperationCount = animationDurationInMili / (animationRatePerMili * animationFrameCount);

    // Perform the animation
    for (int i = 0; i < animationOperationCount; i++) 
    {    
        for (int ii = 0; ii < animationFrameCount; ii++) 
        {
            console.println(animation[ii]);    // Print the current animation frame          
            console.sleep(animationRatePerMili);    // Wait for a specific duration to create the animation effect
            console.clearll();    // Clear the last line to update the animation frame
        }
    }

    // Print final text
    console.println("Loading was complete!");
}                                                                                                                                
```

---

### Request raw input

In this example, the console prompts the user to provide fundamental information using raw input methods.

```java
// Create a new console with a window titled "Console" and specified properties
Console console = new Console(new Window("Console", new WindowProperties()));

// Prompt the user to enter their name
console.println("What is your name:");

// Get user input as String
String name = console.getInputPlain();

Integer age = -1;
do {
    // Prompt the user to enter their age (integer)
    console.println("What is your age:");    
    try {        
        age = console.getInputInt();    // If user enter a valid input accept input and continue       
        break;    
    } catch (Exception e) 
    {        
        console.println("Enter a digit!");    // If user don't enter a valid input don't accept input and restart the loop
    }
} while (age == -1);

Float height = -1f;
do {    
    // Prompt the user to enter their height (floating-point number)
    console.println("What is your height[cm]:");    
    try {
        height = console.getInputFloat();    // If user enter a valid input accept input and continue        
        break;    
    } catch (Exception e) 
    {    
        console.println("Enter a valid input!");    // If user don't enter a valid input don't accept input and restart the loop
    }
} while (height == -1);

// Prompt the user to answer a yes/no question (boolean)
console.println("Do you have children [yes/no]:");

// Get answer from user
boolean hasChild = console.getInputBoolean();

// Clear whole console
console.clear();
// Write informations
console.println(
        "Your name is " + name + "\n"+
        "Your age is " + age + "\n"+
        "Your height is " + height + "cm\n"+
        "You " + ((!hasChild) ? "don't " : "") + "have childeren"
);
```

---

### Request input

In this example, the user is presented with a choice between two different options.

```java
// Create a new console with a window titled "Console" and specified properties
Console console = new Console(new Window("Console", new WindowProperties()));

// Create a new UserInput object of type Selection
UserInput question = new UserInput(UserInputTypes.Selection);

// Add two possible answers to the question
question.addAnswer("Answer 1").addAnswer("Answer 2");

// Ask the user to input their selection using the created UserInput object
console.askInput(question);

// Get the result of the user's selection, which will be an Integer value (0 for the first answer, 1 for the second answer)
Integer answer = question.getResult();
```

---

### Request any key

In this example, when a user enters an invalid input, the console will immediately show an error message and return to the beginning of the loop to request input anew. Rather than waiting for a specific time interval before restarting the loop, the console will now wait for the user to press any key on the keyboard.

```java
// Create a new console with a window titled "Console" and specified properties
Console console = new Console(new Window("Console", new WindowProperties()));

// Prompt the user to enter their age
console.println("Type enter your age: ");

boolean answerValid = false;
Integer answer = 0;

do {    
    try {        
        // Get the user's input as an Integer
        answer = console.getInputInt();        

        // Mark the answer as valid and exit the loop
        answerValid = true;        
        break;    
    } catch (Exception e) {        
        // Handle the case when the user enters non-digit input
        console.println("Please enter a digit");        
        console.println("\tPress any key to continue");

        // Wait for the user to press any key to continue
        console.waitForAnyKey();        

        // Clear the last two lines to prepare for the next input attempt
        console.clearll();        
        console.clearll();    
    }
// Repeat the loop until a valid input is obtained
} while (!answerValid);

console.println(answer);
```

---

### Randomness

In this example, various aspects of the console change randomly every 5 seconds to showcase the utilization of the WindowProperties attribute assignment

```java
// List of colors that will be used random in the console
public static Color[] colorList = {Color.black, Color.blue, Color.cyan, Color.darkGray, Color.gray, 
		Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.pink, 
		Color.red, Color.white, Color.yellow};

// List of colors that is not yet used
public static List<Color> unusedColorList;

// Initialize a console
static WindowProperties properties = new WindowProperties().lineWrapping(true);
static Console console = new Console(new Window("RANDOMNESS", properties));

// Get the current screen size info
static Dimension maxScreenSize = Toolkit.getDefaultToolkit().getScreenSize();

public static void main(String[] args) 
{	
	do
	{
		console.clear();	// Clear the console output screen
		console.sleep(500);	// Wait half second
		resetRandomColorList();	// Fill the list with random colors
		
		// A dummy text
		console.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Duis blandit volutpat tempus. Integer vitae tristique sem. Phasellus "
				+ "vel justo eget sapien ultricies interdum. Pellentesque ultricies id erat "
				+ "a pharetra. Mauris in est ut felis molestie gravida. Praesent nisl turpis\n");
		
		console.sleep(250);	// Wait 0.25 second
		
		// 5 second countdown loop
		for(int i = 5; i >= 0; i--)
		{
			console.println(i+" seconds after next RANDOMNESS");	// Print how many seconds left before countdown ends
			console.sleepSec(1);	// Wait 1 second
			console.clearll();	// Clear last line to print text that showing seconds again
		}
		
		// Give random color to every element of console
		randomness();
	} while (true);
}

private static void randomness() 
{
	/*  Change the attributes of WindowProperties instance
		*  Every change applies automatically to Window class
		*  Which is repaint Console elements
		*/ 
	properties.inputSelectedTextColor(getRandomColor())
		.inputTextCaretColor(getRandomColor())
		.outputSelectedColor(getRandomColor())
		.inputTextBackground(getRandomColor())
		.outputTextBackground(getRandomColor())
		.inputTextForeground(getRandomColor())
		.outputTextForeground(getRandomColor())
		.inputSelectedTextColor(getRandomColor());
	
	// Adjust the console size to a randomly determined value.
	properties.windowWidth(new Random().nextInt(300, 950)).windowHeight(new Random().nextInt(350,800));
	
	// Adjust the console position to a randomly determined value between 0 and maxScreenSize variable.
	properties.windowPosX(new Random().nextInt(maxScreenSize.width)).windowHeight(new Random().nextInt(maxScreenSize.height));
}

private static Color getRandomColor() 
{
	int randomIndex = new Random().nextInt(unusedColorList.size());	// Generate a random number
	
	Color selectedColor = unusedColorList.get(randomIndex);	// Use this number to get a random index from Color List
	unusedColorList.remove(selectedColor); // Exclude this chosen color from the unusedColorList to ensure it is not used more than once.
	
	return selectedColor;
}

private static void resetRandomColorList() 
{
	unusedColorList = new LinkedList<Color>(Arrays.asList(colorList)); // Restore unusedColorList to enable the reuse of all colors once more.
}
```
