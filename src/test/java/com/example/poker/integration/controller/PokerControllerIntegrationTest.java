package com.example.poker.integration.controller;

import com.example.poker.constant.ErrorCode;
import com.example.poker.controller.PokerController;
import com.example.poker.exception.PokerException;
import com.example.poker.service.PokerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(PokerController.class)
public class PokerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokerService pokerService;

    @Test
    public void whenValidPokerHandRequest_thenReturnsSuccess() throws Exception {
        when(pokerService.isPokerHand(eq("StraightPokerHand"), any())).thenReturn(true);

        mockMvc.perform(post("/poker-service/v1/hands/straight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ranks\":[5,6,7,8,9],\"suits\":[\"S\",\"H\",\"D\",\"C\",\"H\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    public void whenSizeMisMatch_thenReturnsErrorResponse() throws Exception {
        when(pokerService.isPokerHand(eq("StraightPokerHand"), any()))
                .thenThrow(new PokerException(ErrorCode.SIZE_MISMATCH));

        mockMvc.perform(post("/poker-service/v1/hands/straight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ranks\":[5,6,7,8,9,10,11],\"suits\":[\"S\",\"H\",\"D\",\"C\",\"H\"]}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.SIZE_MISMATCH.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.SIZE_MISMATCH.getMessage()));
    }

    @Test
    public void whenRanksEmpty_thenReturnsErrorResponse() throws Exception {
        when(pokerService.isPokerHand(eq("StraightPokerHand"), any()))
                .thenThrow(new PokerException(ErrorCode.NULL_LISTS));

        mockMvc.perform(post("/poker-service/v1/hands/straight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"suits\":[\"S\",\"H\",\"D\",\"C\",\"H\"]}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.NULL_LISTS.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.NULL_LISTS.getMessage()));
    }

    @Test
    public void whenSuitsEmpty_thenReturnsErrorResponse() throws Exception {
        when(pokerService.isPokerHand(eq("StraightPokerHand"), any()))
                .thenThrow(new PokerException(ErrorCode.NULL_LISTS));

        mockMvc.perform(post("/poker-service/v1/hands/straight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ranks\":[5,6,7,8,9]}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.NULL_LISTS.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.NULL_LISTS.getMessage()));
    }

    @Test
    public void whenInvalidHandSize_thenReturnsErrorResponse() throws Exception {
        when(pokerService.isPokerHand(eq("StraightPokerHand"), any()))
                .thenThrow(new PokerException(ErrorCode.INVALID_HAND_SIZE));

        mockMvc.perform(post("/poker-service/v1/hands/straight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ranks\":[5,6,7,8,9,10,11],\"suits\":[\"S\",\"H\",\"D\",\"C\",\"H\"]}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value(ErrorCode.INVALID_HAND_SIZE.getCode()))
                .andExpect(jsonPath("$.message").value(ErrorCode.INVALID_HAND_SIZE.getMessage()));
    }

    @Test
    public void whenIllegalArgumentExceptionThrown_thenReturnsErrorResponse() throws Exception {
        when(pokerService.isPokerHand(eq("StraightPokerHand"), any()))
                .thenThrow(new IllegalArgumentException("Invalid request"));

        mockMvc.perform(post("/poker-service/v1/hands/straight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ranks\":[5,6,7,8,9],\"suits\":[\"S\",\"H\",\"D\",\"C\",\"H\"]}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").value("ERR000"))
                .andExpect(jsonPath("$.message").value("Invalid request"));
    }

    @Test
    public void whenInvalidJsonInput_thenReturnsBadRequest() throws Exception {
        when(pokerService.isPokerHand(eq("StraightPokerHand"), any()))
                .thenThrow(new IllegalArgumentException("Invalid request"));

        mockMvc.perform(post("/poker-service/v1/hands/straight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"invalidField\":\"value\"}"))
                .andExpect(status().isBadRequest());
    }

}
