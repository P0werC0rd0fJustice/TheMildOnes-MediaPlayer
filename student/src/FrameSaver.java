import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.WritableImage;
import javafx.scene.media.MediaView;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class FrameSaver
{
    MediaView mediaView;
    File currentFile;//for getting the path to save the frame image

    public void showFrameSaveDialog()
    {
        if(currentFile == null)
            return;

        //only save screenshots for video
        String fileType = FileInfo.getFileExtension(currentFile);
        if(!(fileType.equals("MP4") || fileType.equals("M4V") || fileType.equals("M4A")))
            return;

        TextInputDialog dialog = new TextInputDialog("screenshot.png");
        dialog.setTitle("Save Frame");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter name of the image:");

        //get the response value
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent())
        {
            boolean saved = saveFrame(result.get());
            if(!saved)
                showUnsuccessfulDialogue();
        }
    }

    //attempts to save a frame, returns false if fails
    public boolean saveFrame(String fileName)
    {
        //save in same directory as the video
        String path = currentFile.getParent() + "\\" + fileName;

        File file = new File(path);

        //check if the file already exists
        if(file.exists())
            return false;

        WritableImage frameImage = mediaView.snapshot(new SnapshotParameters(), null);

        try
        {
            ImageIO.write(SwingFXUtils.fromFXImage(frameImage, null),"png", file);
        }
        catch(IOException e)
        {
            return false;
        }

        return true;
    }


    void showUnsuccessfulDialogue()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Save Frame Failed");
        alert.setHeaderText(null);
        alert.setContentText("Could not save this frame");

        alert.showAndWait();
    }


    public void setMediaView(MediaView mv)
    {
        mediaView = mv;
    }

    public void setCurrentFile(File cf)
    {
        currentFile = cf;
    }

}
