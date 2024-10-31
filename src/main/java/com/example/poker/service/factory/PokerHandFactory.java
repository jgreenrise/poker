package com.example.poker.service.factory;

import com.example.poker.service.PokerHandStrategy;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class PokerHandFactory {

    private final Map<String, PokerHandStrategy> strategies;

    public PokerHandFactory(List<PokerHandStrategy> strategyList) {
        this.strategies =
                strategyList
                        .stream()
                        .collect(Collectors.toMap(strategy -> strategy.getClass().getSimpleName(), strategy -> strategy));
    }

    public PokerHandStrategy getStrategy(String type) {
        return strategies.get(type);
    }
}
