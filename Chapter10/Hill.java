// Find connections using hill climbing.
import java.util.*;
import java.io.*;

// Flight information.
class FlightInfo3 {
  String from;
  String to;
  int distance;
  boolean skip; // used in backtracking

  FlightInfo3(String f, String t, int d) {
    from = f;
    to = t;
    distance = d;
    skip = false;
  }
}

class Hill {
  final int MAX = 100;

  // This array holds the flight information.
  FlightInfo3 flights[] = new FlightInfo3[MAX];

  int numFlights = 0; // number of entries in flight array

  Stack btStack = new Stack(); // backtrack stack

  public static void main(String args[])
  {
    
    String to, from;
    Hill ob = new Hill();
    BufferedReader br = new 
      BufferedReader(new InputStreamReader(System.in)); 
 
    ob.setup();  

    try { 
      System.out.print("From? ");
      from = br.readLine(); 
      System.out.print("To? ");
      to = br.readLine(); 

      ob.isflight(from, to);

      if(ob.btStack.size() != 0)
        ob.route(to);

    } catch (IOException exc) { 
      System.out.println("Error on input.");
    }
  }
  
  // Initialize the flight database.
  void setup()
  {
    addFlight("New York", "Chicago", 900);
    addFlight("Chicago", "Denver", 1000);
    addFlight("New York", "Toronto", 500);
    addFlight("New York", "Denver", 1800);
    addFlight("Toronto", "Calgary", 1700);
    addFlight("Toronto", "Los Angeles", 2500);
    addFlight("Toronto", "Chicago", 500);
    addFlight("Denver", "Urbana", 1000);
    addFlight("Denver", "Houston", 1000);
    addFlight("Houston", "Los Angeles", 1500);
    addFlight("Denver", "Los Angeles", 1000);
  }
  
  // Put flights into the database.
  void addFlight(String from, String to, int dist)
  {  
    if(numFlights < MAX) {
      flights[numFlights] =
        new FlightInfo3(from, to, dist);

      numFlights++;
    }
    else System.out.println("Flight database full.\n");
  }

  // Show the route and total distance.
  void route(String to)
  {
    Stack rev = new Stack();
    int dist = 0;
    FlightInfo3 f;
    int num = btStack.size();

    // Reverse the stack to display route.
    for(int i=0; i < num; i++) 
      rev.push(btStack.pop());

    for(int i=0; i < num; i++) {
      f = (FlightInfo3) rev.pop();
      System.out.print(f.from + " to ");
      dist += f.distance;
    }

    System.out.println(to);
    System.out.println("Distance is " + dist);
  }

  /* If there is a flight between from and to,
     return the distance of flight;
     otherwise, return 0. */
  int match(String from, String to)
  {
    for(int i=numFlights-1; i > -1; i--) {
      if(flights[i].from.equals(from) &&
         flights[i].to.equals(to) && 
         !flights[i].skip)
      {
        flights[i].skip = true; // prevent reuse
        return flights[i].distance;
      }
    }

    return 0; // not found 
  }
  
  // Given from, find the farthest connection.
  FlightInfo3 find(String from)
  {
    int pos = -1;
    int dist = 0;

    for(int i=0; i < numFlights; i++) {
      if(flights[i].from.equals(from) &&
         !flights[i].skip)
      {
        if(flights[i].distance > dist) {
          pos = i;
          dist = flights[i].distance;
        }
      }
    }

    if(pos != -1) {
      flights[pos].skip = true; // prevent reuse
      FlightInfo3 f = new FlightInfo3(flights[pos].from,
                           flights[pos].to,
                           flights[pos].distance);
      return f;
    }

    return null;
  }
  
  // Determine if there is a route between from and to. 
  void isflight(String from, String to)
  {
    int dist;
    FlightInfo3 f;

    // See if at destination.
    dist = match(from, to);
    if(dist != 0) {
      btStack.push(new FlightInfo3(from, to, dist));
      return;
    }

    // Try another connection.
    f = find(from);
    if(f != null) {
      btStack.push(new FlightInfo3(from, to, f.distance));
      isflight(f.to, to);
    }
    else if(btStack.size() > 0) {
      // Backtrack and try another connection.
      f = (FlightInfo3) btStack.pop();
      isflight(f.from, f.to);
    }
  }
}  
  