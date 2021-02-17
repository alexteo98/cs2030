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
      if (!t) { 
//          System.out.println("Error during enq");
      }
//      System.out.println(t);
      return this.emptyEvent;
  }
  public String  ggetTime(){
    return String.format("%.3f", super.getTime());
  }

  private Event[] joinQ() { 
    System.out.println(String.format("%s: %s joined %s queue %s", 
          super.toString(), this.c, this.q));
    this.q.enq(this.c);
    return emptyEvent;
  }

  @Override
  public String toString() { 
      return super.toString();
  }

}
