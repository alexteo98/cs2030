class JoinQueueEvent extends Event { 
    
  private Queue<Customer> q;
  private Customer c;
  private Event[] emptyEvent = new Event[] {};

  public JoinQueueEvent(Customer c, Queue q) { 
    super(c.getTime());
    this.c = c;
    this.q = q;
  }

  public Event[] simulate() { 
      boolean t=this.q.enq(this.c);
//      System.out.println(t);
      return this.emptyEvent;
  }

  private Event[] joinQ() { 
    System.out.println(String.format("%s: %s joined queue %s", 
          super.toString(), this.c, this.q));
    this.q.enq(this.c);
    return emptyEvent;
  }

  @Override
  public String toString() { 
      return String.format("%s: %s joined queue %s", 
            super.toString(), this.c, this.q);
  }

}
