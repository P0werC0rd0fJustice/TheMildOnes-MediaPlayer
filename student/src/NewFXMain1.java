/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Graphics;
import javafx.fxml.FXML;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author Shiv
 */
public class NewFXMain1 extends Application implements MouseListener {
    
    MediaPlayer mediaPlayer;
    MediaView mediaView;
    Media media = new Media(new File("C:\\Users\\Shiv\\Videos\\Baarish - Half Girlfriend - Arjun K & Shraddha K - Ash King & Shashaa Tirupati - Tanishk Bagchi(1).mp4").toURI().toString());
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

    //The location of your file
        //Media media = new Media(new File("C:\\Users\\Shiv\\Videos\\Baarish - Half Girlfriend - Arjun K & Shraddha K - Ash King & Shashaa Tirupati - Tanishk Bagchi(1).mp4").toURI().toString());

        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView = new MediaView(mediaPlayer);

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(mediaView);
        borderPane.setCenter(addToolBar());

        borderPane.setStyle("-fx-background-color: Black");

        Scene scene = new Scene(borderPane, 600, 600);
        //scene.setFill(Color.BLACK);
        DropShadow dropshadow = new DropShadow();
        dropshadow.setOffsetY(5.0);
        dropshadow.setOffsetX(5.0);
        //dropshadow.setColor(Color.WHITE);

        mediaView.setEffect(dropshadow);
        primaryStage.setTitle("Media Player!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    Button playButton,pauseButton,forwardButton,backButton,filesButton;
    private HBox addToolBar() throws FileNotFoundException {
        HBox toolBar = new HBox();
        //toolBar.setPadding(new Insets(10,10,10,10));
        toolBar.setAlignment(Pos.TOP_CENTER);
        toolBar.alignmentProperty().isBound();
        toolBar.setSpacing(5);
        toolBar.setStyle("-fx-background-color: white");
        //File imageFile = new File("C:\\Users\\Shiv\\Documents\\NetBeansProjects\\student\\Play.png");
        //File pic=new File(this.getClass().getResource("C:\\Users\\Shiv\\Documents\\NetBeansProjects\\student\\Play.png").getFile());
        //FileInputStream fis=new FileInputStream(pic);
        //Image playimage = new Image(fis);
        //Image playimage = new Image(new FileInputStream("C:\\Users\\Shiv\\Documents\\NetBeansProjects\\student\\Play.png"));
        
        //Image playButtonImage = new Image(getClass().getResourceAsStream("Play.png")) {};
        playButton = new Button();
        //playButton.setGraphic(new ImageView(playButtonImage));
        //playButton.setStyle("-fx-background-color: Orange");
        pauseButton = new Button();
        //playButton.setGraphic(new ImageView(playimage));
        //pauseButton.setStyle("-fx-background-color: Orange");

        playButton.setOnAction((ActionEvent e) -> {
            //playButton.setStyle("-fx-background-color: Blue");   
            mediaPlayer.play();
        });
        pauseButton.setOnAction((ActionEvent e) -> {
            mediaPlayer.pause();
        });
        
        backButton = new Button();
        forwardButton = new Button();
        filesButton = new Button();
        //playButton.setGraphic(new ImageView(playButtonImage));
        pauseButton.setStyle("-fx-graphic: url('Pause.png')");
        playButton.setStyle("-fx-graphic: url('Play.png')");
        backButton.setStyle("-fx-graphic: url('back.png')");
        forwardButton.setStyle("-fx-graphic: url('fast.png')");
        filesButton.setStyle("-fx-graphic: url('file.png')");
        //playButton.setGraphic(new ImageView(playimage));
        //pauseButton.setStyle("-fx-background-color: Orange");
        
        backButton.setOnAction((ActionEvent e) -> {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().divide(1.2));
        });
        forwardButton.setOnAction((ActionEvent e) -> {
            mediaPlayer.seek(mediaPlayer.getCurrentTime().multiply(1.2));
        });
        
        filesButton.setOnAction((ActionEvent e) -> {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().add(new ExtensionFilter("*.flv", "*.mp4", "*.mpeg"));
            File file = fc.showOpenDialog(null);
            String path = file.getAbsolutePath();
            path = path.replace("\\", "/");
            media = new Media(new File(path).toURI().toString());
            mediaPlayer.stop();
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaView.setMediaPlayer(mediaPlayer);
        });
        
        /*playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
        playButton.setStyle("-fx-background-color: Black");
        playButton.setStyle("-fx-body-color: Black");
        });*/

        toolBar.getChildren().addAll(filesButton,playButton,pauseButton,backButton,forwardButton);
        return toolBar;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        playButton.setStyle("-fx-background-color: Black");
        playButton.setStyle("-fx-body-color: Black");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
