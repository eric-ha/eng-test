package fitpay.engtest.model;

import java.util.List;

/**
* Model for the CompositeUserResponse object
*/
public class CompositeUserResponse
{
  public String userId;
  public List<CreditCard> creditCards;
  public List<Device> devices;

  public String getUserId()
  {
    return userId;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public List<CreditCard> getCreditCards()
  {
    return creditCards;
  }

  public void setCreditCards(List<CreditCard> creditCardList)
  {
    this.creditCards = creditCardList;
  }

  public List<Device> getDevices()
  {
    return devices;
  }

  public void setDevices(List<Device> deviceList)
  {
    this.devices = deviceList;
  }
}
