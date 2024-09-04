import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Map;
import java.util.HashMap;

public class PursePanel extends JPanel {
    private Purse purse; // Holds the current Purse object with its denominations and quantities
    private Map<String, Image> denominationImages; // Map to store images for each denomination

    // Constructor that initializes the Purse and loads the images for the denominations
    public PursePanel(Purse purse) {
        this.purse = purse; // Set the provided Purse object
        this.denominationImages = new HashMap<>(); // Create the map for storing denomination images
        loadDenominationImages(); // Load the denomination images into the map
    }

    // Loads images for each denomination and stores them in the map
    private void loadDenominationImages() {
        denominationImages.put("One Hundred Dollar Note", loadImage("images/100_dollar_bill.png"));
        denominationImages.put("Fifty-Dollar Note", loadImage("images/50_dollar_bill.png"));
        denominationImages.put("Twenty-Dollar Note", loadImage("images/20_dollar_bill.png"));
        denominationImages.put("Ten-Dollar Note", loadImage("images/10_dollar_bill.png"));
        denominationImages.put("Five-Dollar Note", loadImage("images/5_dollar_bill.png"));
        denominationImages.put("One-Dollar Note", loadImage("images/1_dollar_bill.png"));
        denominationImages.put("Quarter", loadImage("images/25_cent.png"));
        denominationImages.put("Dime", loadImage("images/10_cent.png"));
        denominationImages.put("Nickel", loadImage("images/05_cent.png"));
        denominationImages.put("Penny", loadImage("images/01_cent.png"));
    }

    // Helper method to load an image from a specified file path
    private Image loadImage(String imagePath) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(imagePath));
            return icon.getImage(); // Return the loaded image
        } catch (NullPointerException e) {
            // Display an error message if the image is not found and return null
            System.err.println("Image not found: " + imagePath);
            return null; // Return null if the image cannot be found
        }
    }

    // Updates the Purse object and triggers a repaint of the panel
    public void setPurse(Purse purse) {
        this.purse = purse; // Set the new Purse object
        repaint(); // Repaint the panel to reflect the updated Purse
    }

    // Override the paintComponent method to draw images for each denomination in the purse
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call the parent class's paintComponent method

        if (purse != null) {
            int x = 10; // Starting x-coordinate for drawing images
            int y = 10; // Starting y-coordinate for drawing images

            // Retrieve the denominations and their respective counts from the Purse
            Map<Denomination, Integer> cash = purse.getCash();

            // Loop through the denominations in the Purse
            for (Denomination denomination : cash.keySet()) {
                int count = cash.get(denomination); // Get the quantity of the current denomination
                Image img = denominationImages.get(denomination.name()); // Retrieve the corresponding image

                if (img != null) {
                    // Draw the image repeatedly based on the denomination's count
                    for (int i = 0; i < count; i++) {
                        g.drawImage(img, x, y, null); // Draw the image at the specified coordinates
                        x += img.getWidth(null) + 10; // Shift the x-coordinate for the next image

                        // If the x-coordinate exceeds the panel width, move to the next line
                        if (x > getWidth() - img.getWidth(null)) {
                            x = 10; // Reset x to the initial position
                            y += img.getHeight(null) + 10; // Shift y to the next row
                        }
                    }
                } else {
                    // Print an error message if the image for the current denomination is missing
                    System.err.println("No image found for denomination: " + denomination.name());
                }
            }
        }
    }
}
