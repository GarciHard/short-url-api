package com.garcihard.shortcode.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garcihard.shortcode.exception.ShortUrlExceptionHandler;
import com.garcihard.shortcode.model.dto.ShortUrlRequestDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShortUrlController.class)
@Import(ShortUrlExceptionHandler.class)
class ShortUrlControllerTest {
    private final static String END_POINT = "/api/v1/urls";
    private final static String EMPTY_STRING = "";
    private final static String NULL_STRING = null;

    private final static String URI_CANNOT_BE_EMPTY = "URI cannot be empty.";
    private final static String INVALID_URI_FORMAT = "Invalid URI Format.";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnBadRequest_whenEmptyUrlProvided() throws Exception {
        ShortUrlRequestDTO requestDTO = new ShortUrlRequestDTO(EMPTY_STRING);
        String requestBody = objectMapper.writeValueAsString(requestDTO);

        mockMvc.perform(post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertInstanceOf(
                        MethodArgumentNotValidException.class,
                        result.getResolvedException()
                ))
                .andExpect(
                        jsonPath("$.fieldErrors[*].message",
                        Matchers.containsInAnyOrder(URI_CANNOT_BE_EMPTY, INVALID_URI_FORMAT))
                );
    }

    @Test
    public void shouldReturnBadRequest_whenNullUrlProvided() throws Exception {
        ShortUrlRequestDTO requestDTO = new ShortUrlRequestDTO(NULL_STRING);
        String requestBody = objectMapper.writeValueAsString(requestDTO);

        mockMvc.perform(post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(result -> Assertions.assertInstanceOf(
                        MethodArgumentNotValidException.class,
                        result.getResolvedException())
                )
                .andExpect(
                        jsonPath("$.fieldErrors[0].message").value(URI_CANNOT_BE_EMPTY)
                );
    }

}