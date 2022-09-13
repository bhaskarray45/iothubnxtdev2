package com.iot.nxt.iothub.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.nxt.iothub.constants.ApplicationConstants;
import com.iot.nxt.iothub.model.D2CMessageBody;
import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.Message;
import com.microsoft.azure.sdk.iot.device.exceptions.IotHubClientException;

import org.springframework.stereotype.Service;

@Service
public class D2CSendTelemetryService {

    public String sendEvent(D2CMessageBody d2cMessageBody) {
        DeviceClient client = new DeviceClient(ApplicationConstants.IOT_HUB_CONN_STRING_DEVICE,
                IotHubClientProtocol.MQTT);

        try {
            client.open(false);
            Message msg = new Message(d2cMessageBody.toString());
            msg.setContentType("application/json");
            msg.setProperty("temperatureAlert", d2cMessageBody.getTemperature() > 28 ? "true" : "false");
            msg.setMessageId(java.util.UUID.randomUUID().toString());
            msg.setExpiryTime(ApplicationConstants.D2C_MESSAGE_TIMEOUT);
            System.out.println(new ObjectMapper().writeValueAsString(d2cMessageBody));
            client.sendEvent(msg);
            return "message sent successfully from device to cloud";

        } catch (InterruptedException |IotHubClientException |JsonProcessingException e) {
            e.printStackTrace();
            return "error sending message from device to cloud";
        }

    }
}
