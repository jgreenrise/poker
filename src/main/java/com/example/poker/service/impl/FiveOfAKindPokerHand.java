package com.example.poker.service.impl;

import com.example.poker.service.PokerHandStrategy;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FiveOfAKindPokerHand implements PokerHandStrategy {

    @Override
    public boolean isPokerHand(List<Integer> ranks, List<Character> suits) {
        return isFiveOfAKind(ranks, suits);
    }

    public boolean isFiveOfAKind(List<Integer> ranks, List<Character> suits) {

        int[] rankFreq = new int[14];   // 1..13
        int maxRankCount = 0;
        boolean containsJoker = false;

        for (int k = 0; k < ranks.size(); k++) {
            if (suits.get(k) != 'J') {
                rankFreq[suits.get(k)]++;
                maxRankCount = Integer.max(maxRankCount, rankFreq[suits.get(k)]);
            }else{
                containsJoker = true;
            }

            if(containsJoker && maxRankCount == 4){
                return true;
            }
        }

        return false;
    }

}
