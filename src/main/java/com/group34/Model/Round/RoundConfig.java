package com.group34.Model.Round;

import com.group34.Model.Enemy.GargamelFactory;
import com.group34.Model.Cash.CashVault;
import java.util.ArrayList;
import java.util.List;

public class RoundConfig {
    public static List<Round> createRounds(CashVault cashVault) {
        List<Round> rounds = new ArrayList<>();

        // Round 1
        rounds.add(new RoundBuilder()
            .addEvent(new RoundEvent(new GargamelFactory(cashVault), 0))
            .addEvent(new RoundEvent(new GargamelFactory(cashVault), 40))
            .addEvent(new RoundEvent(new GargamelFactory(cashVault), 80))
            .build());
        
        // Round 2 etc
        rounds.add(new RoundBuilder()
            .addEvent(new RoundEvent(new GargamelFactory(cashVault), 0))
            .addEvent(new RoundEvent(new GargamelFactory(cashVault), 80))
            .addEvent(new RoundEvent(new GargamelFactory(cashVault), 160))
            .addEvent(new RoundEvent(new GargamelFactory(cashVault), 240))
            .build());

        return rounds;
    }
}

