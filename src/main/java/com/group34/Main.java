package com.group34;

import java.awt.geom.Point2D;
import java.util.List;

import com.group34.Model.Board.Board;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.Player;
import com.group34.Model.Road.RoadBuilder;
import com.group34.Model.Road.RoadSpawn;
import com.group34.Model.Round.Round;
import com.group34.Model.Round.RoundConfig;
import com.group34.Model.Shop.CashVault;
import com.group34.Model.Shop.Shop;
import com.group34.Model.Shop.ShopItem;
import com.group34.Model.Tower.*;
import com.group34.View.ViewConstants;
import com.group34.Model.Tower.FireSmurfFactory;
import com.group34.Model.Tower.LightningSmurfFactory;
import com.group34.Model.Tower.Tower;
import com.group34.Model.Tower.TowerWrapper;
import com.group34.Model.Tower.WaterSmurfFactory;

public class Main {
    public static void main (String[] args) throws Exception {
        Point2D position = new Point2D.Double(100, 100);

        // should these exist in the final game?
        Tower tower = new TowerWrapper(new LightningSmurfFactory().createTower(position)) ;
        Tower tower2 = new TowerWrapper(new LightningSmurfFactory().createTower(new Point2D.Double(100,400))).upgrade();

        Player player = new Player(30);
        CashVault cashVault = new CashVault(100);
        Board board = new Board(ViewConstants.BOARD_SIZE);
        Game game = new Game();
        
        ShopItem lightningItem = new ShopItem(new LightningSmurfFactory(), 50);
        ShopItem fireItem = new ShopItem(new FireSmurfFactory(), 100);
        ShopItem waterItem = new ShopItem(new WaterSmurfFactory(), 75);
        Shop shop = new Shop(cashVault, board)
            .addItem(lightningItem)
            .addItem(fireItem)
            .addItem(waterItem);
            
        RoadSpawn spawn = new RoadBuilder(board, player)
                .add(new Point2D.Double(671., 0))
                .add(new Point2D.Double(666., 32.))
                .add(new Point2D.Double(661., 58.))
                .add(new Point2D.Double(653., 84.))
                .add(new Point2D.Double(648., 102.))
                .add(new Point2D.Double(638., 115.))
                .add(new Point2D.Double(625., 127.))
                .add(new Point2D.Double(612., 136.))
                .add(new Point2D.Double(599., 140.))
                .add(new Point2D.Double(576., 139.))
                .add(new Point2D.Double(554., 134.))
                .add(new Point2D.Double(530., 129.))
                .add(new Point2D.Double(517., 123.))
                .add(new Point2D.Double(496., 117.))
                .add(new Point2D.Double(478., 107.))
                .add(new Point2D.Double(452., 101.))
                .add(new Point2D.Double(427., 95.))
                .add(new Point2D.Double(401., 90.))
                .add(new Point2D.Double(371., 86.))
                .add(new Point2D.Double(345., 86.))
                .add(new Point2D.Double(317., 85.))
                .add(new Point2D.Double(292., 86.))
                .add(new Point2D.Double(268., 88.))
                .add(new Point2D.Double(242., 91.))
                .add(new Point2D.Double(216., 96.))
                .add(new Point2D.Double(190., 105.))
                .add(new Point2D.Double(170., 114.))
                .add(new Point2D.Double(145., 133.))
                .add(new Point2D.Double(131., 163.))
                .add(new Point2D.Double(120., 193.))
                .add(new Point2D.Double(111., 230.))
                .add(new Point2D.Double(109., 263.))
                .add(new Point2D.Double(111., 301.))
                .add(new Point2D.Double(129., 341.))
                .add(new Point2D.Double(150., 377.))
                .add(new Point2D.Double(190., 415.))
                .add(new Point2D.Double(240., 432.))
                .add(new Point2D.Double(298., 447.))
                .add(new Point2D.Double(368., 450.))
                .add(new Point2D.Double(436., 449.))
                .add(new Point2D.Double(499., 442.))
                .add(new Point2D.Double(531., 412.))
                .add(new Point2D.Double(543., 381.))
                .add(new Point2D.Double(524., 355.))
                .add(new Point2D.Double(485., 349.))
                .add(new Point2D.Double(428., 357.))
                .add(new Point2D.Double(365., 357.))
                .add(new Point2D.Double(311., 351.))
                .add(new Point2D.Double(267., 348.))
                .add(new Point2D.Double(232., 340.))
                .add(new Point2D.Double(205., 310.))
                .add(new Point2D.Double(187., 288.))
                .add(new Point2D.Double(176., 260.))
                .add(new Point2D.Double(174., 224.))
                .add(new Point2D.Double(189., 192.))
                .add(new Point2D.Double(219., 171.))
                .add(new Point2D.Double(256., 161.))
                .add(new Point2D.Double(296., 150.))
                .add(new Point2D.Double(338., 150.))
                .add(new Point2D.Double(382., 154.))
                .add(new Point2D.Double(434., 168.))
                .add(new Point2D.Double(474., 186.))
                .add(new Point2D.Double(478., 189.))
                .add(new Point2D.Double(424., 208.))
                .add(new Point2D.Double(376., 216.))
                .add(new Point2D.Double(321., 221.))
                .add(new Point2D.Double(285., 239.))
                .add(new Point2D.Double(302., 277.))
                .add(new Point2D.Double(382., 280.))
                .add(new Point2D.Double(449., 282.))
                .add(new Point2D.Double(552., 282.))
                .add(new Point2D.Double(647., 272.))
                .add(new Point2D.Double(717., 272.))
                .add(new Point2D.Double(786., 271.))
                .build();

        board.addTower(tower);
        board.addTower(tower2);

        TowerDefence towerDefence = new TowerDefenceBuilder()
            .setCashVault(cashVault)
            .setGame(game)
            .setBoard(board)
            .setPlayer(player)
            .setRoadSpawn(spawn)

            .setShop(shop)
            .setGameSpeed(GameSpeed.NORMAL)
            .build();

        Thread thread = new Thread(towerDefence);
        thread.start();
    }
}
