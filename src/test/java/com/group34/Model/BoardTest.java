package com.group34.Model;

import com.group34.Model.Board.Board;
import com.group34.Model.Board.InvalidRemovalError;
import com.group34.Model.Board.PlacementError;
import com.group34.Model.Tower.LightningSmurfFactory;
import com.group34.Model.Tower.Tower;
import com.group34.Model.Tower.TowerFactory;
import com.group34.View.ViewConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {
    private Board board;
    private TowerFactory towerFactory;

    @BeforeEach
    public void setup() {
        board = new Board(ViewConstants.BOARD_SIZE);
        towerFactory = new LightningSmurfFactory();
    }

    @Test
    public void testAddTowerSuccessfully() throws PlacementError {
        Tower tower1 = towerFactory.createTower(new Point(100,100));
        boolean result = board.addTower(tower1);
        assertTrue(result);
    }

    @Test
    public void testPlaceTower() {
        Point2D point1 = new Point(100, 100);
        Point2D point2 = new Point(105, 105);
        Point2D point3 = new Point(149, 100);
        Point2D point4 = new Point(150, 100);
        Point2D point5 = new Point(-5, -5);

        Tower tower1 = towerFactory.createTower(point1);
        Tower tower2 = towerFactory.createTower(point2);
        Tower tower3 = towerFactory.createTower(point3);
        Tower tower4 = towerFactory.createTower(point4);
        Tower tower5 = towerFactory.createTower(point5);

        try {
            board.addTower(tower1);
        } catch (PlacementError e) {
            e.printStackTrace();
        }

        assertThrows(PlacementError.class, () -> board.addTower(tower2));
        assertThrows(PlacementError.class, () -> board.addTower(tower3));

        try {
            board.addTower(tower4);
        } catch (PlacementError e) {
            fail("PlacementError was thrown for tower4");
        }

        assertThrows(PlacementError.class, () -> board.addTower(tower5));
    }

    @Test
    public void testRemoveTower() {
        Tower tower1 = towerFactory.createTower(new Point(0, 0));

        assertThrows(InvalidRemovalError.class, () -> board.removeTower(tower1));

        try {
            board.addTower(tower1);
        } catch (PlacementError e) {
            e.printStackTrace();
        }

        try {
            board.removeTower(tower1);
        } catch (InvalidRemovalError e) {
            fail("PlacementError was thrown for tower4");
        }
    }
}
