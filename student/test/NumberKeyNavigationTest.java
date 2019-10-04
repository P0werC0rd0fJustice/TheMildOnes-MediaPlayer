import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class NumberKeyNavigationTest
{
    @Test
    public void getTimeFromKey_validKey_returnFractionDuration()
    {
        Duration totalDur = new Duration(50);

        Duration oneTenth = NumberKeyNavigation.getTimeFromKey(KeyCode.DIGIT1, totalDur);

        assertEquals(oneTenth, new Duration(5));

        Duration oneHalf = NumberKeyNavigation.getTimeFromKey(KeyCode.DIGIT5, totalDur);

        assertEquals(oneHalf, new Duration(25));
    }

    @Test
    public void getTimeFromKey_invalidKey_returnZeroDuration()
    {
        Duration totalDur = new Duration(50);

        Duration invalidKeyDuration = NumberKeyNavigation.getTimeFromKey(KeyCode.E, totalDur);

        assertEquals(invalidKeyDuration, new Duration(0));
    }
    
}
