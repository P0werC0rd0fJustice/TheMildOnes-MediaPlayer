
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MediaPlayerApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mediaPlayer.fxml"));
        primaryStage.setTitle("Mild Ones Media Player");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.getIcons().add(new Image("media_player_icon_by_xylomon.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}