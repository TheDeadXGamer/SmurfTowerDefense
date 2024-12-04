import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.group34.Model.Player.Player;
import com.group34.Model.Tower.LightningSmurf;
import com.group34.Model.Tower.Tower;
import com.group34.Model.Enemy.EnemyGargamel;
import java.awt.geom.Point2D;

public class IntegrationTest {
    private Player player;
    private Tower tower;
    private EnemyGargamel enemy;

    @Before
    public void setUp() {
        player = new Player(100, 500);
        tower = new LightningSmurf(new Point2D.Double(0, 0));
        enemy = new EnemyGargamel();
        player.addTower(tower);
    }

    @Test
    public void testPlayerTowerInteraction() {
        assertEquals(1, player.getTowers().size());
        player.getTowers().get(0).attack(enemy);
        assertEquals(198, enemy.getHealth());
    }

    @Test
    public void testGameFlow() {
        player.setHealth(80);
        player.setMoney(300);
        assertEquals(80, player.getHealth());
        assertEquals(300, player.getMoney());
        player.getTowers().get(0).attack(enemy);
        assertEquals(198, enemy.getHealth());
    }
}