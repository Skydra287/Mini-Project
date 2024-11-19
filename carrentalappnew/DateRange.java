/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrentalappnew;
import java.util.Date;

/**
 *
 * @author ASUS TUF
 */
class DateRange {
    private Date startDate;
    private Date endDate;

    public DateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean contains(Date date) {
    return !date.before(startDate) && !date.after(endDate);
}

// Add a method to check overlap between two ranges
public boolean overlaps(DateRange other) {
    return (startDate.before(other.endDate) || startDate.equals(other.endDate)) &&
           (endDate.after(other.startDate) || endDate.equals(other.startDate));
}


    @Override
    public String toString() {
        return "DateRange{" +
               "startDate=" + startDate +
               ", endDate=" + endDate +
               '}';
    }
}


