/*/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carrentalapp;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;
import java.util.Date;




/**
 *
 * @author ASUS TUF
 */
public class Carrentalapp {
public static Map<String, Map<String, Integer>> carAvailability = new HashMap<>();


    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
         // Initialize car availability for the next 30 days
    String[] carTypes = {"Honda City", "Myvi", "Vellfire"};
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    for (String car : carTypes) {
        Map<String, Integer> availability = new HashMap<>();
        for (int i = 0; i < 30; i++) { // Initialize availability for the next 30 days
            String date = sdf.format(calendar.getTime());
            availability.put(date, car.equals("Honda City") ? 3 : car.equals("Myvi") ? 4 : 2);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        carAvailability.put(car, availability);
        calendar.setTimeInMillis(System.currentTimeMillis()); // Reset calendar to today's date
    }
        
        new WelcomeScreen(); // Launch the Welcome Screen
    }
}

// Welcome Screen class
class WelcomeScreen extends JFrame {
    WelcomeScreen() {
        setTitle("SewaKaar - Car Rental");
        setLayout(new BorderLayout());
        
        // Main Description Panel (Top/NORTH)
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionPanel.setBorder(new EmptyBorder(20, 0, 10, 0)); // Add padding at the top and bottom

        // Main header
        JLabel headerLabel = new JLabel("BOOK THE BEST CAR EASILY", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.BLACK);
        
        // Subheader for logo text
        JLabel subHeaderLabel = new JLabel("SEWAKAAR", SwingConstants.CENTER);
        subHeaderLabel.setFont(new Font("Arial", Font.BOLD, 24));
        subHeaderLabel.setForeground(Color.RED);
        
        descriptionPanel.add(headerLabel, BorderLayout.NORTH);
        descriptionPanel.add(subHeaderLabel, BorderLayout.SOUTH);
        add(descriptionPanel, BorderLayout.NORTH);

        // Logo Panel (Center)
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        // Load the image icon (update path to your logo image)
        ImageIcon logosewaKaar = new ImageIcon("logo.png"); // Replace with your actual image path
        Image logoImage = logosewaKaar.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Scale image if needed
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage)); // Set scaled image as icon
        
        

        logoPanel.add(logoLabel);
        add(logoPanel, BorderLayout.CENTER);

        // Start Button Panel (Bottom/SOUTH)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 0, 20, 0)); // Add padding to the bottom

        JButton startBtn = new JButton("START");
        startBtn.setBackground(Color.RED);
        startBtn.setForeground(Color.WHITE);
        startBtn.setFont(new Font("Arial", Font.BOLD, 18));
        startBtn.setFocusPainted(false);
        startBtn.setPreferredSize(new Dimension(100, 40)); // Set button size
        startBtn.addActionListener(e -> {
            dispose();
            new IDDetailsScreen();  // Proceed to the next screen
        });

        buttonPanel.add(startBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Frame settings
        setSize(400, 500);  // Adjusted size for more visual spacing
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
    }
}

// ID Details Screen class
class IDDetailsScreen extends JFrame {
    JTextField emailField, phoneField, nameField, ageField;

    IDDetailsScreen() {
        setTitle("USER DETAILS");
        setLayout(new GridLayout(5, 2, 10, 10));

        // Name
        add(new JLabel("NAME"));
        nameField = new JTextField(20);
        add(nameField);

        // Age
        add(new JLabel("AGE"));
        ageField = new JTextField(20);
        add(ageField);

        // Email
        add(new JLabel("E-MAIL"));
        emailField = new JTextField(20);
        add(emailField);

        // Phone Number
        add(new JLabel("PHONE NUMBER"));
        phoneField = new JTextField(20);
        add(phoneField);

        // Next Button
        JButton nextBtn = new JButton("Next");
        nextBtn.addActionListener(e -> {
            try {
                // Retrieve input values
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String ageText = ageField.getText();

                // Validate fields are not empty
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || ageText.isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                // Validate age
                int age = Integer.parseInt(ageText);
                if (age <= 17) {
                    throw new IllegalArgumentException("Invalid age: Must be 18 or older.");
                }

                // Validate email format
                if (!email.contains("@") || !email.contains(".")) {
                    throw new IllegalArgumentException("Please enter a valid email address.");
                }

                // Validate phone number format (e.g., only digits and exactly 10 digits)
                if (!phone.matches("\\d{10}")) {
                    throw new IllegalArgumentException("Please enter a valid phone number (10 digits).");
                }

                // If all validations pass, proceed to the CarSelectionScreen
                dispose();
                new CarSelectionScreen(name, email, phone, age);
            } catch (NumberFormatException ex) {
                // Handle invalid age input (not a number)
                JOptionPane.showMessageDialog(this, "Please enter a valid number for age.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                // Handle other validation errors
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Handle any unexpected errors
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel to hold the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextBtn);
        add(buttonPanel);

        // Frame settings
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

// Car Selection Screen class
class CarSelectionScreen extends JFrame {
    String name, email, phone;
    int age;

    CarSelectionScreen(String name, String email, String phone, int age) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;

        setTitle("car selection");
        setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("<html><center>BOOK THE BEST <span style='color:red;'>CARS</span> EASILY</center></html>", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        JLabel subTitleLabel = new JLabel("Explore our car selection", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        titlePanel.add(subTitleLabel, BorderLayout.CENTER);

        add(titlePanel, BorderLayout.NORTH);

        // Car Options Panel
        JPanel carPanel = new JPanel();
        carPanel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows for each car option

        // Car Option Buttons with Images and Descriptions
        carPanel.add(createCarOption("Myvi", "Malaysia Leading Compact Car", "myvi.png"));
        carPanel.add(createCarOption("Honda City", "The Coolest Hatchback", "honda.png"));
        carPanel.add(createCarOption("Vellfire", "True luxury is experienced", "vellfire.png"));

        add(carPanel, BorderLayout.CENTER);

        // Back Button Panel
        JPanel backButtonPanel = new JPanel();
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            dispose();
            new IDDetailsScreen();
        });
        backButtonPanel.add(backBtn);

        add(backButtonPanel, BorderLayout.SOUTH);

        // Frame settings
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createCarOption(String carName, String description, String imagePath) {
    // Panel for each car option
    JPanel carOptionPanel = new JPanel();
    carOptionPanel.setLayout(new BorderLayout());
    carOptionPanel.setBackground(Color.RED);
    carOptionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Car Image
    ImageIcon carImageIcon = new ImageIcon(imagePath);
    Image carImage = carImageIcon.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);
    JLabel carImageLabel = new JLabel(new ImageIcon(carImage));
    carOptionPanel.add(carImageLabel, BorderLayout.WEST);

    // Car Name and Description
    JPanel textPanel = new JPanel();
    textPanel.setLayout(new BorderLayout());
    textPanel.setBackground(Color.RED);
    textPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));

    JLabel carNameLabel = new JLabel(carName);
    carNameLabel.setFont(new Font("Roboto", Font.BOLD, 18));
    carNameLabel.setForeground(Color.WHITE);
    textPanel.add(carNameLabel, BorderLayout.NORTH);

    JLabel carDescLabel = new JLabel(description);
    carDescLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
    carDescLabel.setForeground(Color.BLACK);
    textPanel.add(carDescLabel, BorderLayout.CENTER);

    // Add availability info
    String today = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
int currentAvailability = Carrentalapp.carAvailability.getOrDefault(carName, new HashMap<>()).getOrDefault(today, 0);
JLabel availabilityLabel = new JLabel("Available: " + currentAvailability);
    availabilityLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
    availabilityLabel.setForeground(Color.WHITE);
    textPanel.add(availabilityLabel, BorderLayout.SOUTH);

    carOptionPanel.add(textPanel, BorderLayout.CENTER);

    // Action on clicking the car panel
    carOptionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            openCarDetails(carName, imagePath, 50); // Adjust rate as needed
        }
    });

    return carOptionPanel;
}



    // Method to open the Car Details screen
    void openCarDetails(String carType, String imagePath, int rate) {
        dispose();
        new CarDetailsScreen(name, email, phone, carType, imagePath, rate, age);
    }
}



// Car Details Screen class
class CarDetailsScreen extends JFrame {
    String name, email, phone;
    int age;

    CarDetailsScreen(String name, String email, String phone, String carType, String imagePath, int rate, int age) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;

        setTitle("CAR DETAILS");
        setLayout(new BorderLayout());

        // Car Image Panel
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new GridBagLayout()); // Center the image
        imagePanel.setBackground(Color.WHITE); // Optional: change the background to enhance appearance

        ImageIcon carImageIcon = new ImageIcon(imagePath);
        Image carImage = carImageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH); // Smaller size for the image
        JLabel carImageLabel = new JLabel(new ImageIcon(carImage));
        imagePanel.add(carImageLabel);

        add(imagePanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton rentBtn = new JButton("Rent");
rentBtn.addActionListener(e -> {
    String selectedDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date()); // Assuming you select today's date for simplicity
    if (Carrentalapp.carAvailability.containsKey(carType)) {
        Map<String, Integer> availabilityMap = Carrentalapp.carAvailability.get(carType);
        int availableCars = availabilityMap.getOrDefault(selectedDate, 0);
        if (availableCars > 0) {
            availabilityMap.put(selectedDate, availableCars - 1); // Decrement the availability for the selected date
            dispose(); // Close the current screen
            new RentalDurationScreen(name, email, phone, carType, rate, age); // Proceed to the Rental Duration Screen
        } else {
            JOptionPane.showMessageDialog(this, "No cars available for " + carType + " on " + selectedDate + ".", "Availability Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});



        JButton detailBtn = new JButton("Check Details");
        detailBtn.addActionListener(e -> new CarDetailsWindow(carType, rate));

        buttonPanel.add(rentBtn);
        buttonPanel.add(detailBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Back Button Panel
        JPanel backButtonPanel = new JPanel();
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            dispose();
            new CarSelectionScreen(name, email, phone, age); // Pass name, email, phone, and age back
        });
        backButtonPanel.add(backBtn);
        add(backButtonPanel, BorderLayout.NORTH);

        // Frame settings
        setSize(400, 500);  // Adjusted size for better visual balance
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}



// Car Details Window class
// Car Details Window class
class CarDetailsWindow extends JFrame {
    CarDetailsWindow(String carType, int rate) {
        setTitle("CAR DETAILS");

        // Get the current availability from the Carrentalapp class
        String today = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
int currentAvailability = Carrentalapp.carAvailability.getOrDefault(carType, new HashMap<>()).getOrDefault(today, 0);


        // Update the details area to include the current availability
        JTextArea detailsArea = new JTextArea("CAR TYPE: " + carType +
            "\nAVAILABILITY: " + currentAvailability +
            "\nTRANSMISSION: MANUAL\nFUEL TYPE: PETROL\nRATE: RM" + rate + "/DAY");
        detailsArea.setEditable(false);
        add(detailsArea, BorderLayout.CENTER);

        // Close Button
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dispose());

        add(closeBtn, BorderLayout.SOUTH);

        // Frame settings
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}


// Rental Duration Screen class
class RentalDurationScreen extends JFrame {
    String name, email, phone, carType;
    int rate, age;

    RentalDurationScreen(String name, String email, String phone, String carType, int rate, int age) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.carType = carType;
        this.rate = rate;
        this.age = age;

        setTitle("ENTER RENTAL DURATION");
        setLayout(new BorderLayout());

        // Main panel to hold form elements
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label for rental days input
        JLabel promptLabel = new JLabel("Enter the number of days you wish to rent the car:");
        promptLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(promptLabel);

        // JTextField for entering rental days
        JTextField daysField = new JTextField(10);
        daysField.setMaximumSize(new Dimension(200, 30));
        mainPanel.add(daysField);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer

        // Date chooser for start date
        JLabel dateLabel = new JLabel("Select Start Date:");
        dateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(dateLabel);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd-MM-yyyy");
        dateChooser.setMaximumSize(new Dimension(200, 30));
        mainPanel.add(dateChooser);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer

        // Label to display the end date
        JLabel endDateLabel = new JLabel("");
        endDateLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(endDateLabel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Next Button to proceed
        JButton nextBtn = new JButton("Next");
        nextBtn.setBackground(Color.GREEN);
        nextBtn.setForeground(Color.WHITE);
       nextBtn.addActionListener(e -> {
    try {
        int days = Integer.parseInt(daysField.getText());
        Date startDate = dateChooser.getDate();
        if (startDate == null) {
            JOptionPane.showMessageDialog(this, "Please select a start date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String startDateStr = dateFormat.format(startDate);

        // Check availability for the selected start date
        if (Carrentalapp.carAvailability.containsKey(carType)) {
            int availableCars = Carrentalapp.carAvailability.get(carType).getOrDefault(startDateStr, 0);
            if (availableCars == 0) {
                JOptionPane.showMessageDialog(this, "No cars available for " + carType + " on " + startDateStr + ".", "Availability Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Proceed to the payment screen
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        String endDateStr = dateFormat.format(calendar.getTime());

        dispose(); // Close the current screen
        new PaymentMethodScreen(name, email, phone, carType, rate, days, age, startDateStr, endDateStr);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid number of rental days.", "Error", JOptionPane.ERROR_MESSAGE);
    }
});

        buttonPanel.add(nextBtn);

        // Cancel Button to go back or cancel rent
        JButton cancelBtn = new JButton("Cancel Rent");
        cancelBtn.setBackground(Color.RED);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.addActionListener(e -> {
            dispose(); // Close the current screen
            new CarSelectionScreen(name, email, phone, age); // Return to the Car Selection screen
            JOptionPane.showMessageDialog(this, "Rental process canceled.", "Cancel", JOptionPane.INFORMATION_MESSAGE);
        });
        buttonPanel.add(cancelBtn);

        // Add main panel and button panel to the frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Frame settings
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}



// Payment Method Screen class
class PaymentMethodScreen extends JFrame {
    private String paymentMethod; // Declare paymentMethod variable
    private String selectedPaymentMethod;
    private JPanel detailPanel;
    private JTextField cardNumberField;
    private JTextField cardHolderField;
    private JComboBox<String> bankComboBox;

    PaymentMethodScreen(String name, String email, String phone, String carType, int rate, int days, int age, String startDate, String endDate) {
        setTitle("PAYMENT METHOD");
        setLayout(new BorderLayout());

        // Main panel to hold payment method options
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridLayout(4, 1, 10, 10));

        // Radio buttons for payment methods
        JRadioButton debitCardBtn = new JRadioButton("Debit Card");
        JRadioButton creditCardBtn = new JRadioButton("Credit Card");
        JRadioButton cashBtn = new JRadioButton("Cash");
        JRadioButton onlineBankingBtn = new JRadioButton("Online Banking");

        // Grouping radio buttons
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(debitCardBtn);
        paymentGroup.add(creditCardBtn);
        paymentGroup.add(cashBtn);
        paymentGroup.add(onlineBankingBtn);

        // Adding radio buttons to the panel
        paymentPanel.add(debitCardBtn);
        paymentPanel.add(creditCardBtn);
        paymentPanel.add(cashBtn);
        paymentPanel.add(onlineBankingBtn);

        add(paymentPanel, BorderLayout.NORTH);

        // Panel for additional details based on selection
        detailPanel = new JPanel();
        detailPanel.setLayout(new GridLayout(3, 2, 5, 5));
        detailPanel.setVisible(false); // Hide initially
        add(detailPanel, BorderLayout.CENTER);

        // Action listeners for payment method selection
        debitCardBtn.addActionListener(e -> showCardDetails());
        creditCardBtn.addActionListener(e -> showCardDetails());
        cashBtn.addActionListener(e -> showCashMessage());
        onlineBankingBtn.addActionListener(e -> showOnlineBankingOptions());

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton proceedBtn = new JButton("Proceed");
        proceedBtn.addActionListener(e -> processPayment(name, email, phone, carType, rate, days, age, startDate, endDate));
        buttonPanel.add(proceedBtn);

        // Back Button to go back to RentalDurationScreen
        JButton backBtn = new JButton("Back");
        backBtn.setBackground(Color.ORANGE);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> {
            dispose(); // Close the current screen
            new RentalDurationScreen(name, email, phone, carType, rate, age); // Redirect to RentalDurationScreen
        });
        buttonPanel.add(backBtn);

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Frame settings
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method to show card details fields
    private void showCardDetails() {
        detailPanel.removeAll();
        detailPanel.setVisible(true);

        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField();
        JLabel cardHolderLabel = new JLabel("Card Holder Name:");
        cardHolderField = new JTextField();

        detailPanel.add(cardNumberLabel);
        detailPanel.add(cardNumberField);
        detailPanel.add(cardHolderLabel);
        detailPanel.add(cardHolderField);

        revalidate();
        repaint();
        selectedPaymentMethod = "Card";
    }

    // Method to show a cash payment message
    private void showCashMessage() {
        detailPanel.removeAll();
        detailPanel.setVisible(true);

        JLabel cashMessageLabel = new JLabel("Please pay when you meet.");
        detailPanel.add(cashMessageLabel);

        revalidate();
        repaint();
        selectedPaymentMethod = "Cash";
    }

    // Method to show online banking options
    private void showOnlineBankingOptions() {
        detailPanel.removeAll();
        detailPanel.setVisible(true);

        JLabel bankLabel = new JLabel("Select Bank:");
        bankComboBox = new JComboBox<>(new String[]{"CIMB", "Maybank", "BSN"});

        detailPanel.add(bankLabel);
        detailPanel.add(bankComboBox);

        revalidate();
        repaint();
        selectedPaymentMethod = "Online Banking";
    }

    // Process payment based on selected method
    private void processPayment(String name, String email, String phone, String carType, int rate, int days, int age, String startDate, String endDate) {
    String paymentDetails = "";

    try {
        switch (selectedPaymentMethod) {
                case "Card":
                    String cardNumber = cardNumberField.getText();
                    String cardHolder = cardHolderField.getText();

                    // Validate card number contains only digits
                    if (cardNumber.isEmpty()) {
                        throw new IllegalArgumentException("Card number cannot be empty.");
                    }

                    if (!cardNumber.matches("\\d+")) {
                        throw new IllegalArgumentException("Invalid card number. Please enter only numbers.");
                    }

                    if (cardHolder.isEmpty()) {
                        throw new IllegalArgumentException("Card holder name cannot be empty.");
                    }

                    paymentDetails = "Card Payment\nCard Number: " + cardNumber + "\nCard Holder: " + cardHolder;
                    break;

                case "Cash":
                    paymentDetails = "Cash Payment\nPlease pay when you meet.";
                    break;

                case "Online Banking":
                    String selectedBank = (String) bankComboBox.getSelectedItem();
                    if (selectedBank == null || selectedBank.isEmpty()) {
                        throw new IllegalArgumentException("Please select a bank for online banking.");
                    }
                    paymentDetails = "Online Banking\nBank: " + selectedBank;
                    break;

                default:
                    throw new IllegalArgumentException("Please select a payment method.");
            }

            // Proceed to the Rental Summary Screen with the payment details
            dispose();
            new RentalSummaryScreen(name, email, phone, carType, rate, days, selectedPaymentMethod, paymentDetails, age, startDate, endDate);


        } catch (IllegalArgumentException e) {
            // Display error message for invalid inputs
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Catch any other unexpected errors
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}





// RentalSummaryScreen class
class RentalSummaryScreen extends JFrame {
    RentalSummaryScreen(String name, String email, String phone, String carType, int rate, int days, String paymentMethod, String paymentDetails, int age, String startDate, String endDate) {
        setTitle("Rental Summary"); // Update the frame title
        setLayout(new BorderLayout());

        // Add a title label in the top-center
        JLabel titleLabel = new JLabel("AWESOME! Your Booking Is Confirmed", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.RED); // Make the text red for emphasis
        add(titleLabel, BorderLayout.NORTH);

        // Create a text area for the summary details
        JTextArea summaryArea = new JTextArea();
        summaryArea.setEditable(false);
        summaryArea.setFont(new Font("Arial", Font.PLAIN, 16));

        // Update the summary text to include startDate and endDate
        StringBuilder summaryText = new StringBuilder();
        summaryText.append("Name: ").append(name)
                   .append("\nPhone: ").append(phone)
                   .append("\nEmail: ").append(email)
                   .append("\nCar: ").append(carType)
                   .append("\nRental Period: ").append(startDate).append(" to ").append(endDate) // Display the rental dates
                   .append("\nTotal Cost: RM").append(rate * days)
                   .append("\nPayment Method: ").append(paymentMethod)
                   .append("\n\nDetails:\n").append(paymentDetails); // Payment details included here

        summaryArea.setText(summaryText.toString());
        add(summaryArea, BorderLayout.CENTER);

        // Add a "Next" button at the bottom
        JButton nextBtn = new JButton("NEXT");
        nextBtn.setBackground(Color.RED);
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setFont(new Font("Arial", Font.BOLD, 18));
        nextBtn.addActionListener(e -> {
            dispose();
            new ContinueScreen(name, email, phone, age);
        });

        add(nextBtn, BorderLayout.SOUTH);

        // Frame settings
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}









// Continue Screen class
class ContinueScreen extends JFrame {
    ContinueScreen(String name, String email, String phone, int age) { // Add name, email, phone, and age as parameters
        setTitle("Continue?");
        setLayout(new BorderLayout());

        // Main message
        JLabel mainLabel = new JLabel("DO YOU WANT TO RENT ANOTHER CAR?", SwingConstants.CENTER);
        mainLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(mainLabel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();

        // Yes Button
        JButton yesBtn = new JButton("Yes");
        yesBtn.setBackground(Color.GREEN);
        yesBtn.setForeground(Color.WHITE);
        yesBtn.setFont(new Font("Arial", Font.BOLD, 14));
        yesBtn.addActionListener(e -> {
            dispose();
            new CarSelectionScreen(name, email, phone, age); // Redirect to Car Selection screen
        });
        buttonPanel.add(yesBtn);

        // No Button
        JButton noBtn = new JButton("No");
        noBtn.setBackground(Color.RED);
        noBtn.setForeground(Color.WHITE);
        noBtn.setFont(new Font("Arial", Font.BOLD, 14));
        noBtn.addActionListener(e -> {
            // Show a thank-you message before exiting
            JOptionPane.showMessageDialog(this, "Thank you for choosing us! Have a great day!", "Thank You", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0); // Close the application
        });
        buttonPanel.add(noBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Frame settings
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
