package com.example.poker.service.impl;

import com.example.poker.service.PokerHandStrategy;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StraightPokerHand implements PokerHandStrategy {

    @Override
    public boolean isPokerHand(List<Integer> ranks, List<String> suits) {
        return isStraight(ranks, suits);
    }

    public boolean isStraight(List<Integer> ranks, List<String> suits) {

        int[] rankFreq = new int[14];   // 1 .. 13
        for (int k = 0; k < ranks.size(); k++) {
            if (!suits.get(k).equals("Joker")) {
                rankFreq[ranks.get(k)]++;
            }
        }
        return isStraight(rankFreq);
    }

    private boolean isStraight(int[] rankFreq) {

        int counter = 0;
        for (int j = 1; j < rankFreq.length; j++) {
            if (rankFreq[j] >= 1) {
                counter++;
                j++;
                while (j < rankFreq.length && rankFreq[j] >= 1) {
                    counter++;
                    j++;
                    if (counter == 5) {
                        // Check if we have 5 consecutive cards
                        return true;
                    } else if (counter == 4 && j == 14 && rankFreq[1] == 1) {
                        return true;
                    }
                }
            } else {
                // Reset if no consecutive card
                counter = 0;
            }
        }
        return false;
    }
}
