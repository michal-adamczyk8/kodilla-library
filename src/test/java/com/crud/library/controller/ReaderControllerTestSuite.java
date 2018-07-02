package com.crud.library.controller;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import com.crud.library.service.ReaderService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@WebMvcTest(ReaderController.class)
public class ReaderControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ReaderService readerService;

    @Test
    public void shouldAddUser() throws Exception{
        //Given
        ReaderDto readerDto = new ReaderDto(1L, "Michał", "Adamczyk",
                "2017-01-01", new ArrayList<>());
        Reader reader = new Reader(1L, "Michał", "Adamczyk",
                LocalDate.of(2017, 01, 01), new ArrayList<>());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(readerDto);
        // /When & Then
        mockMvc.perform(post("/v1/library/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }
}
