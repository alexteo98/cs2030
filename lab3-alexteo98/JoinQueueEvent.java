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
     return this.emptyEvent;
  }
  public String  ggetTime(){
    return String.format("%.3f", super.getTime());
  }

 @Override
  public String toString() { 
      return super.toString();
  }

}
