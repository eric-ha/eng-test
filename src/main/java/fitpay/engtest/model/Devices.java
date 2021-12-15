package fitpay.engtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
* Model to represent a Devices object to match JSON object structure
*/
public class Devices {
    @JsonProperty("results")
    public List<Device> devices;

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
