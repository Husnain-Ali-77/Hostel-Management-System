package finance;

import java.util.Date;

//Base Payment class
public class Payment {
 private int paymentId;
 private double amount;
 private Date paymentDate;

 // Constructors, getters, and setters
 public Payment(int paymentId, double amount, Date paymentDate) {
     this.paymentId = paymentId;
     this.amount = amount;
     this.paymentDate = paymentDate;
 }

 public int getPaymentId() {
     return paymentId;
 }

 public void setPaymentId(int paymentId) {
     this.paymentId = paymentId;
 }

 public double getAmount() {
     return amount;
 }

 public void setAmount(double amount) {
     this.amount = amount;
 }

 public Date getPaymentDate() {
     return paymentDate;
 }

 public void setPaymentDate(Date paymentDate) {
     this.paymentDate = paymentDate;
 }

 // Common method for all payment types
 public void processPayment() {
     System.out.println("Processing payment of $" + amount);
 }
}


