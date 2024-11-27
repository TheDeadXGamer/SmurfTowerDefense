
package com.group34.Model.Tower;


public class OverDraftError extends Exception { 

    public OverDraftError() {
        super("Not enough Money");
    }

    public OverDraftError(String msg) {
        super(msg);
    }

}
