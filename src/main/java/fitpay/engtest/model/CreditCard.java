package fitpay.engtest.model;

/**
* Model to represent a CreditCard object
*/
public class CreditCard
{
  public String creditCardId;
  public String state;

  public String getCreditCardId()
  {
    return creditCardId;
  }

  public void setCreditCardId(String creditCardId)
  {
    this.creditCardId = creditCardId;
  }

  public String getState()
  {
    return state;
  }

  public void setState(String state)
  {
    this.state = state;
  }
}
