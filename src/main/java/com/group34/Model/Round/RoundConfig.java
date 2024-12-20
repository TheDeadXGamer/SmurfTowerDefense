package com.group34.Model.Round;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.group34.Model.Enemy.*;
import com.group34.Model.Shop.CashVault;

public class RoundConfig {

    private static int totalRoundValue = 3;
    private static ArrayList<EnemyFactory> factories = new ArrayList<>();


    static {
        factories.add(new GargamelFactory());
        factories.add(new BalthazarFactory());
        factories.add(new AzraelFactory());
        factories.add(new HogathaFactory());
    }
    public static Round createRound() {
        return createRandomizedRound();
    }
    private static Round createRandomizedRound() {
        Random random = new Random();
        int waitForSpawn = 0;
        int thisRoundValue = totalRoundValue;

        RoundBuilder builder = new RoundBuilder();

        ArrayList<EnemyFactory> copyOfFactories = (ArrayList<EnemyFactory>) factories.clone();


        int bound = factories.size()-1;
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

