import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class NumberKeyNavigation
{
    //returns the correct time stamp to seek to given a numeric keycode and total media file length
    public static Duration getTimeFromKey(KeyCode keyCode, Duration totalDuration)
    {
        //fraction of the video to check
        //top row keycodes 48 - 57 = 0 - 9
        double fraction = (keyCode.getCode() - 48) * .1;

        if(fraction < 0 || fraction > 1)
            return new Duration(0);

        return totalDuration.multiply(fraction);
    }
}
