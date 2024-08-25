package application;

public class room{
	
	    private int noofguests;
	    private int floornumber;
	    private String roomtype;
	    private int roomnumber;
	    private String hostel_name;
	    private String customer_id;
	  

	    public room(int noofguests,int floornumber, String roomtype, int roomnumber, String hn,String C) {
	        this.noofguests=noofguests;
	        this.floornumber = floornumber;
	        this.roomtype = roomtype;
	        this.roomnumber = roomnumber;
	        this.hostel_name=hn;
	        this.customer_id=C;
	        }
	    

	    public int get_noofguests() {
	        return noofguests;
	    }

	    public void set_noofguests(int noofguests) {
	        this.noofguests = noofguests;
	    }

	    public int get_floornumber() {
	        return floornumber;
	    }

	    public void set_floornumber(int n) {
	        this.floornumber = n;
	    }

	    public String get_roomtype() {
	        return roomtype;
	    }

	    public void set_roomtype(String rt) {
	        this.roomtype = rt;
	    }
	    
	    public String get_customerid() {
	        return customer_id;
	    }

	    public void set_customerid(String rt) {
	        this.customer_id = rt;
	    }

	    public int get_roomnumber() {
	        return roomnumber;
	    }

	    public void set_roomnumber(int p) {
	        this.roomnumber = p;
	    }

	    public void set_hostelname(String hostel_name) {
	        this.hostel_name = hostel_name;
	    }
	    public String get_hostelname() {
	        return hostel_name;
	    }
	    
};
