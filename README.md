# FlightBookingApplication
Java based, flight app allows users to search and book flights, interacting with MySQL DB. Consists of 3 separate Java SE projects.
A flight client (gui), a rmi server, and a java socker server.

## Flight Client
Java swing libraries are made a graphical interface, for the entry of flight search info.

## FlightApplicationServer
It's a RMI Java Server. Offers two remote methods @searchFlight(), and @reservateFlights(). Sends the requests to Java db server, 
and forwards reply to client.

## DBServer
Interacts with MySQL database and Flights table.
