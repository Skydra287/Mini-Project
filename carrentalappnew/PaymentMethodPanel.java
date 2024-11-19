/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrentalappnew;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ASUS TUF
 */
public class PaymentMethodPanel extends JPanel {
    private WebManager webManager;

    public PaymentMethodPanel(WebManager webManager) {
        this.webManager = webManager;

        setLayout(new GridBagLayout()); // Use GridBagLayout for centering

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
        JRadioButton cashButton = new JRadioButton("Cash");
        JRadioButton onlineBankingButton = new JRadioButton("Online Banking");
        JRadioButton paypalButton = new JRadioButton("PayPal");

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
        gbc.gridy = 8;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(proceedButton, gbc);

        JButton backButton = new JButton("Back");
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(backButton, gbc);

        // Back Button Action
        backButton.addActionListener(e -> webManager.showPanel("RentalSummary"));

        // Proceed Button Action
proceedButton.addActionListener(e -> {
    if (creditDebitButton.isSelected()) {
        if (dynamicField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out the Card Number.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            webManager.showReceiptPanel("Credit/Debit Card", dynamicField.getText(), null, null);
        }
    } else if (paypalButton.isSelected()) {
        if (dynamicField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out the PayPal Email.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            webManager.showReceiptPanel("PayPal", dynamicField.getText(), null, null);
        }
    } else if (cashButton.isSelected()) {
        webManager.showReceiptPanel("Cash", "Pay on pickup", null, null);
    } else if (onlineBankingButton.isSelected()) {
        if (accountField.getText().isEmpty() || usernameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out Bank Account and Username.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String selectedBank = (String) bankComboBox.getSelectedItem();
            String bankAccount = accountField.getText().trim();
            String bankUsername = usernameField.getText().trim();

            webManager.showReceiptPanel(
                "Online Banking (" + selectedBank + ")", // Payment method
                null,                                   // No payment info for online banking
                bankAccount,                            // Pass the bank account
                bankUsername                            // Pass the bank username
            );
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a payment method.", "Error", JOptionPane.ERROR_MESSAGE);
    }
});

    }
}
