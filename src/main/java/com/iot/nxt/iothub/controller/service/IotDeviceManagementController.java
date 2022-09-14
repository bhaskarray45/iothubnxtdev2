package com.iot.nxt.iothub.controller.service;

import com.iot.nxt.iothub.service.IotDeviceManagerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IotDeviceManagementController {

    @Autowired
    private IotDeviceManagerService iotDeviceManagerService;

    @PostMapping("/device/add")
    public String addDevice(@RequestParam String deviceId) {

        return iotDeviceManagerService.addDevice(deviceId);
    }

    @GetMapping("/device/get")
    public String getDevice(@RequestParam String deviceId) {
        return iotDeviceManagerService.getDevice(deviceId);

    }

    @PutMapping("/device/update")
    public String updateDevice(@RequestParam String deviceId,
                                @RequestParam String edgeId){

        return iotDeviceManagerService.updateDevice(deviceId, edgeId);
    }

    @DeleteMapping("/device/delete/{edgeId}/{deviceId}")
    public String deleteDevice(@PathVariable String edgeId, @PathVariable String deviceId) {
        return iotDeviceManagerService.removeDevice(edgeId, deviceId);
    }
}
