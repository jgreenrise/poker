package com.example.poker.service;

import com.example.poker.request.dto.PokerHandRequest;
import com.example.poker.service.factory.PokerHandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PokerService {

    @Autowired
    private PokerHandFactory strategyFactory;

    public boolean isPokerHand(String type, PokerHandRequest pokerHandRequest) {
        pokerHandRequest.validate();
        return checkPokerHandLogic(type, pokerHandRequest);
    }

    @Cacheable(value = "pokerHandCache", key = "#type + '_' + #request.ranks.toString() + '_' + #request.suits.toString()", condition = "#request.isValid()")
    private boolean checkPokerHandLogic(String type, PokerHandRequest pokerHandRequest) {
        PokerHandStrategy strategy = strategyFactory.getStrategy(type);
        if(strategy != null){
            return strategy.isPokerHand(pokerHandRequest.getRanks(), pokerHandRequest.getSuits());
        }else{
            throw new IllegalArgumentException("Invalid poker hand strategy provided: "+type);
        }
    }
}
