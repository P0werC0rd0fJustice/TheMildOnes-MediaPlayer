import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import org.junit.Test;
import java.io.File;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class ImageDirectoryTest {

        @Test
        public void getNewDirectoryStructureImages()
        {
            JFXPanel jfxPanel = new JFXPanel();
            Image test1 = new Image("cursor.png");
            Image test2 = new Image("ffbutton.png");
            Image test3 = new Image("filebutton.png");
            Image test4 = new Image("infobutton.png");
            Image test5 = new Image("pause.png");
            Exception failed = new Exception();
            try {
                Image test6 = new Image("notAnImage.png");
            } catch (Exception e) {
                failed = e;
            }

            assertFalse(test1.isError());
            assertFalse(test2.isError());
            assertFalse(test3.isError());
            assertFalse(test4.isError());
            assertFalse(test5.isError());
            assertNotNull(failed);
        }
}
