class DepartureEvent extends Event { 
 
  /**
  * This class implements a departure event of the shop.
  *
  * @author Alex Teo (Lab16A)
  * @version CS2030S AY20/21 Semester 2
  */ 

  // ----- Data -------------------------
  private Customer c;
  
  // ----- Constructors ---------------
  public DepartureEvent(Customer c) { 
    super(c.getTime());
    this.c = c;
  }

  // ----- Methods --------------------
  @Override
  public String toString() { 
    return super.toString() + String.format(": %s departed", c);
  }

  public Event[] simulate() { 
    return new Event[] { };
  }
}
