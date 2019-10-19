import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class FileInfo
{

    public static boolean supportedFile(String fileName)
    {

        String[] supportedExtensions = {"mp3", "mp4", "flv", "mpeg"};

        for(int i=0; i<supportedExtensions.length; i++)
        {
            String extension = fileName.substring(fileName.length()-supportedExtensions[i].length());
            if(extension.equals(supportedExtensions[i]))
                return true;
        }

        return false;
    }

    //returns a string containing useful information about the file to be displayed directly to the user
    public static String fileInfoString(File file)
    {
        BasicFileAttributes fileAttributes = null;
        try {
            fileAttributes = Files.readAttributes(file.toPath(),BasicFileAttributes.class);
        }
        catch(IOException e)
        {
            return "";
        }

        String infoString = "Name: " + (String)file.getName() +
                "\nPath: " +  getPath(file) +
                "\nSize: " + getFileSizeMB(file) +
                "\nCreated: " + getCreationDate(fileAttributes).toString() +
                "\nLast Modified: " + getLastModifiedDate(file).toString() +
                "\nFile Type: " + getFileExtension(file) +
                "\nReadOnly: " + (file.canWrite()?"No":"Yes");

        return infoString;
    }


    public static Date getCreationDate(BasicFileAttributes fileAttributes)
    {
        return new Date(fileAttributes.creationTime().toMillis());
    }


    public static String getFileExtension(File file)
    {
        String fileName = file.getName();

        int dotIndex = fileName.lastIndexOf('.');

        if(dotIndex==-1)
            return "No Extension";

        return fileName.substring(dotIndex+1).toUpperCase();
    }

    public static String getFileSizeMB(File file)
    {
        Double std = (double) file.length() / (1024*1024); //File size in MB
        String fileSize = String.format("%.2f", std);

        return fileSize + " MB";
    }

    public static Date getLastModifiedDate(File file)
    {
        return new Date(file.lastModified());
    }

    public static String getPath(File file)
    {
        String path = file.getAbsolutePath();
        path = path.replace("\\", "/");

        return path;
    }

}
