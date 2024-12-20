package com.group34;

import java.awt.geom.Point2D;

import com.group34.Model.Board.Board;
import com.group34.Model.Game.Game;
import com.group34.Model.Game.Player;
import com.group34.Model.Road.RoadBuilder;
import com.group34.Model.Road.RoadSpawn;
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
                .add(new Point2D.Double(689.8623279098873, 0))
                .add(new Point2D.Double(680.8510638297872, 27.18120805369127))
                .add(new Point2D.Double(677.8473091364206, 62.41610738255034))
                .add(new Point2D.Double(668.8360450563204, 103.69127516778524))
                .add(new Point2D.Double(651.8147684605757, 135.90604026845637))
                .add(new Point2D.Double(631.7897371714644, 160.06711409395973))
                .add(new Point2D.Double(605.7571964956195, 161.0738255033557))
                .add(new Point2D.Double(569.712140175219, 154.02684563758388))
                .add(new Point2D.Double(526.6583229036296, 141.94630872483222))
                .add(new Point2D.Double(480.6007509386734, 121.81208053691276))
                .add(new Point2D.Double(437.54693366708386, 107.71812080536913))
                .add(new Point2D.Double(391.48936170212767, 99.66442953020135))
                .add(new Point2D.Double(336.4205256570713, 94.63087248322147))
                .add(new Point2D.Double(284.35544430538175, 99.66442953020135))
                .add(new Point2D.Double(230.28785982478098, 110.73825503355705))
                .add(new Point2D.Double(187.2340425531915, 131.87919463087246))
                .add(new Point2D.Double(153.19148936170214, 165.1006711409396))
                .add(new Point2D.Double(130.162703379224, 204.36241610738253))
                .add(new Point2D.Double(120.15018773466835, 253.69127516778522))
                .add(new Point2D.Double(113.14142678347936, 301.00671140939596))
                .add(new Point2D.Double(118.14768460575719, 347.3154362416107))
                .add(new Point2D.Double(131.1639549436796, 388.59060402684565))
                .add(new Point2D.Double(152.19023779724657, 428.8590604026846))
                .add(new Point2D.Double(179.22403003754695, 462.08053691275165))
                .add(new Point2D.Double(212.26533166458074, 484.2281879194631))
                .add(new Point2D.Double(259.32415519399245, 504.36241610738256))
                .add(new Point2D.Double(310.38798498122657, 514.4295302013422))
                .add(new Point2D.Double(371.4643304130162, 518.4563758389262))
                .add(new Point2D.Double(418.52315394242805, 515.4362416107382))
                .add(new Point2D.Double(473.5919899874844, 512.4161073825503))
                .add(new Point2D.Double(517.6470588235294, 503.3557046979866))
                .add(new Point2D.Double(545.6821026282854, 464.09395973154363))
                .add(new Point2D.Double(549.6871088861076, 421.81208053691273))
                .add(new Point2D.Double(529.6620775969963, 403.6912751677852))
                .add(new Point2D.Double(495.61952440550687, 401.67785234899327))
                .add(new Point2D.Double(467.5844806007509, 405.7046979865772))
                .add(new Point2D.Double(434.54317897371715, 411.74496644295306))
                .add(new Point2D.Double(400.5006257822278, 412.75167785234896))
                .add(new Point2D.Double(360.450563204005, 412.75167785234896))
                .add(new Point2D.Double(315.39424280350437, 411.74496644295306))
                .add(new Point2D.Double(274.34292866082603, 401.67785234899327))
                .add(new Point2D.Double(235.29411764705884, 390.6040268456376))
                .add(new Point2D.Double(210.26282853566957, 362.41610738255036))
                .add(new Point2D.Double(193.2415519399249, 332.2147651006712))
                .add(new Point2D.Double(179.22403003754695, 293.9597315436242))
                .add(new Point2D.Double(177.2215269086358, 247.6510067114094))
                .add(new Point2D.Double(201.25156445556945, 208.38926174496646))
                .add(new Point2D.Double(234.29286608260327, 187.248322147651))
                .add(new Point2D.Double(283.3541927409262, 172.1476510067114))
                .add(new Point2D.Double(323.4042553191489, 172.1476510067114))
                .add(new Point2D.Double(365.45682102628285, 176.1744966442953))
                .add(new Point2D.Double(414.51814768460576, 183.2214765100671))
                .add(new Point2D.Double(455.5694618272841, 193.28859060402684))
                .add(new Point2D.Double(473.5919899874844, 206.37583892617448))
                .add(new Point2D.Double(474.5932415519399, 223.48993288590606))
                .add(new Point2D.Double(459.57446808510645, 237.58389261744966))
                .add(new Point2D.Double(423.5294117647059, 241.61073825503354))
                .add(new Point2D.Double(386.4831038798498, 246.6442953020134))
                .add(new Point2D.Double(350.4380475594493, 251.6778523489933))
                .add(new Point2D.Double(316.39549436795994, 259.7315436241611))
                .add(new Point2D.Double(287.3591989987484, 277.8523489932886))
                .add(new Point2D.Double(286.35794743429284, 301.00671140939596))
                .add(new Point2D.Double(304.38047559449313, 318.12080536912754))
                .add(new Point2D.Double(340.4255319148936, 319.1275167785235))
                .add(new Point2D.Double(415.5193992490613, 322.1476510067114))
                .add(new Point2D.Double(474.5932415519399, 323.1543624161074))
                .add(new Point2D.Double(538.6733416770963, 327.1812080536913))
                .add(new Point2D.Double(565.7071339173967, 322.1476510067114))
                .add(new Point2D.Double(612.7659574468086, 313.0872483221477))
                .add(new Point2D.Double(667.8347934918648, 313.0872483221477))
                .add(new Point2D.Double(729.9123904881102, 311.0738255033557))
                .add(new Point2D.Double(786, 308.05369127516775))
                .build();

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
