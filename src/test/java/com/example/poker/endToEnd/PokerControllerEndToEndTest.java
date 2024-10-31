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
            "'5,6,7,8,9', '[\"S\",\"H\",\"D\",\"C\",\"H\"]', true, true",
            "'2,3,4,5,6', '[\"C\",\"D\",\"H\",\"S\",\"S\"]', true, true",
            "'1,10,11,12,13', '[\"C\",\"D\",\"H\",\"S\",\"S\"]', true, true"
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
