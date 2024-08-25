package authentication;

public class Manager extends user {
    private int managerSpecificField;

    public Manager() {
     
    }

    public Manager(String name, String cnic, String phoneNumber, String username, String password, String type, int managerSpecificField) {
        super(name, cnic, phoneNumber, username, password, type);
        this.managerSpecificField = managerSpecificField;
    }

    public int getManagerSpecificField() {
        return managerSpecificField;
    }

    public void setManagerSpecificField(int managerSpecificField) {
        this.managerSpecificField = managerSpecificField;
    }

    @Override
    public void displayUserInfo() {
        super.displayUserInfo(); // Call the base class method
        System.out.println("Manager Specific Field: " + managerSpecificField);
    }
}
