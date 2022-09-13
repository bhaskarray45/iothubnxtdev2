package com.iot.nxt.iothub.controller.service;

import com.iot.nxt.iothub.model.D2CMessageBody;
import com.iot.nxt.iothub.service.D2CSendTelemetryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class D2CSendTelemetryController {
    
    @Autowired
    private D2CSendTelemetryService d2CSendTelemetryService;
    @PostMapping("send/message/D2C")
    public String sendEvent(@RequestBody D2CMessageBody d2cMessageBody){

        return d2CSendTelemetryService.sendEvent(d2cMessageBody);

    }
}
