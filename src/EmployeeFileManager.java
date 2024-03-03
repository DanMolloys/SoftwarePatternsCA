import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class EmployeeFileManager {

    //unsure of what value to assign,
    public static final long SIZE = 101;
    private RandomFile application = new RandomFile();
    private File file;

    // Constructor
    public EmployeeFileManager(File file) {
        this.file = file;
    }

    // Methods
    public Employee readRecords(long currentByteStart) {
        return application.readRecords(currentByteStart);
    }

    public void writeRecords(Employee employee, long currentByteStart) {
        application.openWriteFile(this.file.getAbsolutePath());
        application.changeRecords(employee, currentByteStart);
        application.closeWriteFile();
    }

    public void deleteRecords(long currentByteStart) {
        application.deleteRecords(currentByteStart);
    }

    // create a new file
    public void createFile(String fileName) {
        application.createFile(fileName);
        this.file = new File(fileName);
    }

    // open an existing file
    public void openFile(String fileName) {
        this.file = new File(fileName);
        application.openReadFile(file.getAbsolutePath());
        application.closeReadFile();
    }

    // save the file
    public void saveFile() {
        File newFile = new File(String.valueOf(file));
        try {
            Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            this.file = newFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // delete the file
    // was using this in the exit app method but the orignal is simplier
    public void deleteFile() {
        file.delete();
    }

    // generate a random file name
    public String generateFileName() {
        String fileNameChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-";
        StringBuilder fileName = new StringBuilder();
        Random rnd = new Random();
        // loop until 20 character long file name is generated
        while (fileName.length() < 20) {
            int index = (int) (rnd.nextFloat() * fileNameChars.length());
            fileName.append(fileNameChars.charAt(index));
        }
        String generatedfileName = fileName.toString();
        return generatedfileName;
    }

    // check if the file exists
    public boolean fileExists() {
        return file.exists();
    }
}
