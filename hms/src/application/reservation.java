package application;

import java.util.Date;

public class reservation {
    private Date date;
    private String status;
    
    public reservation(Date date, String status) {
        this.date = date;
        this.status = status;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
