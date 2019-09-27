import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Test;

public class FileListTest
{
    @Test
    public void givenFile_returnNextFile()
    {
        FileList fl = new FileList(new File("student/test/videos/vid1.mp4"));

        ArrayList<File> files = fl.getSupportedFiles();
        for(File f : files)
        {
            System.out.println(f.getName());
        }
        assertTrue(true);
    }

    @Test
    public void getNextSupportedFile_atFirstFile_returnSecondFile()
    {
        FileList fl = new FileList(new File("student/test/videos/vid1.mp4"));
        File nextFile = fl.getNextSupportedFile();

        assertEquals(nextFile.getName(), "vid2.mp4");
    }

    @Test
    public void getNextSupportedFile_atLastFile_returnFirstFile()
    {
        FileList fl = new FileList(new File("student/test/videos/vid1.mp4"));
        fl.getNextSupportedFile();
        File lastFile = fl.getNextSupportedFile();
        File firstFile = fl.getNextSupportedFile();

        assertEquals(firstFile.getName(), "vid1.mp4");
    }

}
