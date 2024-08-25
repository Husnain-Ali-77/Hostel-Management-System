package authentication;

public class Customerr extends user
{

    private int customerSpecificField;


    public Customerr() {
      
    }

    public Customerr(String name, String cnic, String phoneNumber, String username, String password, String type, int customerSpecificField) {
        super(name, cnic, phoneNumber, username, password, type);
        this.customerSpecificField = customerSpecificField;
    }

    public int getCustomerSpecificField() {
        return customerSpecificField;
    }

    public void setCustomerSpecificField(int customerSpecificField) {
        this.customerSpecificField = customerSpecificField;
    }

    @Override
    public void displayUserInfo() {
        super.displayUserInfo(); 
        System.out.println("Customer Specific Field: " + customerSpecificField);
    }
}
