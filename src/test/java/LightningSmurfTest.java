import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.awt.geom.Point2D;
import com.group34.Model.Enemy.EnemyGargamel;
import com.group34.Model.Tower.LightningSmurf;
import com.group34.Model.Tower.ThunderSmurf;
import com.group34.Model.Tower.Tower;

public class LightningSmurfTest {
    private Tower tower;
    private EnemyGargamel enemy;

    @Before
    public void setUp() {
        tower = new LightningSmurf(new Point2D.Double(0, 0));
        enemy = new EnemyGargamel();
    }

    @Test
    public void testGetAttackSpeed() {
        assertEquals(1, tower.getAttackSpeed());
    }

    @Test
    public void testGetDamage() {
        assertEquals(2, tower.getDamage());
    }

    @Test
    public void testGetCost() {
        assertEquals(500, tower.getCost());
    }

    @Test
    public void testAttack() {
        tower.attack(enemy);
        assertEquals(198, enemy.getHealth());
    }

    @Test
    public void testGetPosition() {
        assertEquals(new Point2D.Double(0, 0), tower.getPosition());
    }

    @Test
    public void testUpgrade() {
        tower = ((LightningSmurf) tower).upgrade();
        assertTrue(tower instanceof ThunderSmurf);
        assertEquals(1, tower.getAttackSpeed());
        assertEquals(3, tower.getDamage());
        assertEquals(1000, tower.getCost());
    }

    @Test
    public void testEnemyDies() {
        enemy.setHealth(2);;
        tower.attack(enemy);
        assertFalse(enemy.isAlive());
    }
}