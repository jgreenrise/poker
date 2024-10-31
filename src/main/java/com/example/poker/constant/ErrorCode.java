package com.example.poker.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_HAND_SIZE("ERR001", "Both ranks and suits must be of size 5 or 7"),
    NULL_LISTS("ERR002", "Ranks and suits cannot be null. "),
    SIZE_MISMATCH("ERR003", "Ranks and suits must of be the same size"),
    INVALID_RANK("ERR004", "Invalid value found in ranks. Valid values range: 0 to 13");

    private final String code;
    private final String message;

}
