class JoinQueueEvent extends Event { 

  private Queue<Customer> q;
  private Customer c;
  private Event[] emptyEvent = new Event[] {};

  public JoinQueueEvent(Customer c, Queue<Customer> q) { 
    super(c.getTime());
    this.c = c;
    this.q = q;
  }

  public Event[] simulate() { 
    this.q.enq(this.c);
    return this.emptyEvent;
  }

  @Override
  public String toString() { 
    return super.toString();
  }

}
