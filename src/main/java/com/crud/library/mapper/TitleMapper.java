package com.crud.library.mapper;

import com.crud.library.domain.Title;
import com.crud.library.domain.TitleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TitleMapper {
    @Autowired
    BookMapper bookMapper;

    public Title mapToTitle(TitleDto titleDto) {
        return new Title(
                titleDto.getTitleId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getYearOfPublish(),
                bookMapper.mapToListBook(titleDto.getBooks()));
    }
}
