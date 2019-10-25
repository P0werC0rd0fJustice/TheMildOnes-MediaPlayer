import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

public class FileEditor
{
    File currentFile;

    public void showEditorDialogue()
    {
        if(currentFile == null)
            return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("File Editor");
        alert.setHeaderText(null);
        alert.setContentText("Choose An Action");

        ButtonType renameFileButton = new ButtonType("Edit File Name");
        ButtonType copyFileButton = new ButtonType("Save A Copy");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(copyFileButton, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == copyFileButton)
        {
            showCopyDialogue();
        }

    }

    void showCopyDialogue()
    {
        if(currentFile == null)
            return;

        TextInputDialog dialog = new TextInputDialog("newfile." + FileInfo.getFileExtension(currentFile).toLowerCase());
        dialog.setTitle("Create a Copy");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter name of the new file:");

        //get the response value
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent())
        {
            boolean copied = copyCurrentFile(result.get());
            if(!copied)
                showUnsuccessfulCopyDialogue();
        }
    }

    void showUnsuccessfulCopyDialogue()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Copy File Failed");
        alert.setHeaderText(null);
        alert.setContentText("Could not make a copy of the file");

        alert.showAndWait();
    }

    public void setCurrentFile(File file)
    {
        currentFile = file;
    }

    //attempts to make a copy of currentFile
    //returns false if unsuccessful
    boolean copyCurrentFile(String newFileName)
    {
        String path = currentFile.getParent() + "\\" + newFileName;

        //check if the file already exists
        if (new File(path).exists())
            return false;

        try
        {
            Path copied = Paths.get(currentFile.getParent() + "\\" + newFileName);
            Path originalPath = currentFile.toPath();
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException e)//copy was unsuccessful
        {
            return false;
        }

        return true;
    }

    //attempts to rename the currentFile
    //returns false if unsuccessful
  /*  boolean renameCurrentFile(String newName)
    {
        System.out.println(currentFile.getPath());
        File newFile = new File(currentFile.getPath() + "\\" + newName);

        if (newFile.exists())
            return false;

        try
        {
            Path source = Paths.get(currentFile.getPath());
            Files.move(source, source.resolveSibling("newname.mp4"));
        }
        catch(IOException e)
        {
            System.out.println("didn't work");
            return false;
        }


    }*/
}
