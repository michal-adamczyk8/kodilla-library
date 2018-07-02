package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.BookStatus;
import com.crud.library.service.LendingService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@WebMvcTest(LendingController.class)
public class LendingControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    LendingService lendingService;

    @Test
    public void shouldLendBook() throws Exception {
        //Given
        BookDto bookDto = new BookDto(1l, 1L, BookStatus.AVAILABLE);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookDto);

        //When & Then
        mockMvc.perform(put("/v1/library/lending")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(jsonContent))
            .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBook() throws Exception {
        //Given
        BookDto bookDto = new BookDto(1L, 1L, BookStatus.RENTED);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(bookDto);

        //When & Then
        mockMvc.perform(put("/v1/library/lendingBack")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(jsonContent))
            .andExpect(status().isOk());
    }

    @Test
    public void shouldChangeStatus() throws Exception {
        //Given
        BookDto bookDto = new BookDto(1L, 1L, BookStatus.AVAILABLE);
        Gson gson = new Gson();
        String jsonContent2 = gson.toJson(BookStatus.AVAILABLE);
        String jsonContent1 = gson.toJson(1l);
        when(lendingService.changingStatus(anyLong(), any())).thenReturn(bookDto);































































































































































































































































































































































































































































































        //When & Then
        mockMvc.perform(put("/v1/library/status")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent1)
                .content(jsonContent2))
                .andExpect(status().isOk());
    }
}
