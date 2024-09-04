import javax.swing.JFrame;

public class MakingChange {

    // The main method serves as the starting point of the application.
    public static void main(String[] args) {

        // Create a new JFrame window titled "Making Change"
        JFrame frame = new JFrame("Making Change");

        // Set the application to close when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the window dimensions to 500 pixels wide and 400 pixels high
        frame.setSize(500, 400);

        // Initialize a new instance of the Register class to handle the change-making process
        Register register = new Register();

        // Create a new RegisterPanel, passing in the Register instance
        RegisterPanel registerPanel = new RegisterPanel(register);

        // Add the RegisterPanel component to the JFrame
        frame.add(registerPanel);

        // Display the window on the screen
        frame.setVisible(true);
    }
}
