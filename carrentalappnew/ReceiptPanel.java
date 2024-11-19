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
public class ReceiptPanel extends JPanel {
    private WebManager webManager;

    public ReceiptPanel(WebManager webManager, String paymentMethod, String paymentInfo, String bankAccount, String bankUsername) {
        this.webManager = webManager;

        setLayout(new BorderLayout());

        // Title: Payment Successful
        JLabel paymentSuccessLabel = new JLabel("Payment Successful", SwingConstants.CENTER);
        paymentSuccessLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        paymentSuccessLabel.setForeground(Color.GREEN);
        add(paymentSuccessLabel, BorderLayout.NORTH);

        // Validate if there are bookings
        if (webManager.getBookings().isEmpty()) {
            JTextArea emptyMessage = new JTextArea("No bookings found. Please add a booking first.");
            emptyMessage.setEditable(false);
            emptyMessage.setFont(new Font("Monospaced", Font.PLAIN, 14));
            add(emptyMessage, BorderLayout.CENTER);

            // Back Button
            JButton backButton = new JButton("Back to Main");
            backButton.addActionListener(e -> webManager.showPanel("ExploreCars"));
            add(backButton, BorderLayout.SOUTH);
            return;
        }

        // Receipt Content
        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // User Details
        String currentUser = webManager.getCurrentUser();
        String userPhone = "N/A";
        if (SignupPanel.getUserDatabase().containsKey(currentUser)) {
            userPhone = SignupPanel.getUserDatabase().get(currentUser);
        }

        // Calculate Grand Total
        int grandTotal = 0;
        StringBuilder receiptText = new StringBuilder();

        receiptText.append("Car Rental Application\n");
        receiptText.append("======================\n");
        receiptText.append("Name: ").append(currentUser).append("\n");
        receiptText.append("Phone: ").append(userPhone).append("\n");
        receiptText.append("----------------------\n");

        for (BookingDetails booking : webManager.getBookings()) {
            int totalPrice = booking.getBasePrice() + booking.getAddOnPrice();
            grandTotal += totalPrice;

            receiptText.append("Car: ").append(booking.getCarName()).append("\n");
            receiptText.append("Rental Dates: ").append(booking.getRentalDates()).append("\n");
            receiptText.append("Total Days: ").append(booking.getRentalDays()).append("\n");
            receiptText.append("Base Price: RM ").append(booking.getBasePrice()).append("\n");
            receiptText.append("Add-Ons: RM ").append(booking.getAddOnPrice()).append("\n");
            receiptText.append("Total Price: RM ").append(totalPrice).append("\n");
            receiptText.append("----------------------\n");
        }

        // Add grand total and payment method
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
        receiptText.append("\nYour car is ready to pick up at 6 AM on the rental date.\n");

        receiptArea.setText(receiptText.toString());
        add(new JScrollPane(receiptArea), BorderLayout.CENTER);

        // Back and Exit Buttons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Main Menu");
        backButton.addActionListener(e -> {
        // Clear all bookings after payment
        webManager.getBookings().clear();

        // Redirect to ExploreCars panel
        webManager.showPanel("ExploreCars");
        });


        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
