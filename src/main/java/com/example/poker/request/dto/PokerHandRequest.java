package com.example.poker.request.dto;

import com.example.poker.constant.ErrorCode;
import com.example.poker.exception.PokerException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokerHandRequest {
    private List<Integer> ranks;
    private List<Character> suits;

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

        for (int rank :
                ranks) {
            if(rank < 0 || rank > 13){
                throw new PokerException(ErrorCode.INVALID_RANK);
            }
        }
    }
}
