package com.group34.Model.Round;

import java.util.ArrayList;

import java.util.Random;

import com.group34.Model.Enemy.*;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import com.group34.Model.Enemy.*;

/**
 * RoundConfig class is responsible for creating a round with random enemies.
 */
public class RoundConfig {

    private static int totalRoundValue = 3;
    private static ArrayList<EnemyFactory> factories = new ArrayList<>();


    // Add all enemy factories to the list
    static {
        factories.add(new GargamelFactory());
        factories.add(new BalthazarFactory());
        factories.add(new AzraelFactory());
        factories.add(new HogathaFactory());
    }

    /**
     * Create a round with random enemies.
     * @return Round
     */
    public static Round createRound() {
        return createRandomizedRound();
    }

    /**
     * Create a round with random enemies. Is called upon by createRound method.
     * @return Round
     */
    private static Round createRandomizedRound() {
        Random random = new Random();
        int waitForSpawn = 0;
        int thisRoundValue = totalRoundValue;

        RoundBuilder builder = new RoundBuilder();

        ArrayList<EnemyFactory> copyOfFactories = (ArrayList<EnemyFactory>) factories.clone();


        int bound = factories.size()-1;

        // Randomly select enemies to spawn
        while (thisRoundValue > 0) {
            int randomNum = random.nextInt(bound);
            if (thisRoundValue < copyOfFactories.get(randomNum).getSpawnValue()) {
                copyOfFactories.remove(randomNum);
                bound -= 1;
            }

            builder.addEvent(new RoundEvent(copyOfFactories.get(randomNum),waitForSpawn));
            thisRoundValue -= copyOfFactories.get(randomNum).getSpawnValue();
            waitForSpawn += random.nextInt(15,100);
        }
        totalRoundValue += 3;
        return builder.build();
    }
}

