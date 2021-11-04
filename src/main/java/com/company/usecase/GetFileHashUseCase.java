package com.company.usecase;

import com.company.services.MessageDigestService;
import com.company.utils.AlertUtil;
import com.company.utils.StringUtil;
import javafx.scene.control.Alert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

public class GetFileHashUseCase {

    private final MessageDigestService service;
    private final StringUtil utils;
    private final AlertUtil alertUtil;

    public GetFileHashUseCase(MessageDigestService service, AlertUtil alertUtil) {
        this.service = service;
        this.utils = new StringUtil();
        this.alertUtil = alertUtil;
    }

    public String getFileHash(String filePath) {
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            byte[] hash = service.compute(inputStream.readAllBytes());
            inputStream.close();

            return utils.byteArrayToHexString(hash).toLowerCase(Locale.ROOT);
        } catch (FileNotFoundException e) {
            alertUtil.showAlert("Ошибка", "Файл не найден", Alert.AlertType.ERROR);

            return "Error";
        } catch (IOException e) {
            alertUtil.showAlert("Ошибка", "Ошибка чтения файла", Alert.AlertType.ERROR);

            return "Error";
        }
    }

}
