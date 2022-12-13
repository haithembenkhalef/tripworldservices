package com.tripworld.utility;

import com.tripworld.hotels.Hotel;
import com.tripworld.pojo.RoomPojo;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import static org.springframework.batch.item.file.transform.DelimitedLineTokenizer.DELIMITER_COMMA;

public class Utility {

    public static LineMapper<Hotel> getLineMapperHotel() {
        DefaultLineMapper<Hotel> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(DELIMITER_COMMA);
        tokenizer.setNames("hotelName", "description", "cityCode");
        BeanWrapperFieldSetMapper<Hotel> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Hotel.class);
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    public static LineMapper<RoomPojo> getLineMapperRoom() {
        DefaultLineMapper<RoomPojo> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(DELIMITER_COMMA);
        tokenizer.setNames("description", "hotelId");
        BeanWrapperFieldSetMapper<RoomPojo> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(RoomPojo.class);
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
}
