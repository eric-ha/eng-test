# Garmin Engineering Test : Eric Ha

## To Run:
To run the application, run this command `mvn spring-boot:run` in the root directory (eng-test)

This new API resource will allow the user to hit an endpoint called /compositeUsers so that they can retrieve information about a user given the userId. Prior to this new API if you wanted to get information such as the credit cards and devices of a user you would have to make 3 separate API calls. One to get the user, one to get the users cards, and one to get the users devices.
Now hitting the /compositeUsers endpoint will return all this information with one API call. The API has a `Controller` class that helps map the incoming request (the URL and its parameters). The 'Service' class does authentication for the API calls as well as assigning the credit card and device data given or not given a filter string in the URL.
