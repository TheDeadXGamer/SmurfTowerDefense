public class Gargamel extends BaseEnemy {
    public Gargamel() {
        super(
            "Gargamel",  // name
            200,         // health
            10,          // speed
            50,          // reward
            100          // scoreValue
        );
    }
    
    @Override
    public void move() {
        // Gargamel-specific movement implementation
    }