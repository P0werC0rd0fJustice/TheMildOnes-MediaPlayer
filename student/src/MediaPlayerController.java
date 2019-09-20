
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;
import javafx.util.Duration;


public class MediaPlayerController
{
    @FXML
    Button playButton,forwardButton,backButton,filesButton,startButton,endButton,MediaProp,speedButton,volbtn;

    @FXML
    Slider volumeSlider,timeSlider,slider,speedSlider;

    @FXML
    Label time,speed,vollabel, filename;

    Duration duration;
    int flag=0;
    double prev;




    @FXML
    protected void handlePlayButtonAction(ActionEvent event)
    {
        System.out.println("Play button pressed");

    }
}
