package com.pb.abaieva.hw12;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    private static final long serialVersionUID = 1L;

    public LocalDateTimeDeserializer(){
        super(LocalDateTime.class);
    }

    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        return LocalDateTime.parse(jp.readValueAs(String.class), DateTimeFormatter.ofPattern("HH:mm:ss dd:MM:yyyy"));

    }

}
