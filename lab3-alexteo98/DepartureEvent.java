class DepartureEvent extends Event { 

  /**
   * This class implements a departure event of the shop.
   *
   * @author Alex Teo (Lab16A)
   * @version CS2030S AY20/21 Semester 2
   */ 

  // ----- Data -------------------------
  /** Customer that Departed. */
  private Customer c;

  /** Shop at which the customer just departed. */
  private Shop shop;

  /** Empty Event to be returned once queue is finished. */
  private Event[] emptyEvent =  new Event[] {};

  // ----- Constructors ---------------
  /**
   * Constructor for Departure Event.
   *
   * @param c Customer which just departed.
   * @param shop Shop at which departure event is happening.
   */
  public DepartureEvent(Customer c, Shop shop) { 
    super(c.getTime());
    this.c = c;
    this.shop = shop;
  }

  // ----- Methods --------------------
  @Override
  public String toString() { 
    return String.format("%s: %s departed", super.toString(), c);
  }

  public Event[] simulate() { 
    return emptyEvent;
  }

  /**
   * Starts another service to serve next customer is queue.
   * De-queues the next Customer from shop queue.
   * Sets the start of the next service to the current time.
   * Assigns the current counter to the upcoming Customer.
   *
   * @return new Service Begin Event for next customer in queue.
   *
   private Event[] nextService() { 
   Customer nextCustomer = (Customer) this.shop.getQueue().deq();
   nextCustomer.setTime(super.getTime());
   nextCustomer.setCounter(this.c.getCounter());
   return new Event[] {new ServiceBeginEvent(nextCustomer, this.shop) };    
   }
   */
}
