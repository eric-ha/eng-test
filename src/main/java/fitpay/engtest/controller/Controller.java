package fitpay.engtest.controller;

import fitpay.engtest.service.CompositeUserService;
import fitpay.engtest.model.CompositeUserResponse;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//Controller layer: will map the incoming request and handles it
@org.springframework.stereotype.Controller
@RequestMapping(value = "/compositeUsers", produces = APPLICATION_JSON_VALUE)
public class Controller
{

  @Autowired
  CompositeUserService cuService;
  /**
   * The purpose of this function is to get a CompositeUserResponse object given with/without query parameters
   *
   * @param userId          (Path Variable) the id of the user
   * @param creditCardState (optional) credit card state queried
   * @param deviceState     (optional) device state queried
   * @return the CompositeUserResponse object in JSON format
   */
  @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<CompositeUserResponse> getCompositeUserResponse
          (@PathVariable String userId,
          @RequestParam(required = false) String creditCardState,
          @RequestParam(required = false) String deviceState)
  {

    CompositeUserResponse response = cuService.getCompositeUserInfo(userId);

    if (creditCardState != null && deviceState != null)
    {
        response = cuService.getCompositeUserInfoFilterAll(response, creditCardState, deviceState);
    }
    else if(creditCardState != null)
    {
        response = cuService.getCompositeUserCardFilter(response, creditCardState);
    }
    else if(deviceState != null)
    {
        response = cuService.getCompositeUserDeviceFilter(response, deviceState);
    }

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
