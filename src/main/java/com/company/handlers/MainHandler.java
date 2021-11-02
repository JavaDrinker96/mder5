package com.company.handlers;

import com.company.exceptions.EmptyComparisonException;
import com.company.exceptions.FilePathException;
import com.company.services.MessageDigestService;
import com.company.utils.AlertUtil;
import com.company.utils.FileUtil;
import com.company.utils.StringUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

public class MainHandler {
    private FileUtil fileUtil;
    private AlertUtil alertUtil;
    private StringUtil stringUtil;
    MessageDigestService messageDigestService;

    public MainHandler() {
        fileUtil = new FileUtil();
        alertUtil = new AlertUtil();
        stringUtil = new StringUtil();
        messageDigestService = new MessageDigestService();
    }

    public void setFilePathOfUserToTextField(TextField textField, Stage stage) {
        try {
            String path = fileUtil.getFilePathOfUser(stage);
            textField.setText(path);
        } catch (FilePathException e) {
            alertUtil.showAlert("Ошибка", "Ошибка получения пути к указанному файлу", Alert.AlertType.ERROR);
        }
    }

    public void compareFieldsValues(TextField firstTextField, TextField secondTextField) {
        compareStringValues(firstTextField.getText(), secondTextField.getText());
    }

    public void compareHashes(TextField firstTextField, TextField secondTextField){
        String firstHash = getFileHashByString(firstTextField.getText());
        String secondHash = getFileHashByString(secondTextField.getText());
        compareStringValues(firstHash,secondHash);
    }

    private void compareStringValues(String firstString, String secondString){
        try {
            boolean comparisonResult = stringUtil.caseInsensitiveCompare(firstString, secondString);
            if (comparisonResult) {
                alertUtil.showAlert("Результат сравнения", "Значения равны", Alert.AlertType.INFORMATION);
            } else {
                alertUtil.showAlert("Результат сравнения", "Значения не равны", Alert.AlertType.INFORMATION);
            }
        } catch (EmptyComparisonException e) {
            alertUtil.showAlert("Ошибка", "Поля не могут быть пустыми", Alert.AlertType.ERROR);
        }
    }

    public void setFileHashToField(String filePath, TextField textField) {
            String hashInString = getFileHashByString(filePath);
            textField.setText(hashInString);
    }

    private String getFileHashByString(String filePath) {
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            byte[] hash = messageDigestService.compute(inputStream.readAllBytes());
            inputStream.close();

            return stringUtil.byteArrayToHexString(hash).toLowerCase(Locale.ROOT);
        } catch (FileNotFoundException e) {
            alertUtil.showAlert("Ошибка", "Файл не найден", Alert.AlertType.ERROR);

            return "Error";
        } catch (IOException e) {
            alertUtil.showAlert("Ошибка", "Ошибка чтения файла", Alert.AlertType.ERROR);

            return "Error";
        }
    }

}
