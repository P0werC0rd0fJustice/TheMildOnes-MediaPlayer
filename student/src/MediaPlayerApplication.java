
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.media.*;
import javafx.scene.media.MediaPlayer.*;
import javafx.scene.control.*;

public class MediaPlayerApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mediaPlayer.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        //FXMLLoader.load(getClass().getResource("mediaPlayer.fxml"));
        MediaPlayerController controller = (MediaPlayerController)fxmlLoader.getController();
        primaryStage.setTitle("Mild Ones Media Player");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.getIcons().add(new Image("windowImages/media_player_icon_by_xylomon.png"));
        primaryStage.setMinWidth(700.0);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(primaryScreenBounds.getWidth() - (primaryScreenBounds.getWidth()/4));
        primaryStage.setHeight(primaryScreenBounds.getHeight() - (primaryScreenBounds.getHeight()/10));

        primaryStage.show();
        controller.setStage(primaryStage);

        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.SPACE) {
                event.consume();
                controller.playpause();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
