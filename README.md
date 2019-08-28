
Documentation
-------------

Application does following:

- Go to site http://www.expedia.com
- Select "Flights"
- Type London in "Fly from: city or airport"
- Select Heathrow in popup
- Type Dublin in "Flying to: city or airport"
- Select "Dublin Airport (DUB), Ireland" in popup
- Select Departing: 01/03/2020
- Select Departing: 07/03/2020
- Select 2 adults
- Click Search Button      
- Wait for all flights to be loaded. Find first row excluding the promotional row for “Flight + Hotel”                                                                   
- Assert Positive scenario that the price in first row is $94 (or any other price at your time)
- Assert Negative scenario that the price in first row is not $22.33
- Assert the visible left panel list of “Airlines included” below the list of “Stops”
- Scroll to the bottom of the page and assert the visibility of text “© 2018 Expedia, Inc. All rights reserved.”


Execution
-------------
It is executable in **chrome** and **firefox** browsers.
```
 mvn clean test -Dbrowser=chrome
```
