package com.example.poker.endToEnd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; // Correct import
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath; // Correct import
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PokerControllerEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @CsvSource({
            "'5,6,7,8,9', '[\"spades\",\"hearts\",\"diamonds\",\"clubs\",\"hearts\"]', true, true",
            "'2,3,4,5,6', '[\"clubs\",\"diamonds\",\"hearts\",\"spades\",\"spades\"]', true, true",
            "'10,11,12,13,1', '[\"clubs\",\"diamonds\",\"hearts\",\"spades\",\"spades\"]', true, true",
            "'1,10,11,12,13', '[\"clubs\",\"diamonds\",\"hearts\",\"spades\",\"spades\"]', true, true",
            "'1,10,11,12,13,4,5', '[\"clubs\",\"diamonds\",\"hearts\",\"spades\",\"spades\",\"spades\",\"spades\"]', true, true",
            "'1,10,11,12,13,2,3', '[\"clubs\",\"diamonds\",\"hearts\",\"spades\",\"spades\",\"spades\",\"spades\"]', true, true"
    })
    public void whenValidPokerHandRequest_thenReturnsSuccess(String ranks, String suits, boolean expectedSuccess, boolean expectedData) throws Exception {
        String content = String.format("{\"ranks\":[%s],\"suits\":%s}", ranks, suits);

        mockMvc.perform(post("/poker-service/v1/hands/straight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(expectedSuccess))
                .andExpect(jsonPath("$.data").value(expectedData));
    }

}
