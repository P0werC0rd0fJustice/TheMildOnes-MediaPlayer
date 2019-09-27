
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;

public class FileTests {


    @Test
    public void givenSupportedFile_whenSupportCheck_thenReturnTrue()
    {
        String fileName = "movie.mpeg";
        boolean supported = FileInfo.supportedFile(fileName);
        assertEquals(supported, true); //uses .equals() internally
    }

    @Test
    public void givenUnsupportedFile_whenSupportCheck_thenReturnFalse()
    {
        String fileName = "movie.mov";
        boolean supported = FileInfo.supportedFile(fileName);
        assertFalse(supported);
    }

    @Test
    public void givenFile_returnNextFile()
    {
        System.out.println("test");
        final File folder = new File("student");
        listFilesForFolder(folder);
        assertTrue(true);
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                //listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }


}
