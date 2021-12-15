package fitpay.engtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
* Model to represent a Device object
*/
public class Device implements Serializable
{
  private String deviceId;
  private String state;

  @JsonProperty("deviceIdentifier") //To set the deviceId as "deviceIdentifier" from JSON object
  public void setDeviceId(String deviceId)
  {
    this.deviceId = deviceId;
  }

  @JsonProperty("deviceId") //To output deviceIdentifier as deviceId in CompositeUserResponse
  public String getDeviceId()
  {
    return deviceId;
  }

  public String getState()
  {
    return state;
  }

  public void setState(String deviceState)
  {
    this.state = deviceState;
  }

}
