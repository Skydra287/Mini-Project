/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrentalappnew;
import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.util.HashMap;
import java.util.Date;


/**
 *
 * @author ASUS TUF
 */
public class RentalDatePanel extends JPanel {
    private WebManager webManager;
    private JLabel selectedCarLabel; // Only declare it once here
    private JLabel carImageLabel; // Label to display car image
    private HashMap<String, String> carImages; // Map to store car images
    private JLabel statusLabel;
    private JDateChooser startDateChooser;
    private JTextField rentalDaysField;
    private JCheckBox gpsCheckbox;
    private JCheckBox insuranceCheckbox;
    private JCheckBox childSeatCheckbox;

    public RentalDatePanel(WebManager webManager) {
        this.webManager = webManager;
        setLayout(null);
        setBackground(new Color(184, 75, 75)); // Set background color for the entire panel
        
      // Initialize and add the start date chooser
    startDateChooser = new JDateChooser();
    startDateChooser.setBounds(550, 100, 150, 30);

    // Prevent selection of past dates
    startDateChooser.setMinSelectableDate(new Date()); // Today's date as the minimum selectable date

    // Disable manual entry in the date chooser
    startDateChooser.getDateEditor().setEnabled(false);

    // Optionally set today's date as default
    startDateChooser.setDate(new Date());

    add(startDateChooser);

        // Initialize and add the rental days field
        rentalDaysField = new JTextField();
        rentalDaysField.setBounds(550, 50, 130, 30);
        add(rentalDaysField);
        
         // Initialize car images
        carImages = new HashMap<>();
        carImages.put("Toyota Alphard", "/alphard.png");
        carImages.put("Audi R8", "/audir8.png");
        carImages.put("BMW M4 Competition", "/bmw.png");
        carImages.put("Lamborghini Urus", "/lamborghini.png");
        
    // Create a panel with a red border
    JPanel redBoxPanel = new JPanel();
    redBoxPanel.setBounds(40, 15, 340, 45); // Set position and size for the red box
    redBoxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); // Red border with thickness 3
    redBoxPanel.setBackground(new Color(184, 75, 75));
    redBoxPanel.setLayout(null); // Use absolute positioning for inner components

        // Initialize and add the selected car label
        selectedCarLabel = new JLabel("Selected Car: Not selected");
        selectedCarLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        selectedCarLabel.setBounds(50, 20, 400, 30);
        selectedCarLabel.setForeground(Color.BLACK);
        selectedCarLabel.setBackground(new Color(184, 75, 75));
        add(selectedCarLabel);
        
         add(redBoxPanel);
  
        // Car Image
       carImageLabel = new JLabel();
    carImageLabel.setBounds(10, 300, 500, 240); // Set bounds for the image
    add(carImageLabel);
    
        // Diamond Icon
        ImageIcon diamondIcon = new ImageIcon(getClass().getResource("/diamond.png")); // Path to the diamond image
        Image scaledIcon = diamondIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Resize the icon
        diamondIcon = new ImageIcon(scaledIcon);

        // "Luxury is for all" with Icon
        JLabel luxuryLabel = new JLabel("Luxury is for all", diamondIcon, JLabel.LEFT);
        luxuryLabel.setFont(new Font("ROBOTO", Font.BOLD, 24));
        luxuryLabel.setForeground(Color.WHITE);
        luxuryLabel.setBounds(475, 260, 300, 30); // Adjust size and position as needed
        add(luxuryLabel);
        
                // Hand Icon
        ImageIcon handIcon = new ImageIcon(getClass().getResource("/hand.png")); // Path to the hand image
        Image scaledHandIcon = handIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Resize the icon
        handIcon = new ImageIcon(scaledHandIcon);

        // "Easy Process" with Hand Icon
        JLabel easyLabel = new JLabel("Easy Process", handIcon, JLabel.LEFT);
        easyLabel.setFont(new Font("ROBOTO", Font.BOLD, 24));
        easyLabel.setForeground(Color.WHITE);
        easyLabel.setBounds(475, 300, 300, 30); // Adjust size and position as needed
        add(easyLabel);

        JLabel DescLabel = new JLabel("The Most Premium ");
        DescLabel.setFont(new Font("ROBOTO", Font.BOLD, 24));
        DescLabel.setForeground(Color.WHITE);
        DescLabel.setBounds(30, 80, 240, 30);
        add(DescLabel);

        JLabel DescLabel2 = new JLabel("Car You Can Get");
        DescLabel2.setFont(new Font("ROBOTO", Font.BOLD, 24));
        DescLabel2.setForeground(Color.WHITE);
        DescLabel2.setBounds(30, 110, 240, 30);
        add(DescLabel2);
        
        JLabel DescLabel3 = new JLabel("Why Choose us?");
        DescLabel3.setFont(new Font("ROBOTO", Font.BOLD, 24));
        DescLabel3.setForeground(Color.BLACK);
        DescLabel3.setBounds(480, 225, 240, 30);
        add(DescLabel3);


        // Rental Days Label and Field
        JLabel rentalDaysLabel = new JLabel("Enter rental days:");
        rentalDaysLabel.setBounds(450, 50, 150, 30);
        rentalDaysLabel.setForeground(Color.WHITE);
        add(rentalDaysLabel);


        // Start Date Label and Field
        JLabel startDateLabel = new JLabel("Select start date:");
        startDateLabel.setBounds(450, 100, 150, 30);
        startDateLabel.setForeground(Color.WHITE);
        add(startDateLabel);

        // Clock Icon
        ImageIcon clockIcon = new ImageIcon(getClass().getResource("/clock.png"));
        JLabel clockLabel = new JLabel(new ImageIcon(clockIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        clockLabel.setBounds(680, 50, 30, 30);
        add(clockLabel);
        
        // Additional Options with Checkboxes
        gpsCheckbox = new JCheckBox("GPS (RM 50/day)");
        gpsCheckbox.setBounds(50, 150, 150, 30);
        gpsCheckbox.setForeground(Color.WHITE);
        gpsCheckbox.setBackground(new Color(184, 75, 75));
        add(gpsCheckbox);

        insuranceCheckbox = new JCheckBox("Insurance (RM 100/day)");
        insuranceCheckbox.setBounds(50, 190, 220, 30);
        insuranceCheckbox.setForeground(Color.WHITE);
        insuranceCheckbox.setBackground(new Color(184, 75, 75));
        add(insuranceCheckbox);

        childSeatCheckbox = new JCheckBox("Child Seat (RM 30/day)");
        childSeatCheckbox.setBounds(50, 230, 220, 30);
        childSeatCheckbox.setForeground(Color.WHITE);
        childSeatCheckbox.setBackground(new Color(184, 75, 75));
        add(childSeatCheckbox);
        
       // Add status label to display car availability
    statusLabel = new JLabel();
    statusLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
    statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
    statusLabel.setForeground(Color.RED); // Default text color
    statusLabel.setBounds(50, 400, 300, 30); // Adjust position and size
    statusLabel.setVisible(false); // Initially hidden
    add(statusLabel);


        
        // Next Button
    JButton nextButton = new JButton("Next");
    nextButton.setBounds(600, 450, 100, 30); // Set position and size
    nextButton.setForeground(Color.BLACK);
    nextButton.setBackground(new Color (234,210,168));
    add(nextButton); // Add to the panel
    nextButton.addActionListener(e -> {
    // Get input values
    String rentalDaysText = rentalDaysField.getText().trim();
    Date startDate = startDateChooser.getDate();

    // Validate inputs
    if (rentalDaysText.isEmpty() || startDate == null) {
        JOptionPane.showMessageDialog(this, "Please fill or choose out all fields correctly", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int days;
    try {
        days = Integer.parseInt(rentalDaysText);

        // Check for invalid or excessive rental days
        if (days <= 0) {
            JOptionPane.showMessageDialog(this, "Rental days must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (days > 180) {
            JOptionPane.showMessageDialog(this, "You cannot rent a car for more than 6 months (180 days).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid number of rental days.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Check car availability
    String car = webManager.getCurrentCar();
    if (!webManager.isAvailable(car, startDate, days)) {
        JOptionPane.showMessageDialog(this, "Sorry, that car is fully rented for the selected date. Try choosing another date!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Book the car
    webManager.bookCar(car, startDate, days);

    // Refresh the GUI after booking
    refreshCarAvailability();

    // Calculate base price based on selected car
    int basePrice = days * (webManager.getCurrentCar().equals("Toyota Alphard") ? 1000 :
                            webManager.getCurrentCar().equals("Audi R8") ? 2100 :
                            webManager.getCurrentCar().equals("BMW M4 Competition") ? 1200 :
                            2300);

    // Calculate additional costs
    int gpsCost = gpsCheckbox.isSelected() ? 50 * days : 0;
    int insuranceCost = insuranceCheckbox.isSelected() ? 100 * days : 0;
    int childSeatCost = childSeatCheckbox.isSelected() ? 30 * days : 0;

    // Calculate add-on price and total price
    int addOnPrice = gpsCost + insuranceCost + childSeatCost;
    int totalPrice = basePrice + addOnPrice;

    // Format start date
    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String startDateStr = dateFormat.format(startDate);

    // Create a new booking
    BookingDetails bookingDetails = new BookingDetails(
    webManager.getCurrentCar(),
    startDateStr + " for " + days + " days",
    days,
    basePrice,      // Base car price
    addOnPrice,     // Add-on price
    gpsCheckbox.isSelected(),
    insuranceCheckbox.isSelected()
    );

    // Add booking to webManager
    if (!webManager.addBooking(bookingDetails)) {
    JOptionPane.showMessageDialog(
        this,
        "You can only rent up to 4 cars!",
        "Limit Reached",
        JOptionPane.WARNING_MESSAGE
    );
    } else {
    JOptionPane.showMessageDialog(
        this,
        "Booking successful for " + webManager.getCurrentCar() + "!",
        "Success",
        JOptionPane.INFORMATION_MESSAGE
    );
    webManager.showPanel("RentalSummary");
    }

    });



        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(450, 450, 100, 30);
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(new Color (234,210,168));
        add(backButton);

        backButton.addActionListener(e -> webManager.showPanel("ExploreCars"));
    }
     // Method to update the selected car dynamically
    // Method to update the selected car dynamically
    public void updateSelectedCar() {
        String selectedCar = webManager.getCurrentCar();
        selectedCarLabel.setText("Selected Car: " + (selectedCar != null ? selectedCar : "Not selected"));
    // Update the car image
        if (selectedCar != null && carImages.containsKey(selectedCar)) {
            String imagePath = carImages.get(selectedCar);
            ImageIcon carIcon = new ImageIcon(getClass().getResource(imagePath));
            carImageLabel.setIcon(new ImageIcon(carIcon.getImage().getScaledInstance(400, 240, Image.SCALE_SMOOTH)));
        } else {
            carImageLabel.setIcon(null); // Clear the image if no car is selected
        }
  
    }

   public void refreshCarAvailability() {
    String car = webManager.getCurrentCar();
    
    if (car != null) {
        int available = webManager.getCarInventory(car);

        if (available <= 0) {
            statusLabel.setText("Car is unavailable.");
            statusLabel.setForeground(Color.RED);
        } 
    } else {
        statusLabel.setText("No car selected.");
        statusLabel.setForeground(Color.RED);
        statusLabel.setVisible(true);
         revalidate();
    repaint();
    }
    
}
   public void resetFields() {
    startDateChooser.setDate(null); // Clear selected date
    rentalDaysField.setText("");    // Clear rental days input
    gpsCheckbox.setSelected(false); // Uncheck GPS option
    insuranceCheckbox.setSelected(false); // Uncheck Insurance option
    childSeatCheckbox.setSelected(false); // Uncheck Child Seat option
}





}

    
