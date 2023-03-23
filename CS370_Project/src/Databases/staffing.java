package Databases;

class staffing {
	private String employeeName;
    private int badgeID;
    private String role;
    
    public String getEmployeeName() {
        return employeeName;
    }

    public int getBadgeID() {
        return badgeID;
    }

    public String getRole() {
        return role;
    }
    
    public void setEmployeeName(String newEmployeeName) {
        employeeName = newEmployeeName;
    }

    public void setBadgeID(int newBadgeID) {
        badgeID = newBadgeID;
    }

    public void setRole(String newRole) {
        role = newRole;
    }
}
