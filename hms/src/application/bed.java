package application;

public class bed {
	private int bed_num;
	private String isavailable;
	private int room_number;
	private String hostel_name;
	
	public bed(int bed_num,String isav,int rn,String hn) {
        this.bed_num=bed_num;
        this.isavailable =isav;
        this.room_number=rn;
        this.hostel_name=hn;
    }
	
	 public int get_bednum() {
	        return this.bed_num ;
	    }

	    public void set_bed_num(int bn) {
	        this.bed_num= bn;
	    }
	    
	    public String get_isavailable() {
	        return this.isavailable;
	    }

	    public void set_isavailable(String ia) {
	        this.isavailable = ia;
	    }
	    
	    public int get_roomnumber() {
	        return room_number;
	    }

	    public void set_roomnumber(int roomNumber) {
	        this.room_number = roomNumber;
	    }
	    public String get_hostelname() {
	        return hostel_name;
	    }
	    public void set_hostelname(String hostelName) {
	        this.hostel_name = hostelName;
	    }

}
