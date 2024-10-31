package com.example.poker.exception;

import com.example.poker.constant.ErrorCode;
import lombok.Getter;

@Getter
public class PokerException extends RuntimeException{

    private final String errorCode;

    public PokerException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
    }

}
