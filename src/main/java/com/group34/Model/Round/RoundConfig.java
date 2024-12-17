package com.group34.Model.Round;

import java.util.ArrayList;
import java.util.List;

import com.group34.Model.Enemy.GargamelFactory;
import com.group34.Model.Shop.CashVault;

public class RoundConfig {
    public static List<Round> createRounds(CashVault cashVault) {
        List<Round> rounds = new ArrayList<>();

        // Round 1
        rounds.add(new RoundBuilder()
            .addEvent(new RoundEvent(new GargamelFactory(), 0))
            .addEvent(new RoundEvent(new GargamelFactory(), 40))
            .addEvent(new RoundEvent(new GargamelFactory(), 80))
            .build());
        
        // Round 2 etc
        rounds.add(new RoundBuilder()
            .addEvent(new RoundEvent(new GargamelFactory(), 0))
            .addEvent(new RoundEvent(new GargamelFactory(), 80))
            .addEvent(new RoundEvent(new GargamelFactory(), 160))
            .addEvent(new RoundEvent(new GargamelFactory(), 240))
            .build());

        return rounds;
    }
}

