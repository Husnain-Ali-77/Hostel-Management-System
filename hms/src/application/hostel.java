package application;

import java.sql.Date;

public class hostel {
    private int hostelId;
    private String hostelName;
    private String address;
    private String managerName;
    private String managerUsername;
    private String managerContact;
    private int totalRooms;
    private int vacantRooms;
    private String hostelType;
    private Date establishedDate;
    private String description;
    
    public hostel() {
    }
    public hostel(int hostelId, String hostelName, String address, String managerName, String managerUsername,
                  String managerContact, int totalRooms, int vacantRooms, String hostelType, Date establishedDate,
                  String description) {
        this.hostelId = hostelId;
        this.hostelName = hostelName;
        this.address = address;
        this.managerName = managerName;
        this.managerUsername = managerUsername;
        this.managerContact = managerContact;
        this.totalRooms = totalRooms;
        this.vacantRooms = vacantRooms;
        this.hostelType = hostelType;
        this.establishedDate = establishedDate;
        this.description = description;
    }
    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public String getManagerContact() {
        return managerContact;
    }

    public void setManagerContact(String managerContact) {
        this.managerContact = managerContact;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }

    public int getVacantRooms() {
        return vacantRooms;
    }

    public void setVacantRooms(int vacantRooms) {
        this.vacantRooms = vacantRooms;
    }

    public String getHostelType() {
        return hostelType;
    }

    public void setHostelType(String hostelType) {
        this.hostelType = hostelType;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
