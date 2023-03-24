package Databases;

public class devices {
    private String deviceLot;
    private String deviceMfrLot;
    private int devicesQty;
    private String devicesProject;
    private boolean isCheckedOut;

    public String getDeviceLot() {
        return deviceLot;
    }

    public String getDeviceMfrLot() {
        return deviceMfrLot;
    }

    public int getDevicesQty() {
        return devicesQty;
    }

    public String getDevicesProject() {
        return devicesProject;
    }
    
    public boolean getIsCheckedOut() {
    	return isCheckedOut;
    }
    
    public void setDeviceLot(String newDeviceLot) {
        deviceLot = newDeviceLot;
    }

    public void setDeviceMfrLot(String newDeviceMfrLot) {
        deviceMfrLot = newDeviceMfrLot;
    }

    public void setDevicesQty(int newDevicesQty) {
        devicesQty = newDevicesQty;
    }

    public void setDevicesProject(String newDevicesProject) {
        devicesProject = newDevicesProject;
    }
    
    public void setIsCheckedOut(boolean checkedOutStatus) {
    	isCheckedOut = checkedOutStatus;
    }
}