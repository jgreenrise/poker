package com.example.poker.service;

import java.util.List;

public interface PokerHandStrategy {
    boolean isPokerHand(List<Integer> ranks, List<Character> suits);
}
