import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Alex Teo (Lab16A)
 * @version CS2030S AY20/21 Semester 2
 */ 

class ShopSimulation extends Simulation { 

  // ----- Data ----------------------------------

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  public Event[] initEvents;

  /** Shop object that the simulation will be carried out on. */
  private Shop shop;

  /** Queue object to be created and passed to shop. */
  private Queue q;

  /** Max length of queue allowed in shop.*/
  private int shopQueueLength;
  private int counterQueueLength;

  /** Number of Customers to be instantiated. */
  private int noOfCustomers = 0;

  /** Number of Counters to be instantiated. */
  private int noOfCounters = 0;

  /** Array containing the Arrival and Service Times. */
  private double[][] timings;

  // ----- Constructors -------------------------
  /** 
   * Constructor for a shop simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */

  public ShopSimulation(Scanner sc) { 
    this.noOfCustomers = sc.nextInt();
    this.noOfCounters = sc.nextInt();
    this.counterQueueLength = sc.nextInt();
    this.shopQueueLength = sc.nextInt();

    initEvents = new Event[noOfCustomers];

    createTimings(sc);
    shop = new Shop(this.noOfCustomers, this.noOfCounters,
        this.shopQueueLength, this.counterQueueLength, this.timings);
    populateEvents();
  }

  // ----- Methods ----------------------------

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() { 
    return initEvents;
  }

  /** 
   * Creates an array of Arrival time and Serivice Time
   * of customers.
   *
   * @param sc  Scanner object scans for 2 values per customer
   *            and assigns them as Arrival and Service Time.
   *
   * @return An array of Arrival and Service Time.
   */
  private void createTimings(Scanner sc) { 
    timings = new double[noOfCustomers][2];
    for (int i = 0; i < noOfCustomers; i++) { 
      timings[i][0] = sc.nextDouble();
      timings[i][1] = sc.nextDouble();
    }
  }

  /**
   * Populates the simulator with an array of
   * Arrival Events.
   */
  public void populateEvents() { 
    Customer[] allCustomers = shop.getCustomers();
    for (int i = 0; i < noOfCustomers; i++) { 
      initEvents[i] = new ArrivalEvent(allCustomers[i], shop);
    }
  }
}
