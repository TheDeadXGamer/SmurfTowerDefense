package com.group34.Model.Board;

public class InvalidRemovalError extends Exception {

    public InvalidRemovalError() {
        super("Invalid removal");
    }

    public InvalidRemovalError(String msg) {
        super(msg);
    }

}
