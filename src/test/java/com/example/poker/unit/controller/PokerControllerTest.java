package com.example.poker.unit.controller;

import com.example.poker.constant.ErrorCode;
import com.example.poker.controller.PokerController;
import com.example.poker.exception.PokerException;
import com.example.poker.request.dto.PokerHandRequest;
import com.example.poker.response.ApiResponse;
import com.example.poker.service.PokerService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PokerControllerTest {
    @InjectMocks
    private PokerController pokerController;

    @Mock
    private PokerService pokerService;

    private PokerHandRequest request;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new PokerHandRequest();
        request.setRanks(List.of(5, 6, 7, 8, 9));
        request.setSuits(List.of("spades", "hearts", "diamonds", "clubs", "hearts"));
    }

    @Test
    public void givenValidPokerHandRequest_whenIsPokerHandCalled_thenReturnsSuccess() {
        // Given
        when(pokerService.isPokerHand("StraightPokerHand", request)).thenReturn(true);

        // When
        ResponseEntity<ApiResponse<Boolean>> response = pokerController.isPokerHand(request);

        // Then
        ApiResponse<Boolean> expectedResponse = new ApiResponse<>(true, true, null, null);
        assertEquals(ResponseEntity.ok(expectedResponse), response);
    }

    @Test
    public void givenPokerException_whenIsPokerHandCalled_thenReturnsErrorResponse() {
        // Given
        when(pokerService.isPokerHand("StraightPokerHand", request)).thenThrow(new PokerException(ErrorCode.SIZE_MISMATCH));

        // When
        ResponseEntity<ApiResponse<Boolean>> response = pokerController.isPokerHand(request);

        // Then
        ApiResponse<Boolean> expectedResponse = new ApiResponse<>(false, null,
                ErrorCode.SIZE_MISMATCH.getCode(), ErrorCode.SIZE_MISMATCH.getMessage());
        assertEquals(ResponseEntity.badRequest().body(expectedResponse), response);
    }

    @Test
    public void givenIllegalArgumentException_whenIsPokerHandCalled_thenReturnsErrorResponse() {
        // Given
        when(pokerService.isPokerHand("StraightPokerHand", request)).thenThrow(new IllegalArgumentException("Invalid request"));

        // When
        ResponseEntity<ApiResponse<Boolean>> response = pokerController.isPokerHand(request);

        // Then
        ApiResponse<Boolean> expectedResponse = new ApiResponse<>(false, null, "ERR000", "Invalid request");
        assertEquals(ResponseEntity.badRequest().body(expectedResponse), response);
    }
}
