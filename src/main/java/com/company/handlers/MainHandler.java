package com.company.handlers;

import com.company.services.MessageDigestService;
import com.company.usecase.*;
import com.company.utils.AlertUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainHandler {

    private final MessageDigestService messageDigestService;
    private final AlertUtil alertUtil;
    private TextField textField;

    private final UseCaseStatusHandler comparePathsHandler = status -> {
        if (status.getStatus() == UseCaseStatus.Status.LOADING) {
            //All ok.
        } else if (status.getStatus() == UseCaseStatus.Status.SUCCESS) {
            handleCompareSuccess((ComparePathsUseCase.CompareResults) status.getMessage());
        } else {
            handleCompareError((ComparePathsUseCase.CompareResults) status.getMessage());
        }
    };

    private final UseCaseStatusHandler getFilePathHandler = status -> {
        if (status.getStatus() == UseCaseStatus.Status.LOADING) {
            //All ok.
        } else if (status.getStatus() == UseCaseStatus.Status.SUCCESS) {
            handleGetPathSuccess((String) status.getMessage());
        } else {
            handleGetPathError();
        }
    };


    public MainHandler() {
        alertUtil = new AlertUtil();
        messageDigestService = new MessageDigestService();
    }

    public void setFilePathOfUserToTextField(TextField textField, Stage stage) {
        //Bad, but why not?
        this.textField = textField;
        new GetFilePathUseCase().getFilePath(stage, getFilePathHandler);
    }

    // TODO: 02.11.2021 rename to maunalCompare
    public void compareFieldsValues(TextField firstTextField, TextField secondTextField) {
        new ComparePathsUseCase().comparePaths(
                getFileHashByString(firstTextField.getText()),
                getFileHashByString(secondTextField.getText()),
                comparePathsHandler
        );
    }

    // TODO: 02.11.2021 rename to automaticCompareHashes
    public void compareHashes(TextField firstTextField, TextField secondTextField) {
        new ComparePathsUseCase().comparePaths(
                getFileHashByString(firstTextField.getText()),
                getFileHashByString(secondTextField.getText()),
                comparePathsHandler
        );
    }

    public void setFileHashToField(String filePath, TextField textField) {
        textField.setText(getFileHashByString(filePath));
    }

    private String getFileHashByString(String filePath) {
        return new GetFileHashUseCase(messageDigestService, alertUtil).getFileHash(filePath);
    }

    private void handleCompareSuccess(ComparePathsUseCase.CompareResults results) {
        if (results == ComparePathsUseCase.CompareResults.COMPARE_RESULTS_TRUE) {
            alertUtil.showAlert("Результат сравнения", "Значения равны", Alert.AlertType.INFORMATION);
        } else if (results == ComparePathsUseCase.CompareResults.COMPARE_RESULTS_FALSE) {
            alertUtil.showAlert("Результат сравнения", "Значения не равны", Alert.AlertType.INFORMATION);
        }
    }

    private void handleCompareError(ComparePathsUseCase.CompareResults message) {
        if (message == ComparePathsUseCase.CompareResults.COMPARE_RESULTS_FAILURE) {
            alertUtil.showAlert("Ошибка", "Поля не могут быть пустыми", Alert.AlertType.ERROR);
        }
    }

    private void handleGetPathSuccess(String path) {
        textField.setText(path);
    }

    private void handleGetPathError() {
        alertUtil.showAlert("Ошибка", "Ошибка получения пути к указанному файлу", Alert.AlertType.ERROR);
    }
}
