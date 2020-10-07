package com.itchannel.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.itchannel.PushNotificationConfig;
import org.graylog.events.contentpack.entities.EventNotificationConfigEntity;
import org.graylog.events.notifications.EventNotificationConfig;
import org.graylog2.contentpacks.model.entities.EntityDescriptor;
import org.graylog2.contentpacks.model.entities.references.ValueReference;


import javax.validation.constraints.NotBlank;
import java.util.Map;

@AutoValue
@JsonTypeName(PushNotificationConfigEntity.TYPE_NAME)
@JsonDeserialize(builder = PushNotificationConfigEntity.Builder.class)
public abstract class PushNotificationConfigEntity implements EventNotificationConfigEntity {

    public static final String TYPE_NAME = "pushnotification-v2";
    private static final String FIELD_USER_TOKEN = "user_token";
    private static final String FIELD_API_TOKEN = "api_token";
    private static final String FIELD_PRIORITY_TOKEN = "priority_token";
    private static final String FIELD_MESSAGE = "message_field";

    @JsonProperty(FIELD_USER_TOKEN)
    @NotBlank
    public abstract ValueReference userToken();

    @JsonProperty(FIELD_API_TOKEN)
    @NotBlank
    public abstract ValueReference apiToken();

    @JsonProperty(FIELD_PRIORITY_TOKEN)
    @NotBlank
    public abstract ValueReference priorityToken();

    @JsonProperty(FIELD_MESSAGE)
    @NotBlank
    public abstract ValueReference messageField();


    public static Builder builder() {
        return Builder.create();
    }

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public static abstract class Builder implements EventNotificationConfigEntity.Builder<Builder>{

        @JsonCreator
        public static Builder create() {
            return new AutoValue_PushNotificationConfigEntity.Builder()
                    .type(TYPE_NAME);
        }

        @JsonProperty(FIELD_USER_TOKEN)
        public abstract Builder userToken(ValueReference userToken);

        @JsonProperty(FIELD_API_TOKEN)
        public abstract Builder apiToken(ValueReference apiToken);

        @JsonProperty(FIELD_PRIORITY_TOKEN)
        public abstract Builder priorityToken(ValueReference priorityToken);

        @JsonProperty(FIELD_MESSAGE)
        public abstract Builder messageField(ValueReference messageField);
        public abstract PushNotificationConfigEntity build();


    }


    @Override
    public EventNotificationConfig toNativeEntity(Map<String, ValueReference> parameters, Map<EntityDescriptor, Object> nativeEntities) {
        return PushNotificationConfig.builder()
                .userToken(userToken().asString(parameters))
                .apiToken(apiToken().asString(parameters))
                .priorityToken(priorityToken().asString(parameters))
                .messageField(messageField().asString(parameters))
                .build();

    }

}
