package com.company.usecase;

import com.company.exceptions.FilePathException;
import com.company.utils.FileUtil;
import javafx.stage.Stage;

public class GetFilePathUseCase {

    private final FileUtil fileUtil;

    public GetFilePathUseCase() {
        this.fileUtil = new FileUtil();
    }

    public void getFilePath(Stage stage, UseCaseStatusHandler handler) {
        handler.onStatusChanged(UseCaseStatus.loading());
        try {
            //TODO: move to repository
            String path = fileUtil.getFilePathOfUser(stage);
            handler.onStatusChanged(UseCaseStatus.success(path));
        } catch (FilePathException e) {
            handler.onStatusChanged(UseCaseStatus.error());
        }
    }

}
