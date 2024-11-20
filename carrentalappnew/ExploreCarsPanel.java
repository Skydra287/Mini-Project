/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrentalappnew;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ASUS TUF
 */
public class ExploreCarsPanel extends JPanel {

    public ExploreCarsPanel(WebManager webManager) {
        setLayout(null); // Use absolute positioning
        setBackground(new Color(184, 75, 75)); // Set background color for the entire panel


        // Title
        JLabel titleLabel = new JLabel("RENT THE HIGH END CARS EASILY");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBounds(145, 20, 500, 30);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        // Car Details
        String[] carNames = {"Toyota Alphard", "Audi R8", "BMW M4 Competition", "Lamborghini Urus"};
        String[] carPrices = {"RM 1,000/day", "RM 2,100/day", "RM 1,200/day", "RM 2,300/day"};
       String[] carFeatures = {
        "<html>4.5 ✯, 6 Passengers, Auto<br>Fuel: Petrol<br>Engine: 2.5L V6</html>",
        "<html>5.0 ✯, 2 Passengers, Auto<br>Fuel: Petrol<br>Engine: 4.2L V8</html>",
        "<html>4.8 ✯, 4 Passengers, Auto<br>Fuel: Diesel<br>Engine: 3.0L V6</html>",
        "<html>5.0 ✯, 4 Passengers, Auto<br>Fuel: Petrol<br>Engine: 4.0L V8</html>"
         };

        // Image paths for cars
        String[] carImages = {
            "/alphard.png",
            "/audir8.png",
            "/bmw.png",
            "/lamborghini.png"
        };

        int xStart = 19; // Starting x position
        int xSpacing = 190; // Horizontal spacing between cars
        int yPosition = 100; // Vertical position for all cars
        for (int i = 0; i < carNames.length; i++) {
            // Car Panel
            JPanel carPanel = new JPanel();
            carPanel.setLayout(null);
            carPanel.setBounds(xStart + (i * xSpacing), yPosition, 180, 300);
            carPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            carPanel.setBackground(new Color(229,184,11));
            add(carPanel);

            // Car Name
            JLabel carNameLabel = new JLabel(carNames[i]);
            carNameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            carNameLabel.setBounds(10, 10, 160, 20);
            carPanel.add(carNameLabel);

            // Car Image
            ImageIcon carIcon = new ImageIcon(getClass().getResource(carImages[i])); // Load car image
            Image carImage = carIcon.getImage().getScaledInstance(160, 100, Image.SCALE_SMOOTH); // Resize image
            JLabel carImageLabel = new JLabel(new ImageIcon(carImage));
            carImageLabel.setBounds(10, 40, 160, 100);
            carPanel.add(carImageLabel);

            // Car Features
            JLabel carFeaturesLabel = new JLabel(carFeatures[i]);
            carFeaturesLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            carFeaturesLabel.setBounds(10, 130, 160, 120); // Ensure height is sufficient
            carPanel.add(carFeaturesLabel);

            // Car Price
            JLabel carPriceLabel = new JLabel(carPrices[i]);
            carPriceLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
            carPriceLabel.setBounds(10, 220, 160, 20);
            carPriceLabel.setForeground(Color.RED);
            carPanel.add(carPriceLabel);

            // Rent Now Button
            JButton rentNowButton = new JButton("Rent Now");
            rentNowButton.setBounds(40, 250, 100, 30);
            rentNowButton.setForeground(Color.WHITE);
            rentNowButton.setBackground(Color.BLACK);
            carPanel.add(rentNowButton);

            int carIndex = i; // Needed for action listener
            rentNowButton.addActionListener(e -> {
            String selectedCar = carNames[carIndex]; // Get selected car
            webManager.setCurrentCar(selectedCar);   // Store selected car in WebManager
            webManager.showPanel("RentalDate");      // Navigate to Rental Date Panel
            System.out.println("Current car set to: " + selectedCar);
            System.out.println("Current car set to: " + webManager.getCurrentCar());


            });




       }
        // Show Receipt Button
        JButton showReceiptButton = new JButton("Show Receipt");
        showReceiptButton.setBounds(50, 500, 150, 30);
        showReceiptButton.setForeground(Color.BLACK);
        showReceiptButton.setBackground(new Color (234,210,168));
        add(showReceiptButton);

        showReceiptButton.addActionListener(e -> {
        String receipt = webManager.getReceipt(webManager.getCurrentUser());
    if (receipt == null) {
        JOptionPane.showMessageDialog(
            this, 
            "No rental receipt yet!", 
            "Receipt", 
            JOptionPane.INFORMATION_MESSAGE
        );
    } else {
        JTextArea receiptArea = new JTextArea(receipt);
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(
            this,
            new JScrollPane(receiptArea),
            "Your Rental Receipt",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
});


// Exit Button
JButton exitButton = new JButton("Exit");
exitButton.setBounds(250, 500, 150, 30);
exitButton.setForeground(Color.BLACK);
exitButton.setBackground(new Color (234,210,168));
add(exitButton);

exitButton.addActionListener(e -> {
    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to exit?",
        "Confirm Exit",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
        System.exit(0); // Exit the application
    }
});


    // Logout Button
    JButton logoutButton = new JButton("Logout");
    logoutButton.setBounds(50, 20, 100, 30); // Adjust position and size if needed
    logoutButton.setForeground(Color.BLACK);
    logoutButton.setBackground(new Color (234,210,168));
    add(logoutButton);

    logoutButton.addActionListener(e -> {
    // Show a message to confirm logout and notify about order clearing
    JOptionPane.showMessageDialog(
        this, 
        "Log out... Orders cleared.", 
        "Logout", 
        JOptionPane.INFORMATION_MESSAGE
    );

    // Clear user session or any necessary data
    webManager.setCurrentUser(null);

    // Clear all existing bookings
    webManager.getBookings().clear();

    // Redirect to the Login panel
    webManager.showPanel("Login");
    });




        // orders Button
        JButton SummaryButton = new JButton("Orders");
        SummaryButton.setBounds(690, 20, 80, 30);
        SummaryButton.setForeground(Color.BLACK);
        SummaryButton.setBackground(new Color (234,210,168));
        add(SummaryButton);

       SummaryButton.addActionListener(e -> {
    System.out.println("Orders button clicked.");
    if (webManager.getBookings().isEmpty()) {
        JOptionPane.showMessageDialog(
            this,
            "No car has been added to the rentals yet!",
            "Cart is Empty",
            JOptionPane.WARNING_MESSAGE
        );
        System.out.println("Cart is empty.");
    } else {
        System.out.println("Navigating to Rental Summary.");
        webManager.showPanel("RentalSummary");
    }
});



    }
}
