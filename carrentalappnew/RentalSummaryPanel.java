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
public class RentalSummaryPanel extends JPanel {
    private WebManager webManager;

    public RentalSummaryPanel(WebManager webManager) {
        this.webManager = webManager;
        setLayout(new BorderLayout());
        updateSummary(); // Populate the summary initially
    }

    // Method to refresh the summary panel
    public void updateSummary() {
        removeAll(); // Clear the existing components

        // Title
        JLabel titleLabel = new JLabel("Rental Summary", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Bookings List
        JPanel bookingsPanel = new JPanel(new GridLayout(0, 1, 10, 10)); // Dynamically grows
        int totalCarPrice = 0;  // Initialize total car price
        int totalAddOnPrice = 0; // Initialize total add-on price
        int totalSum = 0;       // Initialize grand total


        for (BookingDetails booking : webManager.getBookings()) {
    JPanel bookingPanel = new JPanel(new BorderLayout()); // Panel for each booking
    JLabel bookingLabel = new JLabel(
        "<html>Car: " + booking.carName + "<br>" +
        "Rental Dates: " + booking.rentalDates + "<br>" +
        "Rental Days: " + booking.rentalDays + "<br>" +
        "Car Price: RM " + booking.getBasePrice() + "<br>" + // Base car price
        "Add-On Price: RM " + booking.getAddOnPrice() + "<br>" + // Add-on price
        "Total Price: RM " + (booking.getBasePrice() + booking.getAddOnPrice()) + "</html>" // Total for this booking
    );
    bookingLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    bookingPanel.add(bookingLabel, BorderLayout.CENTER);

    // Add delete button for each booking
    JButton deleteButton = new JButton("Delete");
    deleteButton.addActionListener(e -> {
    webManager.cancelBooking(booking);
    webManager.getBookings().remove(booking);
    updateSummary(); // Refresh the summary after deletion
});

    bookingPanel.add(deleteButton, BorderLayout.EAST);
    bookingsPanel.add(bookingPanel);

    // Update totals
    totalCarPrice += booking.getBasePrice();
    totalAddOnPrice += booking.getAddOnPrice();
}

        JScrollPane scrollPane = new JScrollPane(bookingsPanel); // Add scrolling if needed
        add(scrollPane, BorderLayout.CENTER);
        
        totalSum = totalCarPrice + totalAddOnPrice;

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new BorderLayout());

        // Total Sum Display
       JLabel totalLabel = new JLabel(
    "Total Car Price: RM " + totalCarPrice + 
    " | Total Add-On Price: RM " + totalAddOnPrice + 
    " | Grand Total: RM " + totalSum,
    SwingConstants.CENTER
);
totalLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
buttonsPanel.add(totalLabel, BorderLayout.NORTH);


        // Navigation Buttons
        JPanel actionButtons = new JPanel();
        JButton backButton = new JButton("Add Car");
        backButton.addActionListener(e -> webManager.showPanel("ExploreCars"));
        actionButtons.add(backButton);

        JButton payButton = new JButton("Pay");
       payButton.addActionListener(e -> {
    if (webManager.getBookings().isEmpty()) {
        JOptionPane.showMessageDialog(
            this,
            "No bookings to pay for!",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    } else {
        // Show confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to proceed to payment?",
            "Confirm Payment",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // User confirmed, proceed to payment method
            JOptionPane.showMessageDialog(
                this,
                "Redirecting to Payment Method...",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
            webManager.showPanel("PaymentMethod");
        }
    }
});

        actionButtons.add(payButton);

        buttonsPanel.add(actionButtons, BorderLayout.SOUTH);

        add(buttonsPanel, BorderLayout.SOUTH);

        revalidate(); // Refresh the UI
        repaint();
    }
}



