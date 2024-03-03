public class EmployeeSearchManager {
    private EmployeeFileManager employeeFileManager;

    public EmployeeSearchManager(EmployeeFileManager employeeFileManager) {
        this.employeeFileManager = employeeFileManager;
    }

    public Employee getFirstRecord() {
        return employeeFileManager.getFirstRecord();
    }

    public Employee getNextRecord(long currentByteStart) {
        return employeeFileManager.getNextRecord(currentByteStart);
    }

    public Employee getPreviousRecord(long currentByteStart) {
        return employeeFileManager.getPreviousRecord(currentByteStart);
    }

    public Employee getLastRecord() {
        return employeeFileManager.getLastRecord();
    }

    public Employee searchEmployeeById(int id) {
        return employeeFileManager.searchEmployeeById(id);
    }

    public Employee searchEmployeeBySurname(String surname) {
        return employeeFileManager.searchEmployeeBySurname(surname);
    }

    public boolean isSomeoneToDisplay() {
        return employeeFileManager.isSomeoneToDisplay();
    }

    public boolean isPpsExist(String pps, long currentByte) {
        return employeeFileManager.isPpsExist(pps, currentByte);
    }
}