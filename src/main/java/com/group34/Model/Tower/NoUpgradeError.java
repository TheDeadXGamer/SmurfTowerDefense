package com.group34.Model.Tower;

public class NoUpgradeError extends Exception{
    public NoUpgradeError(String message) {
        super(message);
    }

    public NoUpgradeError() {
        super("Tower has no upgrade");
    }
}
