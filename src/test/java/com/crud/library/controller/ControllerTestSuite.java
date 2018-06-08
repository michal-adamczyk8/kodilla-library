package com.crud.library.controller;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.service.BookService;
import com.crud.library.service.DbService;
import com.crud.library.service.LendingService;
import com.crud.library.service.ReaderService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(LibraryController.class)
public class ControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @MockBean
    LendingService lendingService;

    @MockBean
    ReaderService readerService;

    @MockBean
    DbService dbService;

    @MockBean
    ReaderMapper readerMapper;



    @Test
    public void shouldAddReader() throws Exception {
        //Given
        ReaderDto readerDto = new ReaderDto(1L, "Michał", "Adamczyk",
                new Date(2018, 1, 1), new ArrayList<>());
        Reader reader = new Reader(1L, "Michał", "Adamczyk",
                new Date(2018, 1, 1), new ArrayList<>());

        Gson gson = new Gson();
        String jsonContent = gson.toJson(readerDto);

        //When & Then
        mockMvc.perform(post("/v1/library/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}
