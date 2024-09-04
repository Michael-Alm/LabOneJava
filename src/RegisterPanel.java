import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    private Register register;        // Holds a reference to the Register object responsible for calculating change
    private JTextField input;         // Text field where the user inputs the amount for which change is needed
    private PursePanel changePanel;   // Panel that displays the calculated change

    // Constructor for RegisterPanel that accepts a Register object
    public RegisterPanel(Register register) {
        this.register = register;     // Assign the provided Register object to this instance

        // Set the layout manager for this panel to BorderLayout for arranging components
        this.setLayout(new BorderLayout());

        // Create a text input field with 10 columns for user input
        input = new JTextField(10);

        // Create a separate panel to hold the input field and add the input field to it
        JPanel inputPanel = new JPanel();
        inputPanel.add(input);

        // Add the input panel to the top (north region) of this panel
        this.add(inputPanel, BorderLayout.NORTH);

        // Initialize the PursePanel with a new Purse created using the denominations from the register
        changePanel = new PursePanel(new Purse(register.getDenominations()));  // Updated to pass denominations to Purse
        this.add(changePanel, BorderLayout.CENTER);  // Add the change display panel to the center region

        // Attach an action listener to the input field to respond when the user presses "Enter"
        input.addActionListener(new InputListener());
    }

    // Private inner class that implements ActionListener to handle user input actions
    private class InputListener implements ActionListener {

        // Method triggered when an action event occurs, such as pressing "Enter"
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Attempt to convert the input text to a double value
                double amount = Double.parseDouble(input.getText());

                // Use the Register object to compute the change for the entered amount
                Purse change = register.makeChange(amount);

                // Update the PursePanel to show the newly calculated change
                changePanel.setPurse(change);

            } catch (NumberFormatException ex) {
                // If the input is not a valid number, display an error message
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
