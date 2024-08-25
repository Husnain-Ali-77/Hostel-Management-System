package finance;

import java.util.Date;

public class CreditCardPayment extends Payment {
private String cardNumber;
private String cardHolderName;
public CreditCardPayment(int paymentId, double amount, Date paymentDate, String cardNumber, String cardHolderName) {
   super(paymentId, amount, paymentDate);
   this.cardNumber = cardNumber;
   this.setCardHolderName(cardHolderName);
}

@Override
public void processPayment() {
   System.out.println("Processing credit card payment of $" + getAmount() + " using card ending with " + cardNumber.substring(cardNumber.length() - 4));
}

public String getCardHolderName() {
	return cardHolderName;
}

public void setCardHolderName(String cardHolderName) {
	this.cardHolderName = cardHolderName;
}
}
