import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Test;

public class FileListTest
{
    @Test
    public void getNumberSupportedFileCount_threeSupportedFiles_returnThree()
    {
        FileList fl = new FileList(new File("student/test/videos/vid1.mp4"));

        int numSupported = fl.getSupportedFileCount();

        assertEquals(numSupported, 3);
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
