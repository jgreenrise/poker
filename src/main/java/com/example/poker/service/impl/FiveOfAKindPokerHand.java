package com.example.poker.service.impl;

import com.example.poker.service.PokerHandStrategy;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FiveOfAKindPokerHand implements PokerHandStrategy {

    @Override
    public boolean isPokerHand(List<Integer> ranks, List<String> suits) {
        return isFiveOfAKind(ranks, suits);
    }

    public boolean isFiveOfAKind(List<Integer> ranks, List<String> suits) {
        // Implemenation missing- @Todo
        return false;
    }

}
