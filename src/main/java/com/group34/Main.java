package com.group34;

import java.awt.Dimension;
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
import com.group34.Model.Tower.LightningSmurfFactory;
import com.group34.Model.Tower.Tower;


public class Main {
    public static void main (String[] args) throws Exception {
    
        Point2D position = new Point2D.Double(100, 100);
        Tower tower = new LightningSmurfFactory().createTower(position);
        Tower tower2 = new LightningSmurfFactory().createTower(new Point2D.Double(400,300));
        Player player = new Player(30);
        CashVault cashVault = new CashVault(100);
        Board board = new Board(new Dimension(815, 635));
        Game game = new Game();  
        
        ShopItem item = new ShopItem(new LightningSmurfFactory(), 50);
        Shop shop = new Shop(cashVault, board).addItem(item);

        List<Round> rounds = RoundConfig.createRounds(cashVault);

        RoadSpawn spawn = new RoadBuilder(board, player)
                .add(new Point2D.Double(682.8535669586984, 7.046979865771812))
                .add(new Point2D.Double(671.8397997496871, 77.51677852348993))
                .add(new Point2D.Double(645.8072590738423, 148.99328859060404))
                .add(new Point2D.Double(560.7008760951189, 162.08053691275168))
                .add(new Point2D.Double(489.6120150187735, 128.85906040268455))
                .add(new Point2D.Double(428.5356695869838, 108.72483221476509))
                .add(new Point2D.Double(350.4380475594493, 95.63758389261744))
                .add(new Point2D.Double(263.3291614518148, 105.70469798657717))
                .add(new Point2D.Double(189.23654568210262, 121.81208053691276))
                .add(new Point2D.Double(141.1764705882353, 174.16107382550337))
                .add(new Point2D.Double(113.14142678347936, 256.7114093959732))
                .add(new Point2D.Double(109.13642052565706, 335.23489932885906))
                .add(new Point2D.Double(134.16770963704633, 406.7114093959732))
                .add(new Point2D.Double(169.21151439299123, 468.12080536912754))
                .add(new Point2D.Double(222.27784730913643, 493.2885906040268))
                .add(new Point2D.Double(296.37046307884856, 511.40939597315435))
                .add(new Point2D.Double(366.4580725907384, 518.4563758389262))
                .add(new Point2D.Double(433.54192740926163, 514.4295302013422))
                .add(new Point2D.Double(496.6207759699625, 508.38926174496646))
                .add(new Point2D.Double(524.6558197747185, 488.25503355704694))
                .add(new Point2D.Double(552.6908635794744, 450.0))
                .add(new Point2D.Double(541.677096370463, 411.74496644295306))
                .add(new Point2D.Double(504.630788485607, 400.6711409395973))
                .add(new Point2D.Double(459.57446808510645, 406.7114093959732))
                .add(new Point2D.Double(405.5068836045057, 410.73825503355704))
                .add(new Point2D.Double(325.4067584480601, 410.73825503355704))
                .add(new Point2D.Double(263.3291614518148, 395.63758389261744))
                .add(new Point2D.Double(217.27158948685857, 376.510067114094))
                .add(new Point2D.Double(189.23654568210262, 339.26174496644296))
                .add(new Point2D.Double(176.22027534418024, 289.93288590604027))
                .add(new Point2D.Double(181.22653316645807, 234.56375838926175))
                .add(new Point2D.Double(221.27659574468086, 197.31543624161074))
                .add(new Point2D.Double(283.3541927409262, 177.18120805369128))
                .add(new Point2D.Double(340.4255319148936, 171.14093959731542))
                .add(new Point2D.Double(389.4868585732165, 174.16107382550337))
                .add(new Point2D.Double(434.54317897371715, 185.23489932885906))
                .add(new Point2D.Double(466.5832290362954, 199.3288590604027))
                .add(new Point2D.Double(472.59073842302877, 220.46979865771812))
                .add(new Point2D.Double(453.56695869837296, 237.58389261744966))
                .add(new Point2D.Double(413.5168961201502, 240.60402684563758))
                .add(new Point2D.Double(360.450563204005, 247.6510067114094))
                .add(new Point2D.Double(325.4067584480601, 258.7248322147651))
                .add(new Point2D.Double(292.36545682102627, 270.80536912751677))
                .add(new Point2D.Double(284.35544430538175, 293.9597315436242))
                .add(new Point2D.Double(302.37797246558193, 318.12080536912754))
                .add(new Point2D.Double(342.42803504380475, 323.1543624161074))
                .add(new Point2D.Double(405.5068836045057, 320.13422818791946))
                .add(new Point2D.Double(476.59574468085106, 324.16107382550337))
                .add(new Point2D.Double(557.6971214017523, 326.1744966442953))
                .add(new Point2D.Double(634.793491864831, 313.0872483221477))
                .add(new Point2D.Double(706.8836045056321, 309.06040268456377))
                .add(new Point2D.Double(765.9574468085107, 308.05369127516775))
                .add(new Point2D.Double(794.9937421777222, 305.03355704697987))
                .build();


        board.addTower(tower);
        board.addTower(tower2);

        TowerDefence towerDefence = new TowerDefenceBuilder()
            .setCashVault(cashVault)
            .setGame(game)
            .setBoard(board)
            .setPlayer(player)
            .setRoadSpawn(spawn)
            .setRounds(rounds)
            .setShop(shop)
            .setGameSpeed(GameSpeed.NORMAL)
            .build();

        Thread thread = new Thread(towerDefence);
        thread.start();

    }
}
