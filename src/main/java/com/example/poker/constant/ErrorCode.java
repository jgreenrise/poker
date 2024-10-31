package com.example.poker.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_HAND_SIZE("ERR001", "Both ranks and suits must be of size 5 or 7"),
    NULL_LISTS("ERR002", "Ranks and suits cannot be null. "),
    SIZE_MISMATCH("ERR003", "Ranks and suits must of be the same size"),
    INVALID_RANK("ERR004", "Invalid rank value. Valid range: 1 (A) to 13 (K). Ranks are represented as follows:\n" +
            "- **10** for \"10\"\n" +
            "- **11** for \"J\"\n" +
            "- **12** for \"Q\"\n" +
            "- **13** for \"K\"\n" +
            "- **1** for \"A\""),
    INVALID_SUIT("ERR005", "Invalid suit provided. Valid suit values: hearts, diamonds, clubs, spades"),
    DUPLICATE_CARD("ERR006", "Duplicate cards found. Try again");

    private final String code;
    private final String message;

}
