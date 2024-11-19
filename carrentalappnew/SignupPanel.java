/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrentalappnew;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.HashMap;



/**
 *
 * @author ASUS TUF
 */
public class SignupPanel extends JPanel {
    private WebManager webManager;
   // Static user database
    private static HashMap<String, String> userDatabase = new HashMap<>();

    // Getter method for userDatabase
    public static HashMap<String, String> getUserDatabase() {
        return userDatabase;
    }


    public SignupPanel(WebManager webManager) {
        this.webManager = webManager;
        setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        // Add Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/logo.png"));
        JLabel logoLabel;
        if (logoIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            Image logoImage = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            logoLabel = new JLabel(new ImageIcon(logoImage));
        } else {
            logoLabel = new JLabel("LOGO");
        }
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(logoLabel, BorderLayout.CENTER);

        // Title
        JLabel titleLabel = new JLabel("The Best Rental Spot For You");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.RED);
        topPanel.add(titleLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(null);
        bottomPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(250, 20, 100, 30);
        bottomPanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(320, 20, 200, 30);
        bottomPanel.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(250, 70, 100, 30);
        bottomPanel.add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(320, 70, 200, 30);
        bottomPanel.add(ageField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(250, 120, 100, 30);
        bottomPanel.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(320, 120, 200, 30);
        bottomPanel.add(phoneField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(250, 170, 100, 30);
        bottomPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(320, 170, 200, 30);
        bottomPanel.add(passwordField);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(300, 220, 90, 30);
        bottomPanel.add(signupButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(410, 220, 90, 30);
        bottomPanel.add(backButton);

        // Action Listener for Sign Up Button
signupButton.addActionListener(e -> {
    try {
        String username = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String phoneText = phoneField.getText().trim();
        String password = new String(passwordField.getPassword()).trim(); // Get the password

        // Validate Age
        int age = Integer.parseInt(ageText);
        if (age < 18) {
            throw new IllegalArgumentException("18+ only. Please enter a valid age.");
        }

        // Validate Phone Number
        if (phoneText.length() < 10 || phoneText.length() > 11 || !phoneText.matches("\\d+")) {
            throw new IllegalArgumentException("Please enter a valid phone number (10-11 digits).");
        }

        // Validate Password
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        // Check if username already exists
        if (userDatabase.containsKey(username)) {
            throw new IllegalArgumentException("Username already exists. Please choose a different username.");
        }

        // Register the user
        userDatabase.put(username, password);

        JOptionPane.showMessageDialog(SignupPanel.this, "Signup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        webManager.showPanel("Login");
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(SignupPanel.this, "Invalid input. Please enter valid details.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(SignupPanel.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(SignupPanel.this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
});

        backButton.addActionListener(e -> webManager.showPanel("Login"));

        add(bottomPanel, BorderLayout.CENTER);
    }
}

