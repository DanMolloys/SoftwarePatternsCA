import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class EmployeeFileManager {
    private RandomFile application;
    private File file;

    public EmployeeFileManager(File file) {
        this.file = file;
        this.application = new RandomFile();
    }

    public Employee getFirstRecord() {
        application.openReadFile(file.getAbsolutePath());
        long currentByteStart = application.getFirst();
        Employee employee = application.readRecords(currentByteStart);
        application.closeReadFile();
        return employee;
    }

    public Employee getNextRecord(long currentByteStart) {
        application.openReadFile(file.getAbsolutePath());
        currentByteStart = application.getNext(currentByteStart);
        Employee employee = application.readRecords(currentByteStart);
        application.closeReadFile();
        return employee;
    }

    public Employee getPreviousRecord(long currentByteStart) {
        application.openReadFile(file.getAbsolutePath());
        currentByteStart = application.getPrevious(currentByteStart);
        Employee employee = application.readRecords(currentByteStart);
        application.closeReadFile();
        return employee;
    }

    public Employee getLastRecord() {
        application.openReadFile(file.getAbsolutePath());
        long currentByteStart = application.getLast();
        Employee employee = application.readRecords(currentByteStart);
        application.closeReadFile();
        return employee;
    }

    public void addRecord(Employee newEmployee, String filePath) {
        application.openWriteFile(filePath);
        application.addRecords(newEmployee);
        application.closeWriteFile();
    }

    public void deleteRecord(long currentByteStart) {
        application.openWriteFile(file.getAbsolutePath());
        application.deleteRecords(currentByteStart);
        application.closeWriteFile();
    }

    public void saveChanges(Employee employee, long currentByteStart) {
        application.openWriteFile(file.getAbsolutePath());
        application.changeRecords(employee, currentByteStart);
        application.closeWriteFile();
    }

    public void openFile(String filePath) {
        application.openReadFile(filePath);
        application.closeReadFile();
    }

    public void saveFile(String newFilePath) {
        File newFile = new File(newFilePath);
        try {
            Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(String fileName) {
        File newFile = new File(fileName);
        try {
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    public long getFileLength() {
        return file.length();
    }

    public boolean isSomeoneToDisplay() {
        application.openReadFile(file.getAbsolutePath());
        boolean isSomeoneToDisplay = application.isSomeoneToDisplay();
        application.closeReadFile();
        return isSomeoneToDisplay;
    }

    public boolean isPpsExist(String pps, long currentByte) {
        application.openReadFile(file.getAbsolutePath());
        boolean ppsExist = application.isPpsExist(pps, currentByte);
        application.closeReadFile();
        return ppsExist;
    }

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

    public Employee searchEmployeeBySurname(String surname) {
        application.openReadFile(file.getAbsolutePath());
        long currentByteStart = application.getFirst();
        Employee employee = application.readRecords(currentByteStart);
        while (employee != null) {
            if (employee.getSurname().trim().equalsIgnoreCase(surname)) {
                return employee;
            }
            currentByteStart = application.getNext(currentByteStart);
            employee = application.readRecords(currentByteStart);
        }
        application.closeReadFile();
        return null;
    }

    public boolean fileExists() {

        return false;
    }
}