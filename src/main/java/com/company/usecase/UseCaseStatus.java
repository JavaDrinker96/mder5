package com.company.usecase;

public class UseCaseStatus {

    private final Status status;
    private final Object message;

    private UseCaseStatus(Status status, Object message) {
        this.status = status;
        this.message = message;
    }

    public static UseCaseStatus success() {
        return new UseCaseStatus(Status.SUCCESS, null);
    }

    public static UseCaseStatus success(Object message) {
        return new UseCaseStatus(Status.SUCCESS, message);
    }

    public static UseCaseStatus loading(Object message) {
        return new UseCaseStatus(Status.LOADING, message);
    }

    public static UseCaseStatus loading() {
        return new UseCaseStatus(Status.LOADING, null);
    }

    public static UseCaseStatus error(Object message) {
        return new UseCaseStatus(Status.ERROR, message);
    }

    public static UseCaseStatus error() {
        return new UseCaseStatus(Status.ERROR, null);
    }

    public Status getStatus() {
        return status;
    }

    public Object getMessage() {
        return message;
    }

    public enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }

}
