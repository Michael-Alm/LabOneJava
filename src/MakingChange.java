import javax.swing.*;

public class MakingChange {

    public static void main(String[] args) {
        // Create the frame for the application
        JFrame frame = new JFrame("Making Change Test Window");

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the RegisterPanel to the frame
        frame.add(new RegisterPanel());

        // Set the frame size
        frame.setSize(400, 300);

        // Make the frame visible
        frame.setVisible(true);
    }
}
