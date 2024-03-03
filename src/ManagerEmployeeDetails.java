import java.io.File;


//if a method is unused it was causing problems, probably due to initlization errors or felt it wasn't helping
public class ManagerEmployeeDetails {
    private RandomFile application = new RandomFile();
    private File file;

    public ManagerEmployeeDetails(File file) {
        this.file = file;
    }

    //reads the file, gets the first employee record, closes the file and then returns the employee
    public Employee getFirst() {
        application.openReadFile(file.getAbsolutePath());
        long currentByteStart = application.getFirst();
        Employee employee = application.readRecords(currentByteStart);
        application.closeReadFile();
        return employee;
    }

    // reads the file, gets the next employee record, closes the file and then returns the employee
    public Employee getNext(long currentByteStart) {
        application.openReadFile(file.getAbsolutePath());
        currentByteStart = application.getNext(currentByteStart);
        Employee employee = application.readRecords(currentByteStart);
        application.closeReadFile();
        return employee;
    }

    // reads the file, gets the last employee record, closes the file and then returns the employee
    public Employee getLast() {
        application.openReadFile(file.getAbsolutePath());
        long currentByteStart = application.getLast();
        Employee employee = application.readRecords(currentByteStart);
        application.closeReadFile();
        return employee;
    }

    // reads the file, gets the previous employee record, closes the file and then returns the employee
    public Employee getPrevious(long currentByteStart) {
        application.openReadFile(file.getAbsolutePath());
        currentByteStart = application.getPrevious(currentByteStart);
        Employee employee = application.readRecords(currentByteStart);
        application.closeReadFile();
        return employee;
    }

    //search for an Employee by their id number
    public Employee searchEmployeeById(int id) {
        application.openReadFile(file.getAbsolutePath());
        long currentByteStart = application.getFirst();
        Employee employee = application.readRecords(currentByteStart);
        while (employee != null) {
            if (employee.getEmployeeId() == id) {
                break;
            }
            currentByteStart = application.getNext(currentByteStart);
            employee = application.readRecords(currentByteStart);
        }
        application.closeReadFile();
        return employee;
    }

    //search for an Employee by their surname
    public Employee searchEmployeeBySurname(String surname) {
        application.openReadFile(file.getAbsolutePath());
        long currentByteStart = application.getFirst();
        Employee employee = application.readRecords(currentByteStart);
        while (employee != null) {
            // if the current employee's surname matches the given surname, return the employee
            if (employee.getSurname().trim().equalsIgnoreCase(surname)) {
                application.closeReadFile();
                return employee;
            }
            // move to the next record
            currentByteStart = application.getNext(currentByteStart);
            employee = application.readRecords(currentByteStart);
        }
        application.closeReadFile();
        // return null if no matching employee is found
        return null;
    }

    //add an employee to the file
    public void addRecord(Employee newEmployee) {
        application.openWriteFile(file.getAbsolutePath());
        application.addRecords(newEmployee);
        application.closeWriteFile();
    }

    //delete an employee from the file
    public void deleteRecord(long currentByteStart) {
        application.openWriteFile(file.getAbsolutePath());
        application.deleteRecords(currentByteStart);
        application.closeWriteFile();
    }

    // checks if there is any active employee record in the file
    public boolean isSomeoneToDisplay() {
        application.openReadFile(file.getAbsolutePath());
        boolean isSomeoneToDisplay = application.isSomeoneToDisplay();
        application.closeReadFile();
        return isSomeoneToDisplay;
    }

    // changes a specific employee record in the file
    public void changeRecords(Employee employee, long currentByteStart) {
        application.openWriteFile(file.getAbsolutePath());
        application.changeRecords(employee, currentByteStart);
        application.closeWriteFile();
    }

    // checks if a certain PPS exists in the file
    public boolean isPpsExist(String pps, long currentByte) {
        application.openReadFile(file.getAbsolutePath());
        boolean ppsExist = application.isPpsExist(pps, currentByte);
        application.closeReadFile();
        return ppsExist;
    }

}