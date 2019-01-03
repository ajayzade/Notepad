package aj.note.lenovo.notes;

public class NotesDataProvider {
    String fileName;
    String fileContent;
    String fileDate;
    String fileTime;

    public NotesDataProvider(){

    }

    public NotesDataProvider(String fileName, String fileContent, String fileDate, String fileTime) {
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.fileDate = fileDate;
        this.fileTime = fileTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }

    public String getFileTime() {
        return fileTime;
    }

    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }
}
