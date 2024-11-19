/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrentalappnew;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.HashSet;
import carrentalappnew.SignupPanel;
import java.text.SimpleDateFormat;



/**
 *
 * @author ASUS TUF
 */
public class LoginPanel extends JPanel {
    private WebManager webManager;

    // Simulated database to store registered usernames
    private static HashSet<String> registeredUsers = new HashSet<>();

    public LoginPanel(WebManager webManager) {
        this.webManager = webManager;
        setLayout(new BorderLayout());

        // Top Panel with Logo and Title
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new BorderLayout());

        // Add Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/logo.png"));
        Image logoImage = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(logoLabel, BorderLayout.CENTER);

        // Add Title Below Logo
        JLabel titleLabel = new JLabel("The Best Rental Spot For You");
        titleLabel.setFont(new Font("ROBOTO", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.RED);
        topPanel.add(titleLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // Bottom Panel with Login Fields and Buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(Color.WHITE);
        
        JLabel syaratLogin = new JLabel("please sign up if you dont have an account");
        syaratLogin.setBounds(280, 150, 300, 30);
        bottomPanel.add(syaratLogin);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(250, 20, 100, 30);
        bottomPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(320, 20, 200, 30);
        bottomPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(250, 70, 100, 30);
        bottomPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(320, 70, 200, 30);
        bottomPanel.add(passwordField);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(300, 120, 90, 30);
        bottomPanel.add(signupButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(410, 120, 90, 30);
        bottomPanel.add(loginButton);

        // Action Listener for Sign Up Button
        signupButton.addActionListener(e -> webManager.showPanel("Signup"));

        // Action Listener for Login Button
        loginButton.addActionListener(e -> {
            String currentUser = webManager.getCurrentUser();
            String userPhone = SignupPanel.getUserDatabase().get(currentUser); // Fetch phone number

            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim(); // Get the password

    // Validate username and password using SignupPanel.userDatabase
    if (!SignupPanel.getUserDatabase().containsKey(username)) {
    JOptionPane.showMessageDialog(
        LoginPanel.this,
        "Sorry, the username is not connected to an account. Please sign up.",
        "Login Error",
        JOptionPane.ERROR_MESSAGE
    );
} else if (!SignupPanel.getUserDatabase().get(username).equals(password)) {
    JOptionPane.showMessageDialog(
        LoginPanel.this,
        "Incorrect password. Please try again.",
        "Login Error",
        JOptionPane.ERROR_MESSAGE
    );
} else {
    JOptionPane.showMessageDialog(
        LoginPanel.this,
        "Login successful!",
        "Success",
        JOptionPane.INFORMATION_MESSAGE
    );
    
    // Store the logged-in user's username in WebManager
        webManager.setCurrentUser(username);
        
    webManager.showPanel("ExploreCars"); // Navigate to Explore Cars Panel

    }

    });

        add(bottomPanel, BorderLayout.CENTER);
    }

    // Static method to register a user
    public static void registerUser(String username) {
        registeredUsers.add(username);
    }
}