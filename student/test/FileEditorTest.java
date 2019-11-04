import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class FileEditorTest
{
    FileEditor editor;
    File fileCopy;

    @Before
    public void createEditorObject()
    {
        editor = new FileEditor(null);
    }

    @Test
    public void copyCurrentFile_validFileAndNewFileName_createFileCopy()
    {
        File videoFile = new File("student/test/videos/vid1.mp4");
        editor.setCurrentFile(videoFile);
        boolean didCopy = editor.copyCurrentFile("filecopy.mp4");

        //correct return value
        assertTrue(didCopy);

        //check if actually copied
        fileCopy = new File("student/test/videos/filecopy.mp4");
        assertTrue(fileCopy.exists());
    }

    @Test
    public void copyCurrentFile_existingFileName_doNotCopyFile()
    {
        File videoFile = new File("student/test/videos/vid1.mp4");
        editor.setCurrentFile(videoFile);
        boolean didCopy = editor.copyCurrentFile("vid3.mp4");//existing file name

        //should be false because copy failed
        assertFalse(didCopy);
    }

    @Test
    public void copyCurrentFile_invalidFile_doNotCopyFile()
    {
        //this file does not exist
        File videoFile = new File("student/test/videos/notafile.mp4");
        editor.setCurrentFile(videoFile);
        boolean didCopy = editor.copyCurrentFile("filecopy.mp4");

        //copy should have not occured
        assertFalse(didCopy);
    }

    @After
    public void deleteCreatedFiles()
    {
        if(fileCopy == null)
            return;

        try
        {
            Files.deleteIfExists(fileCopy.toPath());
        }
        catch(IOException e)
        {

        }
    }
}
