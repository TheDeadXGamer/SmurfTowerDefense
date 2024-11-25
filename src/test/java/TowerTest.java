import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.awt.geom.Point2D;
import com.group34.Model.Enemy.EnemyGargamel;
import com.group34.Model.Tower.ThunderSmurf;
import com.group34.Model.Tower.Tower;

public class TowerTest {
    private Tower tower;
    private EnemyGargamel enemy;

    @Before
    public void setUp() {
        tower = new ThunderSmurf(new Point2D.Double(0, 0));
        enemy = new EnemyGargamel();
    }

    @Test
    public void testGetAttackSpeed() {
        assertEquals(1, tower.getAttackSpeed());
    }

    @Test
    public void testGetDamage() {
        assertEquals(10, tower.getDamage());
    }

    @Test
    public void testGetCost() {
        assertEquals(100, tower.getCost());
    }

    @Test
    public void testAttack() {
        tower.attack(enemy);
        assertEquals(40, enemy.getHealth());
    }

    @Test
    public void testGetPosition() {
        assertEquals(new Point2D.Double(0, 0), tower.getPosition());
    }
}