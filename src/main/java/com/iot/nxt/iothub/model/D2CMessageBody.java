package com.iot.nxt.iothub.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonPropertyOrder({ "deviceId", "messageId", "temperature", "humidity" })
public class D2CMessageBody {

    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("messageId")
    private String messageId;
    @JsonProperty("temperature")
    private Double temperature;
    @JsonProperty("humidity")
    private Double humidity;

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return this.humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

}
