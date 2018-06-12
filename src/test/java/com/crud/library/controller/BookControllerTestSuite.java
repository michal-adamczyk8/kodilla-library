package com.crud.library.controller;

import com.crud.library.domain.BookDto;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.TitleDto;
import com.crud.library.service.BookService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    public void shouldAddTitle() throws Exception {
        //Given
        TitleDto titleDto = new TitleDto(1L, "Potop", "Henryk Sienkiewicz", 1886,
                new ArrayList<>());
        Gson gson = new Gson();
        String jsonContent = gson.toJson(titleDto);

        //When & Then
        mockMvc.perform(post("/v1/library/addTitle")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddBook() throws Exception {
        //Given
        BookDto bookDto = new BookDto(1l, 1l, BookStatus.AVAILABLE);

        Gson gson = new Gson();
        String jsonContet = gson.toJson(bookDto);

        //When & Then
        mockMvc.perform(post("/v1/library/addBook")
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding("UTF-8")
        .content(jsonContet))
        .andExpect(status().isOk());
    }

    @Test
    public void shouldGetBookCount() throws Exception {
        //Given
        when(bookService.gettingBookCount(anyLong())).thenReturn(3L);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(1L);
        //When & Then
        mockMvc.perform(get("/v1/library/getBookCount?titleId=1")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(jsonContent))
            .andExpect((ResultMatcher) jsonPath("$", 3L))
            .andExpect(status().isOk());



    }

}
