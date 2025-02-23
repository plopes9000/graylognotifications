package com.itchannel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.graylog2.contentpacks.EntityDescriptorIds;
import com.google.auto.value.AutoValue;
import org.graylog.events.notifications.EventNotificationConfig;
import org.graylog.events.event.EventDto;
import org.graylog2.plugin.rest.ValidationResult;
import org.graylog.events.notifications.EventNotificationExecutionJob;
import org.graylog.scheduler.JobTriggerData;
import org.graylog2.contentpacks.model.entities.references.ValueReference;
import javax.validation.constraints.NotBlank;
import com.itchannel.entities.PushEventNotificationConfigEntity;
import org.graylog.events.contentpack.entities.EventNotificationConfigEntity;

@AutoValue
@JsonTypeName(PushNotificationConfig.TYPE_NAME)
@JsonDeserialize(builder = PushNotificationConfig.Builder.class)
public abstract class PushNotificationConfig implements EventNotificationConfig {
    public static final String TYPE_NAME = "push-notifications-v2";
    private static final String FIELD_USER_TOKEN = "user_token";
    private static final String FIELD_API_TOKEN = "api_token";
    private static final String FIELD_PRIORITY_TOKEN = "priority_token";
    private static final String FIELD_RETRY_TOKEN = "retry_token";
    private static final String FIELD_EXPIRE_TOKEN = "expire_token";
    private static final String FIELD_SOUND_TOKEN = "sound_token";
    private static final String FIELD_MESSAGE = "message_field";

    @JsonProperty(FIELD_USER_TOKEN)
    @NotBlank
    public abstract String userToken();

    @JsonProperty(FIELD_API_TOKEN)
    @NotBlank
    public abstract String apiToken();

    @JsonProperty(FIELD_PRIORITY_TOKEN)
    @NotBlank
    public abstract String priorityToken();

    @JsonProperty(FIELD_RETRY_TOKEN)
    @NotBlank
    public abstract String retryToken();

    @JsonProperty(FIELD_EXPIRE_TOKEN)
    @NotBlank
    public abstract String expireToken();

    @JsonProperty(FIELD_SOUND_TOKEN)
    @NotBlank
    public abstract String soundToken();

    @JsonProperty(FIELD_MESSAGE)
    @NotBlank
    public abstract String messageField();




    @Override @JsonIgnore
    public JobTriggerData toJobTriggerData(EventDto dto) {
        return EventNotificationExecutionJob.Data.builder().eventDto(dto).build();
    }

    public static Builder builder() {
        return Builder.create();
    }

    @Override @JsonIgnore
    public ValidationResult validate() {
        final ValidationResult validation = new ValidationResult();
        if (userToken().isEmpty()) {
            validation.addError(FIELD_USER_TOKEN, "User/Group Token cannot be empty.");
        }
        if (apiToken().isEmpty())
        {
            validation.addError(FIELD_API_TOKEN, "APP Token cannot be empty.");
        }
        if (priorityToken().isEmpty())
        {
            validation.addError(FIELD_PRIORITY_TOKEN, "Please Specify a priority Value");
        }
        if (messageField().isEmpty())
        {
            validation.addError(FIELD_PRIORITY_TOKEN, "Please Specify the message template");
        }


        return validation;
    }


    @AutoValue.Builder
    public static abstract class Builder implements EventNotificationConfig.Builder<Builder> {
        @JsonCreator
        public static Builder create() {
            return new AutoValue_PushNotificationConfig.Builder()
                    .type(TYPE_NAME);
        }
        @JsonProperty(FIELD_USER_TOKEN)
        public abstract Builder userToken(String userToken);

        @JsonProperty(FIELD_API_TOKEN)
        public abstract Builder apiToken(String apiToken);

        @JsonProperty(FIELD_PRIORITY_TOKEN)
        public abstract Builder priorityToken(String priorityToken);

        @JsonProperty(FIELD_RETRY_TOKEN)
        public abstract Builder retryToken(String retryToken);

        @JsonProperty(FIELD_EXPIRE_TOKEN)
        public abstract Builder expireToken(String expireToken);

        @JsonProperty(FIELD_SOUND_TOKEN)
        public abstract Builder soundToken(String soundToken);

        @JsonProperty(FIELD_MESSAGE)
        public abstract Builder messageField(String messageField);

        public abstract PushNotificationConfig build();
    }

    @Override
    public EventNotificationConfigEntity toContentPackEntity(EntityDescriptorIds entityDescriptorIds) {
        return PushEventNotificationConfigEntity.builder()
                .userToken(ValueReference.of(userToken()))
                .apiToken(ValueReference.of(apiToken()))
                .priorityToken(ValueReference.of(priorityToken()))
                .retryToken(ValueReference.of(retryToken()))
                .expireToken(ValueReference.of(expireToken()))
                .soundToken(ValueReference.of(soundToken()))
                .messageField(ValueReference.of(messageField()))
                .build();
    }

}
