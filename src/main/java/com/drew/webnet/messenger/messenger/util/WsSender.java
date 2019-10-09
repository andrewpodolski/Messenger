package com.drew.webnet.messenger.messenger.util;

import com.drew.webnet.messenger.messenger.dto.EventType;
import com.drew.webnet.messenger.messenger.dto.ObjectType;
import com.drew.webnet.messenger.messenger.dto.WsEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Component
public class WsSender {
    private SimpMessagingTemplate template;
    private ObjectMapper mapper;

    @Autowired
    public WsSender(SimpMessagingTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public <T> BiConsumer<EventType, T> getSender(ObjectType objectType, Class view) {
        ObjectWriter objectWriter = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(view);

        return (EventType eventType, T payload) -> {
            String value = null;
            try {
                value = objectWriter.writeValueAsString(payload);
            } catch (JsonProcessingException e) {
                throw new RuntimeException();
            }
            template.convertAndSend(
                    "/topic/activity",
                    new WsEventDto(objectType, eventType, value
                    ));
        };

    }
}
