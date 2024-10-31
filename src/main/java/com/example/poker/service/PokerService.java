package com.example.poker.service;

import com.example.poker.controller.PokerController;
import com.example.poker.request.dto.PokerHandRequest;
import com.example.poker.service.factory.PokerHandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PokerService {

    @Autowired
    private PokerHandFactory strategyFactory;

    private static final Logger logger = LoggerFactory.getLogger(PokerController.class);

    public boolean isPokerHand(String type, PokerHandRequest pokerHandRequest) {
        logger.debug("Validating requests");
        pokerHandRequest.validate();

        logger.info("Checking if hand is stright: "+pokerHandRequest.toString());
        return checkPokerHandLogic(type, pokerHandRequest);
    }

//    @Cacheable(value = "pokerHandCache", key = "#type + '_' + #request.ranks.toString() + '_' + #request.suits.toString()")
    @Cacheable(value = "pokerHandCache", key = "#type")
    private boolean checkPokerHandLogic(String type, PokerHandRequest pokerHandRequest) {

        logger.info("Cache key: " + type + "_" + pokerHandRequest.getRanks() + "_" + pokerHandRequest.getSuits());

        logger.info("Getting strategy and checking if the hand is straight: "+pokerHandRequest.toString());
        PokerHandStrategy strategy = strategyFactory.getStrategy(type);
        if(strategy != null){
            return strategy.isPokerHand(pokerHandRequest.getRanks(), pokerHandRequest.getSuits());
        }else{
            throw new IllegalArgumentException("Invalid poker hand strategy provided: "+type);
        }
    }
}
