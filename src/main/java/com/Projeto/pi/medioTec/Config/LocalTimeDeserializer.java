package com.Projeto.pi.medioTec.Config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalTime;

public class LocalTimeDeserializer extends StdDeserializer<LocalTime> {

    public LocalTimeDeserializer() {
        super(LocalTime.class);
    }

    @Override
    public LocalTime deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);

        int hour = node.get("hour").asInt();
        int minute = node.get("minute").asInt();
        int second = node.has("second") ? node.get("second").asInt() : 0;
        int nano = node.has("nano") ? node.get("nano").asInt() : 0;

        return LocalTime.of(hour, minute, second, nano);
    }
}
