import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import com.group34.Model.Player.Player;
import com.group34.Model.Tower.LightningSmurf;
import com.group34.Model.Tower.Tower;
import java.awt.geom.Point2D;

public class PlayerTest {
    private Player player;
    private Tower tower;

    @Before
    public void setUp() {
        player = new Player(100, 500);
        tower = new LightningSmurf(new Point2D.Double(0, 0));
    }

    @Test
    public void testSetHealth() {
        player.setHealth(80);
        assertEquals(80, player.getHealth());
    }

    @Test
    public void testSetMoney() {
        player.setMoney(300);
        assertEquals(300, player.getMoney());
    }

    @Test
    public void testAddTower() {
        player.addTower(tower);
        List<Tower> towers = player.getTowers();
        assertEquals(1, towers.size());
        assertEquals(tower, towers.get(0));
    }

    @Test
    public void testRemoveTower() {
        player.addTower(tower);
        player.removeTower(tower);
        List<Tower> towers = player.getTowers();
        assertEquals(0, towers.size());
    }
}