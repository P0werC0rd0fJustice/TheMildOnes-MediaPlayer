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
}
