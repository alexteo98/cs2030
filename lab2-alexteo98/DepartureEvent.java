class DepartureEvent extends Event { 
 
  /**
  * This class implements a departure event of the shop.
  *
  * @author Alex Teo (Lab16A)
  * @version CS2030S AY20/21 Semester 2
  */ 

  // ----- Data -------------------------
  private Customer c;
  private Shop shop;
  
  // ----- Constructors ---------------
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
    Queue q = this.shop.getQueue();
    if (q.isEmpty()) { 
        return new Event[] {};
    } else if (!shop.counterAvailable()) { 
        return new Event[] {};
    }
    else {
      Customer nextCustomer = (Customer)this.shop.getQueue().deq();
      nextCustomer.setTime(super.getTime());
      nextCustomer.setCounter(this.c.getCounter());
         return new Event[] {new ServiceBeginEvent(nextCustomer, this.shop) };
    }
  }
}
