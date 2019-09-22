
import static org.junit.Assert.*;
import org.junit.Test;

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
}
