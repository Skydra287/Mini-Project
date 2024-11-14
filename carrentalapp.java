import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

// Main Application Entry Point
public class carrentalapp {
    public static void main(String[] args) {
        new WelcomeScreen(); // Launch the Welcome Screen
    }
}

// Welcome Screen class
class WelcomeScreen extends JFrame {
    WelcomeScreen() {
        setTitle("WELCOME");
        setLayout(new BorderLayout());

        add(new JLabel("WELCOME TO SEWAAKAR", SwingConstants.CENTER), BorderLayout.CENTER);

        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(e -> {
            dispose();
            new IDDetailsScreen();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

// ID Details Screen class
class IDDetailsScreen extends JFrame {
    JTextField emailField, phoneField;

    IDDetailsScreen() {
        setTitle("ID DETAILS");
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("E-MAIL"));
        emailField = new JTextField(20);
        add(emailField);

        add(new JLabel("PHONE"));
        phoneField = new JTextField(20);
        add(phoneField);

        JButton nextBtn = new JButton("Next");
        nextBtn.addActionListener(e -> {
            try {
                // Validate email and phone fields
                String email = emailField.getText();
                String phone = phoneField.getText();

                // Check if the email and phone number fields are empty
                if (email.isEmpty() || phone.isEmpty()) {
                    throw new IllegalArgumentException("Both fields must be filled.");
                }

                // Add simple email format validation (optional)
                if (!email.contains("@") || !email.contains(".")) {
                    throw new IllegalArgumentException("Please enter a valid email address.");
                }

                // Optional phone number validation (e.g., only digits and a certain length)
                if (!phone.matches("\\d{10}")) {
                    throw new IllegalArgumentException("Please enter a valid phone number (10 digits).");
                }

                // If validation is successful, proceed to the next screen
                dispose();
                new CarSelectionScreen(email, phone);
            } catch (IllegalArgumentException ex) {
                // Show error message
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // Handle any other unexpected errors
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextBtn);
        add(buttonPanel);

        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

// Car Selection Screen class
class CarSelectionScreen extends JFrame {
    String email, phone;

    CarSelectionScreen(String email, String phone) {
        this.email = email;
        this.phone = phone;

        setTitle("CAR OPTION");
        setLayout(new BorderLayout());

        JPanel carButtonsPanel = new JPanel();
        carButtonsPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton myviBtn = new JButton("1. Myvi");
        JButton hondaCityBtn = new JButton("2. Honda City");
        JButton vellfireBtn = new JButton("3. Vellfire");

        myviBtn.addActionListener(e -> openCarDetails("Myvi", "C:\\Users\\haziq\\Downloads\\image for java pbl\\myvi.jpeg", 50));
        hondaCityBtn.addActionListener(e -> openCarDetails("Honda City", "C:\\Users\\haziq\\Downloads\\image for java pbl\\HondaCity.jpeg", 100));
        vellfireBtn.addActionListener(e -> openCarDetails("Vellfire", "C:\\Users\\haziq\\Downloads\\image for java pbl\\vellfire.jpeg", 150));

        carButtonsPanel.add(myviBtn);
        carButtonsPanel.add(hondaCityBtn);
        carButtonsPanel.add(vellfireBtn);
        add(carButtonsPanel, BorderLayout.CENTER);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            dispose();
            new IDDetailsScreen();
        });

        JPanel backButtonPanel = new JPanel();
        backButtonPanel.add(backBtn);
        add(backButtonPanel, BorderLayout.SOUTH);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void openCarDetails(String carType, String imagePath, int rate) {
        dispose();
        new CarDetailsScreen(email, phone, carType, imagePath, rate);
    }
}

// Car Details Screen class
class CarDetailsScreen extends JFrame {
    CarDetailsScreen(String email, String phone, String carType, String imagePath, int rate) {
        setTitle("CAR DETAILS");
        setLayout(new BorderLayout());

        ImageIcon carImage = new ImageIcon(imagePath);
        Image newImage = carImage.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(newImage));
        add(imageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton rentBtn = new JButton("Rent");
        rentBtn.addActionListener(e -> {
            dispose();
            new RentalDurationScreen(email, phone, carType, rate);
        });

        JButton detailBtn = new JButton("Check Details");
        detailBtn.addActionListener(e -> new CarDetailsWindow(carType, rate));

        buttonPanel.add(rentBtn);
        buttonPanel.add(detailBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            dispose();
            new CarSelectionScreen(email, phone);
        });

        JPanel backButtonPanel = new JPanel();
        backButtonPanel.add(backBtn);
        add(backButtonPanel, BorderLayout.NORTH);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

// Car Details Window class
class CarDetailsWindow extends JFrame {
    CarDetailsWindow(String carType, int rate) {
        setTitle("CAR DETAILS");

        JTextArea detailsArea = new JTextArea("CAR TYPE: " + carType + "\nAVAILABILITY: 4\nTRANSMISSION: MANUAL\nFUEL TYPE: PETROL\nRATE: RM" + rate + "/DAY");
        detailsArea.setEditable(false);
        add(detailsArea, BorderLayout.CENTER);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> dispose());

        add(closeBtn, BorderLayout.SOUTH);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

// Rental Duration Screen class
class RentalDurationScreen extends JFrame {
    RentalDurationScreen(String email, String phone, String carType, int rate) {
        setTitle("ENTER RENTAL DURATION");
        setLayout(new BorderLayout());

        // Label to prompt for rental days
        JLabel promptLabel = new JLabel("Enter the number of days you wish to rent the car.", SwingConstants.CENTER);
        add(promptLabel, BorderLayout.NORTH);

        //JTextField for entering the rental days
        JTextField daysField = new JTextField(1);
        add(daysField, BorderLayout.CENTER);

        // panel for the bottom area to hold the pickup note and Next button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        //note to the bottom panel
        JLabel pickupNote = new JLabel("NOTE: THE CAR WILL BE READY TO PICKUP A DAY AFTER THE BOOKING IS MADE");
        bottomPanel.add(pickupNote, BorderLayout.NORTH);

        // Next button with action listener
        JButton nextBtn = new JButton("Next");
        nextBtn.addActionListener(e -> {
            try {
                int days = Integer.parseInt(daysField.getText());
                dispose(); // Close the current screen and move to the next
                new PaymentMethodScreen(email, phone, carType, rate, days);
            } catch (NumberFormatException ex) {
                // Handle error if the input is not a valid integer
                JOptionPane.showMessageDialog(this, "ERROR: PLEASE INPUT A VALID INTEGER FOR THE RENTAL DAYS", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        bottomPanel.add(nextBtn, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        //frame size and location
        setSize(500, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

// Payment Method Screen class
class PaymentMethodScreen extends JFrame {
    PaymentMethodScreen(String email, String phone, String carType, int rate, int days) {
        setTitle("PAYMENT METHOD");
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton debitBtn = new JButton("Debit Card");
        JButton creditBtn = new JButton("Credit Card");
        JButton cashBtn = new JButton("Cash");

        ActionListener paymentListener = e -> {
            dispose();
            new RentalSummaryScreen(email, phone, carType, rate, days, e.getActionCommand());
        };

        debitBtn.addActionListener(paymentListener);
        creditBtn.addActionListener(paymentListener);
        cashBtn.addActionListener(paymentListener);

        add(debitBtn);
        add(creditBtn);
        add(cashBtn);

        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

// Rental Summary Screen class
class RentalSummaryScreen extends JFrame {
    RentalSummaryScreen(String email, String phone, String carType, int rate, int days, String paymentMethod) {
        setTitle("RENTAL SUMMARY");
        setLayout(new BorderLayout());

        JTextArea summaryArea = new JTextArea("E-MAIL: " + email + "\nPHONE NO: " + phone + "\nCAR TYPE: " + carType +
                "\nRENTAL DAYS: " + days + "\nTOTAL COST: RM" + (rate * days) + "\nPAYMENT METHOD: " + paymentMethod);
        add(summaryArea, BorderLayout.CENTER);

        JButton nextBtn = new JButton("Next");
        nextBtn.addActionListener(e -> {
            dispose();
            new ContinueScreen();
        });

        add(nextBtn, BorderLayout.SOUTH);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

// Continue Screen class
class ContinueScreen extends JFrame {
    ContinueScreen() {
        setTitle("CONTINUE?");
        setLayout(new BorderLayout());

        add(new JLabel("DO YOU WANT TO RENT ANOTHER CAR?", SwingConstants.CENTER), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton yesBtn = new JButton("Yes");
        yesBtn.addActionListener(e -> {
            dispose();
            new CarSelectionScreen("", "");
        });

        JButton noBtn = new JButton("No");
        noBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(yesBtn);
        buttonPanel.add(noBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
