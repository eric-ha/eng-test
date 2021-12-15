package fitpay.engtest.service;

import fitpay.engtest.model.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
* Service layer: performs business logic, performs logic on the data that is mapped with model classes. Supports the Controller class given client requested data
* Authentification occurs here
*/
@org.springframework.stereotype.Service
public class CompositeUserService
{
  private String bearerToken;

  /**
   * The purpose of this function is to build the RestTemplate object with authorization from the client id and client secret
   *
   * @param templateBuilder
   * @return RestTemplate
   */
  @Bean
  public RestTemplate buildAuthRestTemplate(RestTemplateBuilder templateBuilder)
  {
      return templateBuilder.basicAuthorization("pSXt7U9c", "cAsx5TMH").build();
  }

  /**
   * The purpose of this function is to generate the bearer token in order to execute the api calls
   * An exception will be thrown if auth is not valid
   */
  @Bean
  @Qualifier("buildAuthRestTemplate")
  public CommandLineRunner run(RestTemplate restTemplate)
  {
      String baseAuthUrl = "https://auth.qa.fitpay.ninja/oauth/token?grant_type=client_credentials";

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<String> entity = new HttpEntity<>(headers);

      return args -> {
          try {
              Token token = restTemplate.exchange(baseAuthUrl, HttpMethod.GET, entity, Token.class).getBody();
              if (token != null)
              {
                  bearerToken = token.getAccessToken();
              }
              else
              {
                  throw new Exception();
              }
          } catch (Exception e) {
              throw new Exception("Unable to parse Bearer token: " + e.getMessage());
          }
      };
  }

  /**
   * The purpose of this function is to build the CompositeUserResponse when given just a userId (no filters)
   *
   * @param String userId
   * @return CompositeUserResponse object
   */
  public CompositeUserResponse getCompositeUserInfo(String userId)
  {
      CompositeUserResponse cuResponse = new CompositeUserResponse();
      cuResponse.setUserId(userId);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.set("Authorization", "Bearer " + bearerToken);
      HttpEntity<String> entity = new HttpEntity<>(headers);
      RestTemplate restTemplate = new RestTemplate();

      String baseUrl = "https://api.qa.fitpay.ninja/users/";
      CreditCards cards = restTemplate.exchange(baseUrl + userId + "/creditCards", HttpMethod.GET, entity, CreditCards.class).getBody();
      if(cards != null)
      {
          cuResponse.setCreditCards(cards.getCreditCards());
      }
      else
      {
          cuResponse.setCreditCards(Collections.emptyList());
      }

      Devices devices = restTemplate.exchange(baseUrl + userId + "/devices", HttpMethod.GET, entity, Devices.class).getBody();
      if(devices != null)
      {
          cuResponse.setDevices(devices.getDevices());
      }
      else
      {
          cuResponse.setDevices(Collections.emptyList());
      }

      return cuResponse;
  }

    /**
   * The purpose of this function is to help return a list of credit cards with a given a creditCardState
   *
   * @param CompositeUserResponse this object will have all the creditCards to a user
   * @param String creditCardState the credit card state we want to filter from the CompositeUserResponse
   * @return CompositeUserResponse object with filtered credit cards that satisfy the creditCardState filter
   */
  public CompositeUserResponse getCompositeUserCardFilter(CompositeUserResponse response, String creditCardState)
  {
      List<CreditCard> filteredCards = new ArrayList<>();

      for (int i=0; i < response.getCreditCards().size(); i=i+1) {
          if (response.getCreditCards().get(i).getState().equals(creditCardState)) {
              filteredCards.add(response.getCreditCards().get(i));
          }
      }
      response.setCreditCards(filteredCards);
      return response;
  }

    /**
   * The purpose of this function is to help return a list of devices with a given a deviceState
   *
   * @param CompositeUserResponse this object will have all the devices to a user
   * @param String deviceState the device state we want to filter from the CompositeUserResponse
   * @return CompositeUserResponse object with filtered devices that satisfy the deviceState filter
   */
  public CompositeUserResponse getCompositeUserDeviceFilter(CompositeUserResponse response, String deviceState)
  {
      List<Device> filteredDevices = new ArrayList<>();

      for (int i=0; i < response.getDevices().size(); i=i+1) {
          if (response.getDevices().get(i).getState().equals(deviceState)) {
              filteredDevices.add(response.getDevices().get(i));
          }
      }
      response.setDevices(filteredDevices);
      return response;
  }

    /**
   * The purpose of this function is to help return a list of credit cards AND devices when we are given both a creditCardState and deviceState filter in the url query parameters
   *
   * @param CompositeUserResponse this object will have all the credit cards and devices to a user
   * @param String creditCardState the credit card state we want to filter from the CompositeUserResponse
   * @param String deviceState the device state we want to filter from the CompositeUserResponse
   * @return CompositeUserResponse object with filtered credit cards and devices that satisfy both the creditCardState and deviceState filter 
   */
  public CompositeUserResponse getCompositeUserInfoFilterAll(CompositeUserResponse response, String creditCardState, String deviceState)
  {
        response = getCompositeUserCardFilter(response, creditCardState);
        response = getCompositeUserDeviceFilter(response, deviceState);
        return response;
    }


}
