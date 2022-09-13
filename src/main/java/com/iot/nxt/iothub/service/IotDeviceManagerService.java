package com.iot.nxt.iothub.service;

import java.io.IOException;

import com.iot.nxt.iothub.constants.ApplicationConstants;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;
import com.microsoft.azure.sdk.iot.service.registry.Device;
import com.microsoft.azure.sdk.iot.service.registry.RegistryClient;
import com.microsoft.azure.sdk.iot.service.twin.DeviceCapabilities;

import org.springframework.stereotype.Service;

@Service
public class IotDeviceManagerService {

    public String AddDevice(String deviceId) {
        RegistryClient registryClient = new RegistryClient(ApplicationConstants.IOT_HUB_CONN_STRING);

        Device device = new Device(deviceId);
        try {
            device = registryClient.addDevice(device);

            System.out.println("Device created: " + device.getDeviceId());
            System.out.println("Device key: " + device.getPrimaryKey());
            return "device added successfully";
        } catch (IotHubException | IOException iote) {
            iote.printStackTrace();
            return "error creating device";

        }
    }

    public String getDevice(String deviceId) {
        RegistryClient registryClient = new RegistryClient(ApplicationConstants.IOT_HUB_CONN_STRING);

        Device returnDevice;
        try {
            returnDevice = registryClient.getDevice(deviceId);

            System.out.println("Device: " + returnDevice.getDeviceId());
            System.out.println("Device primary key: " + returnDevice.getPrimaryKey());
            System.out.println("Device secondary key: " + returnDevice.getSecondaryKey());
            System.out.println("Device ETag: " + returnDevice.getETag());
            return "Device details returned successfully";
        } catch (IotHubException | IOException iote) {
            iote.printStackTrace();
            return "Error getting device details";
        }
    }

    public String updateDevice(String deviceId, String edgeId) {
        RegistryClient registryClient = new RegistryClient(ApplicationConstants.IOT_HUB_CONN_STRING);

        // Create an Edge device, and set leaf-device as a child.
        Device edge = new Device(edgeId);
        DeviceCapabilities capabilities = new DeviceCapabilities();
        capabilities.setIotEdge(true);
        edge.setCapabilities(capabilities);

        try {
            edge = registryClient.addDevice(edge);

            // Set Edge device as a parent by getting its scope and adding it to the
            // device's device scope.
            Device device = registryClient.getDevice(deviceId);
            device.setScope(edge.getScope());
            device = registryClient.updateDevice(device);

            System.out.println("Device updated: " + device.getDeviceId());
            System.out.println("Device scope: " + device.getScope());
            System.out.println("Device parent: " + device.getParentScopes().get(0));
            return "device successfully updated";
        } catch (IotHubException | IOException iote) {
            iote.printStackTrace();
            return "error updating device";
        }
    }

    public String removeDevice(String edgeId,String deviceId) {
        RegistryClient registryClient = new RegistryClient(ApplicationConstants.IOT_HUB_CONN_STRING);

        try {
            registryClient.removeDevice(deviceId);
            System.out.println("Device removed: " + deviceId);

            registryClient.removeDevice(edgeId);
            System.out.println("Edge removed: " + edgeId);
            return "Edge and device has been removed";
        } catch (IotHubException | IOException iote) {
            iote.printStackTrace();
            return "error removing edge and device";
        }
    }
}
