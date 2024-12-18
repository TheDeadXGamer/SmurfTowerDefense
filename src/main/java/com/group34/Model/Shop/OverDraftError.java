
package com.group34.Model.Shop;


public class OverDraftError extends Exception { 

    public OverDraftError() {
        super("Not enough Money");
    }

    public OverDraftError(String msg) {
        super(msg);
    }

}
