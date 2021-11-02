package com.company.utils;

import com.company.exceptions.FilePathException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileUtil {

    public String getFilePathOfUser(Stage stage) throws FilePathException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        if (file == null) {
            throw new FilePathException("Error of getting absolute file path");
        }

        return file.getAbsolutePath();
    }
}
