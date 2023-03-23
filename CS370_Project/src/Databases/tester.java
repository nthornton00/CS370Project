package Databases;

class tester {
	private String testerName;
    private String testerProject;
    private String peripheral;

    public String getTesterName() {
        return testerName;
    }

    public String getTesterProject() {
        return testerProject;
    }

    public String getPeripheral() {
        return peripheral;
    }
    
    public void setTesterName(String newTesterName) {
        testerName = newTesterName;
    }

    public void setTesterProject(String newTesterProject) {
        testerProject = newTesterProject;
    }

    public void setPeripheral(String newPeripheral) {
        peripheral = newPeripheral;
    }
}
