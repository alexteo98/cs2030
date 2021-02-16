class ArrivalEvent extends Event { 

  /**
   * This class implements a arrival event of the shop.
   *
   * @author Alex Teo (Lab16A)
   * @version CS2030S AY20/21 Semester 2
   */

  // ------ Data ---------------------------------
  /** Shop object involved in the arrival event. */
  private Shop shop;

  /** Customer that just arrived in the store. */
  private Customer c; 

  // ----- Constructors --------------------------
  /**
   * Constructor for an Arrival Event.
   *
   * @param c Customer object that arrived at the shop.
   * @param shop Shop object that the customer arrived to.
   */
  public ArrivalEvent(Customer c, Shop shop) { 
    super(c.getTime());
    this.c = c;
    this.shop = shop;
  }

  // ----- Methods ------------------------------
  @Override
  public String toString() { 
    return String.format("%s: %s arrived %s", super.toString(), c, shop.getQueue());
  }

  public Event[] simulate() { 
    if (shop.counterAvailable()) { 
      return serve();
    } else {
      if (shop.isShopFull()) { 
        return depart();
      } else { 
        return joinQ();
      }
    }
  }

  /**
   * Prints a line that Customer has joined queue and enqueues customer into shop queue.
   *
   * @return Empty event.
   */
  private Event[] joinQ() { 
   /**
    Counter ctr;
    Counter[] allCounters = this.shop.getCounters();
    ctr=allCounters[0];
    for (int i=1 ;i<allCounters.length;i++) { 
      
    } 
    */
    Counter bestCounter=this.shop.chooseCounter();
    if (!bestCounter.isFull()) {

//    System.out.println(String.format("Joined counter queue %s", bestCounter.getQueue()));
      return new Event[] {new JoinQueueEvent(this.c, bestCounter.getQueue(), "counter")};  
    }

//    System.out.println(bestCounter);
//    System.out.println(String.format("Joined shop queue %s", this.shop.getQueue()));
    return new Event[] {new JoinQueueEvent(this.c, this.shop.getQueue(), "shop")};
  }  

  /**
   * Sets a counter to a customer object and return a Service Begin Event.
   *
   * @return A Service Begin Event of the customer and the shop.
   */
  private Event[] serve() {
    Counter ctr=shop.getAvailableCounter();
    c.setCounter(ctr);
    return new Event[] { new ServiceBeginEvent(this.c, this.shop, ctr)};
  }

  /**
   * Returns a Departure Event.
   *
   * @return A Departure Event of the customer and the shop.
   */
  private Event[] depart() { 
    return new Event[] { new DepartureEvent(this.c, this.shop)};
  }
}
