package finance;

import java.util.Date;

public class CashPayment extends Payment {
	 private String currency;

	 // Constructors, getters, and setters specific to CashPayment
	 public CashPayment(int paymentId, double amount, Date paymentDate, String currency) {
	     super(paymentId, amount, paymentDate);
	     this.currency = currency;
	 }

	 // Override the processPayment method for CashPayment
	 @Override
	 public void processPayment() {
	     System.out.println("Received cash payment of " + currency + " " + getAmount());
	 }
	}
