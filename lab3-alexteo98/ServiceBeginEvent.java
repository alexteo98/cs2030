class ServiceBeginEvent extends Event { 

  /**
   * This class implements a service begin event of the shop.
   *
   * @author Alex Teo (Lab16A)
   * @version CS2030S AY20/21 Semester 2
   */

  // ----- Data --------------------------
  /** Customer currently being served. */
  private Customer c;

  private Counter ctr;

  /** Shop at which this event is serving. */
  private Shop shop;

  // ----- Construcctors -------------------
  /**
   * Constructor for Service Begin Event.
   *
   * @param c Customer that is currently being served.
   * @param shop Shop at which customer is being served.
   */
  public ServiceBeginEvent(Customer c, Shop shop, Counter ctr) { 
    super(c.getTime());
    this.c = c;
    this.shop = shop;
    this.ctr=ctr;
  }

  // ----- Methods ------------------------
  public Event[] simulate() { 
    ctr.occupyCounter(c);
    c = c.setTime(c.getTime() + c.getServiceTime());
    return new Event[] {new ServiceEndEvent(this.c, this.shop, this.ctr)};
  }

  @Override
  public String toString() { 
    return String.format("%s: %s service begin (by %s)", super.toString(), c, ctr);
  }
}
