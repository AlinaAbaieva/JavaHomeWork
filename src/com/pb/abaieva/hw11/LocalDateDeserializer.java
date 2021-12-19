package com.pb.abaieva.hw11;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static final long serialVersionUID = 1L;

    public LocalDateDeserializer(){
        super(LocalDate.class);
    }

    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

        return LocalDate.parse(jp.readValueAs(String.class), DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }

}