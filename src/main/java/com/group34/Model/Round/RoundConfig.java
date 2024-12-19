package com.group34.Model.Round;

import java.util.ArrayList;
import java.util.List;

import com.group34.Model.Enemy.AzraelFactory;
import com.group34.Model.Enemy.BalthazarFactory;
import com.group34.Model.Enemy.GargamelFactory;
import com.group34.Model.Enemy.HogathaFactory;
import com.group34.Model.Shop.CashVault;

public class RoundConfig {
    public static List<Round> createRounds(CashVault cashVault) {
        List<Round> rounds = new ArrayList<>();

        // Round 1
        rounds.add(new RoundBuilder()
            .addEvent(new RoundEvent(new GargamelFactory(), 0))
            .addEvent(new RoundEvent(new BalthazarFactory(), 40))
            .addEvent(new RoundEvent(new AzraelFactory(), 80))
            .addEvent(new RoundEvent(new HogathaFactory(), 80))
            .build());
        
        // Round 2 etc
        rounds.add(new RoundBuilder()
            .addEvent(new RoundEvent(new GargamelFactory(), 0))
            .addEvent(new RoundEvent(new BalthazarFactory(), 80))
            .addEvent(new RoundEvent(new AzraelFactory(), 160))
            .addEvent(new RoundEvent(new HogathaFactory(), 240))
            .addEvent(new RoundEvent(new GargamelFactory(), 250))
            .addEvent(new RoundEvent(new GargamelFactory(), 260))
            .addEvent(new RoundEvent(new BalthazarFactory(), 270))
            .addEvent(new RoundEvent(new AzraelFactory(), 280))
            .addEvent(new RoundEvent(new HogathaFactory(), 290))
            .addEvent(new RoundEvent(new GargamelFactory(), 300))
            .addEvent(new RoundEvent(new HogathaFactory(), 310))
            .addEvent(new RoundEvent(new GargamelFactory(), 320))
            .addEvent(new RoundEvent(new GargamelFactory(), 330))
            .addEvent(new RoundEvent(new BalthazarFactory(), 340))
            .addEvent(new RoundEvent(new AzraelFactory(), 350))
            .addEvent(new RoundEvent(new HogathaFactory(), 360))
            .addEvent(new RoundEvent(new GargamelFactory(), 370))
            
            .build());

        return rounds;
    }
}

