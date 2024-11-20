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
public class PaymentMethodPanel extends JPanel {
    private WebManager webManager;
    
// Validate PayPal email
private boolean isValidPaypalEmail(String email) {
    return email.contains("@") && (email.endsWith(".com") || email.endsWith(".my"));
}

// Validate credit/debit card number
private boolean isValidCardNumber(String cardNumber) {
    return cardNumber.matches("\\d{16}"); // Exactly 16 digits
}

// Validate bank account
private boolean isValidBankAccount(String accountNumber) {
    return accountNumber.matches("\\d{8,12}"); // 8 to 12 digits
}

    public PaymentMethodPanel(WebManager webManager) {
        this.webManager = webManager;

        setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        setBackground(new Color(184, 75, 75)); 

        // Constraints for layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Align components to the center

        // Title
        JLabel titleLabel = new JLabel("PAYMENT METHOD");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        add(titleLabel, gbc);

        // Payment Method Options
        ButtonGroup paymentGroup = new ButtonGroup();
        JRadioButton creditDebitButton = new JRadioButton("Credit/Debit Card");
        creditDebitButton.setBackground(new Color(229,184,11));
        JRadioButton cashButton = new JRadioButton("Cash");
        cashButton.setBackground(new Color(229,184,11));
        JRadioButton onlineBankingButton = new JRadioButton("Online Banking");
        onlineBankingButton.setBackground(new Color(229,184,11));
        JRadioButton paypalButton = new JRadioButton("PayPal");
        paypalButton.setBackground(new Color(229,184,11));

        paymentGroup.add(creditDebitButton);
        paymentGroup.add(cashButton);
        paymentGroup.add(onlineBankingButton);
        paymentGroup.add(paypalButton);

        // Add Radio Buttons
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(creditDebitButton, gbc);
        gbc.gridy = 2;
        add(cashButton, gbc);
        gbc.gridy = 3;
        add(onlineBankingButton, gbc);
        gbc.gridy = 4;
        add(paypalButton, gbc);

        // Dynamic Input Section
        JLabel dynamicLabel = new JLabel();
        dynamicLabel.setVisible(false);
        gbc.gridy = 1;
        gbc.gridx = 1;
        add(dynamicLabel, gbc);

        JTextField dynamicField = new JTextField(15);
        dynamicField.setVisible(false);
        gbc.gridy = 2;
        add(dynamicField, gbc);

        // Online Banking Section
        JLabel bankLabel = new JLabel("Select Bank:");
        bankLabel.setVisible(false);
        gbc.gridy = 1;
        add(bankLabel, gbc);

        JComboBox<String> bankComboBox = new JComboBox<>(new String[]{"CIMB", "Maybank", "ASB"});
        bankComboBox.setVisible(false);
        gbc.gridy = 2;
        add(bankComboBox, gbc);

        // Bank Account and Username
        JLabel accountLabel = new JLabel("Bank Account:");
        accountLabel.setVisible(false);
        gbc.gridy = 3;
        add(accountLabel, gbc);

        JTextField accountField = new JTextField(15);
        accountField.setVisible(false);
        gbc.gridy = 4;
        add(accountField, gbc);

        JLabel usernameLabel = new JLabel("Bank Username:");
        usernameLabel.setVisible(false);
        gbc.gridy = 5;
        add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(15);
        usernameField.setVisible(false);
        gbc.gridy = 6;
        add(usernameField, gbc);

        // Summary Label
        JLabel summaryLabel = new JLabel("Selected Payment: None");
        summaryLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(summaryLabel, gbc);

        // Action Listeners for Payment Methods
        creditDebitButton.addActionListener(e -> {
            dynamicLabel.setText("Card Number:");
            dynamicLabel.setVisible(true);
            dynamicField.setVisible(true);
            bankLabel.setVisible(false);
            bankComboBox.setVisible(false);
            accountLabel.setVisible(false);
            accountField.setVisible(false);
            usernameLabel.setVisible(false);
            usernameField.setVisible(false);
            summaryLabel.setText("Selected Payment: Credit/Debit Card");
        });

        paypalButton.addActionListener(e -> {
            dynamicLabel.setText("PayPal Email:");
            dynamicLabel.setVisible(true);
            dynamicField.setVisible(true);
            bankLabel.setVisible(false);
            bankComboBox.setVisible(false);
            accountLabel.setVisible(false);
            accountField.setVisible(false);
            usernameLabel.setVisible(false);
            usernameField.setVisible(false);
            summaryLabel.setText("Selected Payment: PayPal");
        });

        cashButton.addActionListener(e -> {
            dynamicLabel.setVisible(false);
            dynamicField.setVisible(false);
            bankLabel.setVisible(false);
            bankComboBox.setVisible(false);
            accountLabel.setVisible(false);
            accountField.setVisible(false);
            usernameLabel.setVisible(false);
            usernameField.setVisible(false);
            summaryLabel.setText("Selected Payment: Cash - Pay on pickup.");
        });

        onlineBankingButton.addActionListener(e -> {
            dynamicLabel.setVisible(false);
            dynamicField.setVisible(false);
            bankLabel.setVisible(true);
            bankComboBox.setVisible(true);
            accountLabel.setVisible(true);
            accountField.setVisible(true);
            usernameLabel.setVisible(true);
            usernameField.setVisible(true);
            summaryLabel.setText("Selected Payment: Online Banking");
        });

        // Buttons
        JButton proceedButton = new JButton("Proceed");
        proceedButton.setForeground(Color.WHITE);
        proceedButton.setBackground(new Color (234,210,168));
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(proceedButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color (234,210,168));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(backButton, gbc);

        // Back Button Action
        backButton.addActionListener(e -> webManager.showPanel("RentalSummary"));

        // Proceed Button Action
        proceedButton.addActionListener(e -> {
    if (creditDebitButton.isSelected()) {
        String cardNumber = dynamicField.getText().trim();
        if (!isValidCardNumber(cardNumber)) {
            JOptionPane.showMessageDialog(
                this, 
                "Invalid Card Number. It must be exactly 16 digits and contain only numbers.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            showReceipt("Credit/Debit Card", cardNumber, null, null);
        }
    } else if (paypalButton.isSelected()) {
        String paypalEmail = dynamicField.getText().trim();
        if (!isValidPaypalEmail(paypalEmail)) {
            JOptionPane.showMessageDialog(
                this, 
                "Invalid PayPal Email. It must contain '@' and end with '.com' or '.my'.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            showReceipt("PayPal", paypalEmail, null, null);
        }
    } else if (cashButton.isSelected()) {
        showReceipt("Cash", "Pay on pickup", null, null);
    } else if (onlineBankingButton.isSelected()) {
        String bankAccount = accountField.getText().trim();
        String bankUsername = usernameField.getText().trim();

        if (bankAccount.isEmpty() || bankUsername.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, 
                "Please fill out both Bank Account and Username.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        } else if (!isValidBankAccount(bankAccount)) {
            JOptionPane.showMessageDialog(
                this, 
                "Invalid Bank Account. It must be 8 to 12 digits long and contain only numbers.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE
            );
        } else {
            String selectedBank = (String) bankComboBox.getSelectedItem();
            showReceipt("Online Banking (" + selectedBank + ")", null, bankAccount, bankUsername);
        }
    } else {
        JOptionPane.showMessageDialog(
            this, 
            "Please select a payment method.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }
});
    }

     // Method to show receipt using JDialog
    private void showReceipt(String paymentMethod, String paymentInfo, String bankAccount, String bankUsername) {
        StringBuilder receiptText = new StringBuilder();

        receiptText.append("Payment Successful!\n\n");
        receiptText.append("Car Rental Receipt\n");
        receiptText.append("======================\n");
        receiptText.append("Name: ").append(webManager.getCurrentUser()).append("\n");

        for (BookingDetails booking : webManager.getBookings()) {
            int totalPrice = booking.getBasePrice() + booking.getAddOnPrice();
            receiptText.append("Car: ").append(booking.getCarName()).append("\n");
            receiptText.append("Rental Dates: ").append(booking.getRentalDates()).append("\n");
            receiptText.append("Total Days: ").append(booking.getRentalDays()).append("\n");
            receiptText.append("Base Price: RM ").append(booking.getBasePrice()).append("\n");
            receiptText.append("Add-Ons: RM ").append(booking.getAddOnPrice()).append("\n");
            receiptText.append("Total Price: RM ").append(totalPrice).append("\n");
            receiptText.append("----------------------\n");
        }

        // Add grand total and payment method
        int grandTotal = webManager.getBookings().stream().mapToInt(b -> b.getBasePrice() + b.getAddOnPrice()).sum();
        receiptText.append("Grand Total: RM ").append(grandTotal).append("\n");
        receiptText.append("Payment Method: ").append(paymentMethod).append("\n");

        // Add banking details if applicable
        if (paymentMethod.contains("Online Banking")) {
            receiptText.append("Bank Account: ").append(bankAccount != null ? bankAccount : "N/A").append("\n");
            receiptText.append("Bank Username: ").append(bankUsername != null ? bankUsername : "N/A").append("\n");
        } else if (paymentMethod.equals("Credit/Debit Card") || paymentMethod.equals("PayPal")) {
            receiptText.append("Payment Info: ").append(paymentInfo).append("\n");
        }

        // Add "Car is ready to pick up" message
       receiptText.append("Your car is ready to pick up at 6 AM on the rental date.\n");

        // Save receipt for the user
        webManager.saveReceipt(webManager.getCurrentUser(), receiptText.toString());


        // Create custom dialog for receipt
        JDialog dialog = new JDialog((Frame) null, "Receipt", true);
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea(receiptText.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        dialog.add(new JScrollPane(textArea), BorderLayout.CENTER);

        
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back to Menu");
        JButton exitButton = new JButton("Exit");
        JButton logoutButton = new JButton("Log Out");
        buttonPanel.add(backButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(exitButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Back Button Action
        backButton.addActionListener(e -> {
            webManager.getBookings().clear();
            webManager.showPanel("ExploreCars");
            dialog.dispose();
        });

        // Logout Button Action
        logoutButton.addActionListener(e -> {
            webManager.getBookings().clear();
            webManager.showPanel("Login");
            dialog.dispose();
        });

        // Exit Button Action
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(dialog, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        dialog.setVisible(true);
    }
}





