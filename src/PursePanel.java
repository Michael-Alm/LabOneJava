import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Map;
import java.util.HashMap;

public class PursePanel extends JPanel implements PurseObserver {
    private Purse purse;
    private Map<String, Image> denominationImages;

    public PursePanel(Purse purse) {
        this.purse = purse;
        this.denominationImages = new HashMap<>();
        loadDenominationImages();
        purse.addObserver(this);  // Register as an observer
    }

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

    private Image loadImage(String imagePath) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(imagePath));
            return icon.getImage();
        } catch (NullPointerException e) {
            System.err.println("Image not found: " + imagePath);
            return null;
        }
    }

    @Override
    public void onPurseChanged(Purse newPurse) {
        if (newPurse != null) {
            // Remove self as observer from old purse if it exists
            if (this.purse != null) {
                this.purse.removeObserver(this);
            }

            // Update to new purse and add self as observer
            this.purse = newPurse;
            this.purse.addObserver(this);

            // Request repaint
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (purse != null) {
            int x = 10;
            int y = 10;

            Map<Denomination, Integer> cash = purse.getCash();

            for (Denomination denomination : cash.keySet()) {
                int count = cash.get(denomination);
                Image img = denominationImages.get(denomination.name());

                if (img != null) {
                    for (int i = 0; i < count; i++) {
                        g.drawImage(img, x, y, null);
                        x += img.getWidth(null) + 10;

                        if (x > getWidth() - img.getWidth(null)) {
                            x = 10;
                            y += img.getHeight(null) + 10;
                        }
                    }
                } else {
                    System.err.println("No image found for denomination: " + denomination.name());
                }
            }
        }
    }
}