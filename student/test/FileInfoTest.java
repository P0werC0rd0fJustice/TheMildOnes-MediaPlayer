import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

public class FileInfoTest
{
    File testFile;

    @Before
    public void createFile()
    {
        testFile = new File("student/test/videos/vid1.mp4");
    }


    @Test
    public void fileInfoString_validFile_returnInfoString()
    {
        String fileInfo = FileInfo.fileInfoString(testFile);
        assertTrue(fileInfo.length() > 0);
    }

    @Test
    public void getFileExtensinon_mp4File_returnMP4()
    {
        String fileExtension = FileInfo.getFileExtension(testFile);
        assertEquals(fileExtension, "MP4");
    }

    @Test
    public void getFileSizeMB_validFile_returnCorrectSize()
    {
        String fileSizeMB = FileInfo.getFileSizeMB(testFile);
        assertEquals(fileSizeMB, "0.63 MB");
    }

    @Test
    public void getLastModifiedDate_validFile_returnValidDate()
    {
        Date modifiedDate = FileInfo.getLastModifiedDate(testFile);
        assertTrue(modifiedDate.after(new Date(0)));
    }

    @Test
    public void getCreationDate_validFile_returnValidDate()
    {
        BasicFileAttributes fileAttributes = null;
        try {
            fileAttributes = Files.readAttributes(testFile.toPath(),BasicFileAttributes.class);
        }
        catch(IOException e)
        {
            fail();
        }

        Date creationDate = FileInfo.getCreationDate(fileAttributes);
        assertTrue(creationDate.after(new Date(0)));
    }

    @Test
    public void getPath_validFile_returnSomePath()
    {
        String filePath = FileInfo.getPath(testFile);
        assertTrue(filePath.length()>0);
    }
}
