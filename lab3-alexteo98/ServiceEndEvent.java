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
    Customer nextCustomer;
    ctr.releaseCounter();
    /* if (ctr.getQueue().isEmpty() && this.shop.getQueue().isEmpty()){
       return new Event[] {new DepartureEvent(this.c, this.shop)};
       }else  {

       if (ctr.getQueue().isEmpty()) { 
       nextCustomer=(Customer) shop.getQueue().deq();
       }else  { 
       nextCustomer = (Customer) ctr.getQueue().deq();
       }
       return new Event[] {new ServiceBeginEvent(nextCustomer, this.shop, this.ctr), new DepartureEvent(this.c, this.shop)};
       }
       */

    if (!ctr.getQueue().isEmpty()) { 
      nextCustomer = (Customer) ctr.getQueue().deq();
      if (!shop.getQueue().isEmpty()) { 
        ctr.getQueue().enq((Customer)shop.getQueue().deq());
      }else  {   }
    } else { 
      if (!shop.getQueue().isEmpty()){
        nextCustomer = (Customer) shop.getQueue().deq();
        // System.out.println(shop.getQueue());
      } else { 
        // System.out.println("sada") ; 
        return new Event[] {new DepartureEvent(this.c, this.shop)};
      }
    }
    //System.out.println(nextCustomer);
    double currentTime = super.getTime();
    nextCustomer.setTime(currentTime);
    return new Event[] {new DepartureEvent(this.c, this.shop), new ServiceBeginEvent(nextCustomer, this.shop, this.ctr)};
}

@Override
public String toString() { 
  return String.format("%s: %s service done (by %s)", 
      super.toString(), c, ctr); 
}
}
