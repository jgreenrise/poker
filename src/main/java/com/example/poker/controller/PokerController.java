package com.example.poker.controller;

import com.example.poker.exception.PokerException;
import com.example.poker.request.dto.PokerHandRequest;
import com.example.poker.response.ApiResponse;
import com.example.poker.service.PokerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poker-service/v1/hands")
@RequiredArgsConstructor
public class PokerController {

    private final PokerService pokerService;
    private static final Logger logger = LoggerFactory.getLogger(PokerController.class);

    @PostMapping("/straight")
    public ResponseEntity<ApiResponse<Boolean>> isPokerHand(@RequestBody PokerHandRequest request) {
        try {
            String type = "StraightPokerHand";
            boolean result = pokerService.isPokerHand(type, request);
            return ResponseEntity.ok(new ApiResponse<>(true, result, null, null));
        } catch (PokerException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, null, e.getErrorCode(), e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, null, "ERR000", e.getMessage()));
        }
    }

}
