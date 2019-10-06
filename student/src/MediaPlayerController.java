
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
//import javafx.fxml.Initializable;

import java.awt.*;
import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.application.Platform.runLater;


public class MediaPlayerController
{

    @FXML
    MediaView mediaView;

    @FXML
    private Button testButton;

    @FXML
    private MediaView testMediaView;

    @FXML
    Button playButton, forwardButton, backButton, filesButton, startButton, endButton, infoButton, speedButton, volumeButton;

    @FXML
    Slider volumeSlider,timeSlider,slider,speedSlider;

    @FXML
    Label timeLabel,speedLabel, volumeLabel, filenameLabel;

    @FXML
    BorderPane borderPane;

    @FXML
    HBox hBox;

    Duration duration;
    int flag=0;
    double prev;

    FileChooser fc;
    MediaPlayer mediaPlayer;



    Media media;
    Image image;
    DropShadow dropshadow;
    //BorderPane borderPane;
    Button enterButton = new Button();
    String fileName="";
    Image cur = new Image("cursor.png");
    int playButtonCount = 0;
    int fullScreenClick=1;

    FileList fileList;

    Stage primaryStage;

    boolean isFullScreen = false;

    @FXML
    public void initialize()
    {
        System.out.println(playButton);

        //set tooltips
        playButton.setTooltip(new Tooltip("Play"));
        startButton.setTooltip(new Tooltip("Replay"));
        backButton.setTooltip(new Tooltip("Rewind"));
        forwardButton.setTooltip(new Tooltip("Forward"));
        filesButton.setTooltip(new Tooltip("Open New File"));
        volumeButton.setTooltip(new Tooltip("Volume"));
        infoButton.setTooltip(new Tooltip("Details"));
        speedButton.setTooltip(new Tooltip("Default Speed"));

        //TODO: replace this with layout tricks
        HBox.setHgrow(timeSlider, Priority.ALWAYS);

        volumeSlider.valueProperty().addListener((Observable ov) -> onVolumeSliderChange(ov));
        timeSlider.valueProperty().addListener((Observable ov) -> onTimeSliderChange(ov));
        speedSlider.valueProperty().addListener((Observable ov) -> onSpeedSliderChange(ov));

        mediaView.fitWidthProperty().bind(borderPane.widthProperty());
        mediaView.fitHeightProperty().bind(borderPane.heightProperty().subtract(40));
       // hBox.prefWidthProperty().bind(borderPane.widthProperty());


    }

    @FXML
    protected void onSizeChange()
    {
        if(hBox.getWidth() <= 950 ){
            hBox.setSpacing(0);
            speedButton.setManaged(false);
            speedButton.setVisible(false);
            filesButton.setText("");
            volumeLabel.setScaleX(0);
            volumeLabel.setScaleY(0.);
            volumeLabel.setManaged(false);
        }
        else{
            hBox.setSpacing(5);
            speedButton.setManaged(true);
            speedButton.setVisible(true);
            filesButton.setText("File");
            volumeLabel.setScaleX(1);
            volumeLabel.setScaleY(1);
            volumeLabel.setManaged(true);
        }

    }

    @FXML
    protected void handlePlayButtonAction(ActionEvent event)
    {
        playButtonCount++;
        if (playButtonCount % 2 == 1) {
            playButton.setStyle("-fx-graphic: url('playbutton.png');");
            playButton.setTooltip(new Tooltip("Play"));
            mediaPlayer.pause();
        } else {
            playButton.setStyle("-fx-graphic: url('pausebutton.png');");
            playButton.setTooltip(new Tooltip("Pause"));
            mediaPlayer.play();
        }
    }

    @FXML
    protected void handleFileButtonAction(ActionEvent event)
    {
        chooseFile(1);
    }

    @FXML
    protected void handleStartButtonAction(ActionEvent event)
    {
        mediaPlayer.seek(Duration.ZERO);
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event)
    {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().divide(1.2));
    }

    @FXML
    protected void handleForwardButtonAction(ActionEvent event)
    {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().multiply(1.2));
    }

    @FXML
    protected void handleVolumeButtonAction(ActionEvent event)
    {
        flag++;
        if (flag % 2 == 1) {
            volumeButton.setStyle("-fx-graphic: url('volumemutebutton.png');");
            prev = volumeSlider.getValue();
            volumeButton.setTooltip(new Tooltip("Mute"));
            mediaPlayer.setVolume(0);
            volumeSlider.setValue(0);
            volumeLabel.setText("Vol:" + Integer.toString((int) (0)) + "%");

        } else {
            volumeButton.setStyle("-fx-graphic: url('volumemaxbutton.png');");
            volumeButton.setTooltip(new Tooltip("Volume"));
            mediaPlayer.setVolume(prev / 100.0);
            volumeSlider.setValue(prev);
            volumeLabel.setText("Vol:" + Integer.toString((int) (prev)) + "%");
        }
    }

    @FXML
    protected void handleInfoButtonAction(ActionEvent event)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("Details");
        a.setContentText(fileName);
        a.setTitle("Properties");
        a.showAndWait();
    }

    @FXML
    protected void handleSpeedButtonAction(ActionEvent event)
    {
        mediaPlayer.setRate(1);
        speedSlider.setValue(50.0);
    }


    void onVolumeSliderChange(Observable ov)
    {
        if (volumeSlider.isValueChanging()) {
            mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
            volumeLabel.setText("Vol:" + Integer.toString((int) (volumeSlider.getValue())) + "%");
        }
        if (volumeSlider.isPressed()) {
            mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
            volumeLabel.setText("Vol:" + Integer.toString((int) (volumeSlider.getValue())) + "%");
        }
        if (volumeSlider.getValue() == 0) {
            volumeButton.setStyle("-fx-graphic: url('volumemutebutton.png');");
            volumeButton.setTooltip(new Tooltip("Mute"));
            volumeLabel.setText("Vol:" + Integer.toString((int) (volumeSlider.getValue())) + "%");
        } else {
            volumeButton.setStyle("-fx-graphic: url('volumemaxbutton.png');");
            volumeButton.setTooltip(new Tooltip("Volume"));
            volumeLabel.setText("Vol:" + Integer.toString((int) (volumeSlider.getValue())) + "%");
        }
    }

    void onTimeSliderChange(Observable ov)
    {
        if (timeSlider.isValueChanging()) {
            //System.out.println(timeSlider.getValue());
            mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
            currentTime = Duration.seconds(timeSlider.getValue());
        }
        if (timeSlider.isPressed()) {
            mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
            currentTime = Duration.seconds(timeSlider.getValue());
        }

    }

    void onSceneScroll(ScrollEvent event)
    {
        volumeSlider.setValueChanging(true);
        double deltaY = event.getDeltaY();
        double prev = volumeSlider.getValue();

        volumeSlider.setValue(prev + (deltaY/20));
    }


    void onSpeedSliderChange(Observable ov)
    {
        if (speedSlider.isValueChanging() || speedSlider.isPressed()) {
            double rate = speedSlider.getValue() / 10;
            if (rate < 1)
                rate = 0.5;
            else if (rate < 2)
                rate = 0.6;
            else if (rate < 3)
                rate = 0.7;
            else if (rate < 4)
                rate = 0.8;
            else if (rate < 5)
                rate = 0.9;
            else if (rate < 6)
                rate = 1;
            else if (rate < 7)
                rate = 1.1;
            else if (rate < 8)
                rate = 1.2;
            else if (rate < 9)
                rate = 1.3;
            else
                rate = 1.4;
            mediaPlayer.setRate(rate);
        }

    }


    void chooseFile(int prev)
    {
        try {
            playButtonCount = 0;
            fc = new FileChooser();
            //fc.getExtensionFilters().add(new ExtensionFilter("*.flv", "*.mp4", "*.mpeg","*.mp3","*.mkv"));
            File file = fc.showOpenDialog(null);
            if(file==null) return;

            Double std = (double) file.length() / (1024*1024); //File size in MB
            String fileSize = String.format("%.2f", std);
            Date d = new Date(file.lastModified());
            fileName = "Name: " + (String)file.getName() + "\nPath: " + getPath(file) + "\nSize: " + fileSize+"MB\n Last Modified: "+d.toString();

            if (!FileInfo.supportedFile(file.getName())) {
                Alert err = new Alert(Alert.AlertType.ERROR);
                err.setTitle("ERROR!");
                err.setHeaderText("Not Supported");
                err.setContentText("Invalid File Type. Please choose again among the following file types : mp3,mp4,mpeg,flv");
                err.showAndWait();
            }
            else {
                displayFile(file);
                fileList = new FileList(file);
            }
        } catch (Exception ex) {
            Logger.getLogger(MainMediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //opens the file in the mediaplayer
    void displayFile(File file)
    {
        String path = getPath(file);
        int flag2=1;
        if (file.getName().substring(file.getName().length()-3,file.getName().length()).compareTo("mp3")==0) {
            flag2=0;
            ImageView img2=new ImageView();
            img2.setImage(image);
            img2.setFitWidth(1200);
            img2.setFitHeight(600);
            img2.autosize();
            img2.setEffect(dropshadow);
            //borderPane.setCenter(img2);
        }
        media = new Media(new File(path).toURI().toString());

        if (prev == 1) {
           // mediaPlayer.stop();
        }
        mediaPlayer = new MediaPlayer(media);
        System.out.println(mediaPlayer + " " + mediaView);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        addMediaPlayerListeners();


    }


    public String getPath(File file)
    {
        String path = file.getAbsolutePath();
        path = path.replace("\\", "/");

        return path;
    }

    Duration currentTime;
    protected void updateValues(Duration currentTime) {
        if (timeLabel != null) {
            runLater(() -> {
                timeLabel.setText(StringFormat.formatTime(currentTime, duration));
            });
        }
    }


    void addMediaPlayerListeners()
    {
        mediaPlayer.currentTimeProperty().addListener((Observable ov) -> {
            currentTime = mediaPlayer.getCurrentTime();
            updateValues(currentTime);
        });

        mediaPlayer.setOnReady(() -> {
            duration = mediaPlayer.getMedia().getDuration();
            currentTime = mediaPlayer.getCurrentTime();
            updateValues(currentTime);
        });

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!timeSlider.isValueChanging()) {
                timeSlider.setValue(newTime.toSeconds());
            }
        });

        mediaView.setOnScroll(e -> onSceneScroll(e));

        timeSlider.maxProperty().bind(Bindings.createDoubleBinding(
                () -> mediaPlayer.getTotalDuration().toSeconds(),
                mediaPlayer.totalDurationProperty()));
    }


    @FXML
    private void handleOnKeyPressed(KeyEvent event)
    {
        //play the next file when N is pressed
        if (event.getCode().equals(KeyCode.N))
        {
            displayFile(fileList.getNextSupportedFile());
        }

        if(event.getCode().isDigitKey())
        {
            Duration dur = NumberKeyNavigation.getTimeFromKey(event.getCode(), mediaPlayer.getTotalDuration());
            mediaPlayer.seek(dur);
        }

        if(event.getCode()==KeyCode.F){
            toggleFullScreen(primaryStage);
        }

    }

    void toggleFullScreen(Stage primaryStage){
        if (!primaryStage.isFullScreen()){
            primaryStage.setFullScreen(true);
        }
        else primaryStage.setFullScreen(false);
    }


    public void setStage(Stage stage)
    {
        primaryStage = stage;
    }

}
