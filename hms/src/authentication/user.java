package authentication;

public class user {
    private String name;
    private String cnic;
    private String phoneNumber;
    private String username;
    private String password;
    private String type;

    
    public user() {
     
    }

    public user(String name, String cnic, String phoneNumber, String username, String password, String type) {
        this.name = name;
        this.cnic = cnic;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void displayUserInfo() {
        System.out.println("Name: " + name);
        System.out.println("CNIC: " + cnic);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Type: " + type);
    }
}