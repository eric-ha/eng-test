package fitpay.engtest.model;

/**
 * Wrapper class for the auth token
 */
public class Token
{
    public String access_token;

    public String getAccessToken()
    {
      return access_token;
    }

    public void setAccessToken(String bearerToken)
    {
      this.access_token = bearerToken;
    }
}
