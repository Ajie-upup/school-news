package com.news.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.news.utils.common.IdsUtils;


import java.io.IOException;

public class ConfusionSerializer extends JsonSerializer<Object> {

    public  void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        try {
            if (value != null) {
                jsonGenerator.writeString(IdsUtils.encryptNumber(Long.valueOf(value.toString())));
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        serializers.defaultSerializeValue(value, jsonGenerator);
    }
}
