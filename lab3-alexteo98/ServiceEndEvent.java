class ServiceEndEvent extends Event { 

  /**
   * This class implements a service end event of the shop.
   *
   * @author Alex Teo (Lab16A)
   * @version CS2030S AY20/21 Semester 2
   */ 

  // ----- Data ---------------------------
  /** Customer who just ended service. */
  private Customer c;

  private Counter ctr;

  /** Shop at which service end event is happening. */
  private Shop shop;

  // ----- Constructors ---------------------
  /**
   * Constructor for Service End Event.
   *
   * @param c Customer that just ended service.
   * @param shop Shop that Service End event is happening.
   */
  public ServiceEndEvent(Customer c, Shop shop,  Counter ctr) { 
    super(c.getTime());
    this.c = c;
    this.shop = shop;
    this.ctr=ctr;
  }

  // ----- Methods ------------------------
  public Event[] simulate() { 
    c.getCounter().releaseCounter();
    if (ctr.getQueue().isEmpty()){
      return new Event[] {new DepartureEvent(this.c, this.shop)};
    }else  {
      Customer nextCustomer = (Customer) ctr.getQueue().deq();
        return new Event[] {new ServiceBeginEvent(nextCustomer, this.shop, this.ctr), new DepartureEvent(this.c, this.shop)};
    }
  }

  @Override
  public String toString() { 
    return String.format("%s: %s service done (by %s %s)", 
        super.toString(), c, c.getCounter(), c.getCounter().getQueue()); 
  }
}
