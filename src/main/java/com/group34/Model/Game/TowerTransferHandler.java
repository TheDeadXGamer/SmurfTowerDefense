package com.group34.Model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class TowerTransferHandler extends TransferHandler {
    private final ImageIcon towerImage;

    public TowerTransferHandler(ImageIcon towerImage) {
        this.towerImage = towerImage;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        return new StringSelection("tower");
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }

    @Override
    public Icon getVisualRepresentation(Transferable t) {
        return towerImage;
    }
}