import java.io.File;
import java.util.ArrayList;

public class FileList
{
    private File directory;
    private File curFile;
    private int curFileIndex;
    private ArrayList<File> supportedFiles;

    public FileList(File curFile)
    {
        this.curFile = curFile;
        directory = curFile.getParentFile();
        supportedFiles = new ArrayList<>();

        populateSupportedFilesList();

        curFileIndex = supportedFiles.indexOf(curFile);
    }


    private void populateSupportedFilesList()
    {
        for(final File fileEntry : directory.listFiles())
        {
            //only add to the file list if it's not a directory and has a supported file type
            if(!fileEntry.isDirectory() && FileInfo.supportedFile(fileEntry.getName()))
            {
                supportedFiles.add(fileEntry);
            }
        }

    }

    public ArrayList<File> getSupportedFiles()
    {
        return supportedFiles;
    }


    public File getNextSupportedFile()
    {
        curFileIndex++;
        if(curFileIndex >= supportedFiles.size())
            curFileIndex = 0;

        return supportedFiles.get(curFileIndex);
    }


}
