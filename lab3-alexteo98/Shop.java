class Shop { 
  // ----- Data --------------------------  

  /** number of customers in shop */
  private int noOfCustomers;

  /** number of counters in shop */
  private int noOfCounters;

  /** an array to store all counter objects */
  private Array<Counter> allCounters;

  /** an array to store all customer objects */
  private Customer[] allCustomers;

  /** Queue object belonging to specific shop */
  private Queue<Customer> q;

  // ----- Constructors --------------------

  /**
   * Constructor for a Shop.
   *
   * Initialises the array of Customers and Counters by calling their relevent methods.
   *
   * @param noOfCustomers Number of Customers in the shop.
   * @param noOfCounters Number of Counters in the shop.
   * @param timings Array used to store arrival and service time of customers.
   */

  public Shop(int noOfCustomers, int noOfCounters,int shopQueueLength, int counterQueueLength,  double[][] timings) { 
    createCounters(noOfCounters, counterQueueLength);
    this.allCustomers = createCustomers(noOfCustomers, timings);
    this.noOfCustomers = noOfCustomers;
    this.noOfCounters = noOfCounters;
    this.q=new Queue<Customer>(shopQueueLength);
  }

  // ----- Getter and Setters ---------------------

  /**
   * Gets the queue object of the shop.
   *
   * @return The queue object of the shop.
   */
  public Queue getQueue() { 
    return this.q;
  }

  /**
   * Gets the array of Customers in the shop.
   *
   * @return The array containing all customer objects in the shop.
   */
  public Customer[] getCustomers() { 
    return this.allCustomers;
  }
 /**
   * Gets the array of Counters in the shop.
   *
   * @return The array containing all counter objects in the shop.
   */
  public Array<Counter> getCounters() { 
    return this.allCounters;
  }



  // ----- Methods -----------------------

  public Counter chooseCounter() { 
//    Counter ctr=allCounters.get(0);
//    for (int i=1;i<allCounters.length;i++) { 
//      if (ctr.compareTo(allCounters[i])>-1) { 
//        ctr=allCounters[i];
//      }else{}
//    }
    return allCounters.min();
  }

  public boolean isShopFull() { 
     
      for (int i=0;i<allCounters.length();i++) { 
          if (!allCounters.get(i).isFull()) { 
           // System.out.println("false");
              return false;
          }
      }
// System.out.println("counters full");
 return this.q.isFull();

  }

  /**
   * Gets a counter object that is available
   *
   * @return A counter object belonging to this shop that is available,  null if none available.
   */
  public Counter getAvailableCounter() { 
    for (int i = 0; i < noOfCounters; i++) { 
      if (allCounters.get(i).available()) { 
        return allCounters.get(i);
      } else { /*not needed*/ }
    }
    return null;
  }

  /**
   * Checks if there are any available counters in the shop.
   *
   * @return True if there is an available counter for use, false otherwise.
   */
  public boolean counterAvailable() { 
    Counter ctr = getAvailableCounter();
    if (ctr == null) { 
      return false;
    } else  { 
      return true;
    }
  }

  /**
   * Initialise an array of counters.
   *
   * @param noOfCounters Number of Counter objects to create.
   *
   * @return An array of counters belonging to the shop.
   */
  private void createCounters(int noOfCounters, int counterQueueLength) {
    allCounters = new Array<Counter>(noOfCounters);
    for (int i = 0; i < noOfCounters; i++) { 
     allCounters.set(i, new Counter(counterQueueLength));
    }
    //return allCounters;
  }

  /**
   * Initialise an array of customers.
   *
   * @param noOfCustomers Number of Customer objects to create.
   * @param timings An containing the arrival and service time of each customer.
   *
   * @return An array of customers belonging to the shop.
   */
  private Customer[] createCustomers(int noOfCustomers, double[][] timings) { 
    Customer[] allCustomers = new Customer[noOfCustomers];
    for (int i = 0; i < noOfCustomers; i++) { 
      allCustomers[i] = new Customer(timings[i][0], timings[i][1]);
    }
    return allCustomers;
  }

  /**
   * Dequeues and gets the next customer in queue.
   *
   * @return The next customer in line.
   */
  public Customer nextCustomer() { 
    return (Customer) q.deq();
  }
}
