package Databases;

public class office {
    private String region;
    private String city;
    private String bldgid;

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getBldgID() {
        return bldgid;
    }
    
    public void setRegion(String newRegion) {
    	region = newRegion;
    }

    public void setCity(String newCity) {
        city = newCity;
    }

    public void setBldgID(String newBldgID) {
        bldgid = newBldgID;
    }
}