# BusSimulation

A program written in Java that simulates a bus system in order to optimize efficieny of buses based on different numbers of passengers 
with different numbers of buses and sizes of buses, etc. 

## Prerequisites

A Jave SE Development Kit must be installed to run program.

To play run 'java BusRouteSim' in cmd after compiling 'BusRouteSim.java' using javac.

## Usage

This simulation uses a priority queue to keep track of the execution of different events within the simulation. There are 2 types of events in the simulation. A BusEvent occurs when a bus reaches a stop. A PassengerEvent occurs when a passenger enters the system and begins waiting for a bus at a bus stop. Depending on the values manipulated in the constants.java file, these events occur at different times and in different ways. There are a number of variables in the constants.java file that can be mainipulated to alter the statistics of the BusSimulation. The 5 main constants to change are (plus 2 related to saving stats):

```java
// variables to be changed to affect simulation
  public static int BUS_SIZE = 40; // number of passengers a bus can hold (40 or 60)
  public static int BUS_MPG = 6; // miles per gallon fuel efficiency of bus
  public static int NUM_BUSES = 10; // number of buses to be run in simulation
  public static int SIMULATION_TIME_LENGTH = 18000; // time to run simulation in seconds
  public static int PASSENGER_INTERVAL = 480; // how often a passenger will enter the line at a bus stop
  public static boolean WRITE_STATS_TO_TXT = false; // whether user wants stats written to text file
  public static String TEXT_FILE_NAME = ""; // filename of text file to write stats to.
```

Upon start of this program, the simulation will begin immediatly as all user input/variables to change should have already been updated in the constants.java file. This is an accelerated simulation, so if you set SIMULATION_TIME_LENGTH to 10800 (3hrs) it will not take 3 hours to run. It will simulate 3 hours of run time almost immediately. Once the simulation is completed, a number of different statistics will be displayed on screen. For example:

    Number of buses: 10
    Size of bus: 40
    MPG of bus: 6
    Time of simulation: 43200
    Average passenger interval: 480
    Number of people who got on a bus: 725.0
    Total number of people who got in line: 936
    Average bus occupancy: 10.275862068965518
    Max bus occupancy: 40
    Average Passenger Miles Per Gallon: 61.65517241379311
    Average travel time: 6434.297008547009
    Max travel time: 26002.0
    Min travel time: 306.0
    
This simulation starts with empty buses initialized at a random stop travelling in a random direction. There are also no passengers queued at any stop when the simulation starts. 

## Authors
Matt Saffert
