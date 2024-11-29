
package com.group34.Model.Cash;


public class OverDraftError extends Exception { 

    public OverDraftError() {
        super("Not enough Money");
    }

    public OverDraftError(String msg) {
        super(msg);
    }

}
