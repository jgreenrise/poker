package com.example.poker.request.dto;

import com.example.poker.constant.ErrorCode;
import com.example.poker.exception.PokerException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokerHandRequest {
    private List<Integer> ranks;
    private List<String> suits;

    public void validate(){
        if(ranks == null || suits == null){
            throw new PokerException(ErrorCode.NULL_LISTS);
        }

        int rankSize = ranks.size();
        int suitSize = suits.size();

        // Validate if ranks and suit size is either 5 or 7
        if((rankSize != 5 && rankSize !=7) || (suitSize != 5 && suitSize !=7)){
            throw new PokerException(ErrorCode.INVALID_HAND_SIZE);
        }

        if(rankSize != suitSize){
            throw new PokerException(ErrorCode.SIZE_MISMATCH);
        }

        // Validate Rank is within range 1 .. 13
        for (int rank :
                ranks) {
            if(rank < 1 || rank > 13){
                throw new PokerException(ErrorCode.INVALID_RANK);
            }
        }

        // Ensure valid suit is provided
        Set<String> validSuits = Set.of("hearts", "diamonds", "clubs", "spades");
        for (String suit :
                suits) {
            if(!validSuits.contains(suit)){
                throw new PokerException(ErrorCode.INVALID_SUIT);
            }
        }

        // Validate duplicate card is not added
        Set<String> set = new HashSet<>();
        for (int i = 0; i < ranks.size(); i++) {
            if(set.contains(ranks.get(i) + "-"+suits.get(i))){
                throw new PokerException(ErrorCode.DUPLICATE_CARD);
            }
            set.add(ranks.get(i) + "-"+suits.get(i));
        }
    }
}
