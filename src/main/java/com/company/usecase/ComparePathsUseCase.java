package com.company.usecase;

import com.company.exceptions.EmptyComparisonException;
import com.company.utils.StringUtil;

public class ComparePathsUseCase {

    private final StringUtil utils = new StringUtil();

    public void comparePaths(String firstPath, String secondPath, UseCaseStatusHandler statusHandler) {
        statusHandler.onStatusChanged(UseCaseStatus.loading(UseCaseStatus.loading()));
        try {
            boolean comparisonResult = utils.caseInsensitiveCompare(firstPath, secondPath);
            CompareResults results = comparisonResult ? CompareResults.COMPARE_RESULTS_TRUE : CompareResults.COMPARE_RESULTS_FALSE;
            statusHandler.onStatusChanged(UseCaseStatus.success(results));
        } catch (EmptyComparisonException e) {
            statusHandler.onStatusChanged(UseCaseStatus.error(CompareResults.COMPARE_RESULTS_FAILURE));
        }
    }

    public enum CompareResults {
        COMPARE_RESULTS_TRUE,
        COMPARE_RESULTS_FALSE,
        COMPARE_RESULTS_FAILURE,
    }
}
