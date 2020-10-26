package com.reka.api.domain;

public enum ResponseMessage {
    // OK messages
    HEARTBEAT_MESSAGE("REKA RESTful API Service Online"),
    FILE_SUCCESSFULLY_UPLOADED("The file has successfully been uploaded."),

    // Error messages
    FILE_NOT_FOUND_MESSAGE("File could not be found in the request.");

    final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
