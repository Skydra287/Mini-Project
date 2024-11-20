/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrentalappnew;
import javax.swing.*;
import java.awt.*;
import carrentalappnew.SignupPanel;




/**
 *
 * @author ASUS TUF
 */
public class LoginPanel extends JPanel {
    

    public LoginPanel(WebManager webManager) {
        setLayout(new BorderLayout());

        // Top Panel with Logo and Title
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(184,75,75));
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
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // Bottom Panel with Login Fields and Buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(new Color(184,75,75));
        
        JLabel syaratLogin = new JLabel("please sign up if you dont have an account");
        syaratLogin.setBounds(280, 150, 300, 30);
        syaratLogin.setForeground(Color.WHITE);
        bottomPanel.add(syaratLogin);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(250, 20, 100, 30);
        usernameLabel.setForeground(Color.WHITE);
        bottomPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(320, 20, 200, 30);
        bottomPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(250, 70, 100, 30);
        passwordLabel.setForeground(Color.WHITE);
        bottomPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(320, 70, 200, 30);
        bottomPanel.add(passwordField);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(300, 120, 90, 30);
        signupButton.setForeground(Color.WHITE);
        signupButton.setBackground(new Color (234,210,168));
        bottomPanel.add(signupButton);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(410, 120, 90, 30);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color (234,210,168));
        bottomPanel.add(loginButton);

        // Action Listener for Sign Up Button
        signupButton.addActionListener(e -> webManager.showPanel("Signup"));

        // Action Listener for Login Button
        loginButton.addActionListener(e -> {
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

    
}