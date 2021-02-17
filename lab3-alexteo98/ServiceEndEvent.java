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
   Event nextEvent=null;
    if (!ctr.getQueue().isEmpty()) { 
      nextCustomer = (Customer) ctr.getQueue().deq();
      
      if (!shop.getQueue().isEmpty()) { 
       Customer nextToQueue = (Customer)shop.getQueue().deq();
        nextToQueue.setTime(super.getTime());
        nextEvent=new JoinCounterQueueEvent(nextToQueue,  ctr);
      }else  { }
    } else { 
      if (!shop.getQueue().isEmpty()){
        nextCustomer = (Customer) shop.getQueue().deq();
      } else { 
        return new Event[] {new DepartureEvent(this.c, this.shop)};
      }
    }
   double currentTime = super.getTime();
    nextCustomer.setTime(currentTime);

    if (nextEvent == null) { 
     return new Event[] {new DepartureEvent(this.c, this.shop), new ServiceBeginEvent(nextCustomer, this.shop, this.ctr)};
    
    }else{

    return new Event[] {new DepartureEvent(this.c, this.shop),  new ServiceBeginEvent(nextCustomer, this.shop, this.ctr), nextEvent};
    }
}

@Override
public String toString() { 
  return String.format("%s: %s service done (by %s)", 
      super.toString(), c, ctr); 
}
}
