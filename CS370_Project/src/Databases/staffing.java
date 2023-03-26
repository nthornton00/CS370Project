package Databases;

class staffing {
	private String employeeName;
    private int badgeID;
    private String role;
    
    public int getBadgeID() {
        return badgeID;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }

    public String getRole() {
        return role;
    }
    
    public void setBadgeID(int newBadgeID) {
        badgeID = newBadgeID;
    }
    
    public void setEmployeeName(String newEmployeeName) {
        employeeName = newEmployeeName;
    }

    public void setRole(String newRole) {
        role = newRole;
    }
}
