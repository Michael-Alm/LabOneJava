import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel {
    private Register register;
    private JTextField input;
    private PursePanel changePanel;
    private Purse currentPurse;  // Keep track of current purse

    public RegisterPanel(Register register) {
        this.register = register;
        this.setLayout(new BorderLayout());

        input = new JTextField(10);
        JPanel inputPanel = new JPanel();
        inputPanel.add(input);
        this.add(inputPanel, BorderLayout.NORTH);

        // Create initial empty purse and store reference
        currentPurse = new Purse(register.getDenominations());

        // Create PursePanel with initial purse
        changePanel = new PursePanel(currentPurse);
        this.add(changePanel, BorderLayout.CENTER);

        input.addActionListener(new InputListener());
    }

    private class InputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(input.getText());
                // Get new purse from register
                Purse newPurse = register.makeChange(amount);

                // Transfer observers from old purse to new purse
                // This is key to maintaining the observer relationship
                newPurse.addObserver(changePanel);

                // Update current purse reference
                currentPurse = newPurse;

                // Manually trigger update for initial display
                changePanel.onPurseChanged(currentPurse);

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}