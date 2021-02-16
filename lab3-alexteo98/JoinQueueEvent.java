class JoinQueueEvent extends Event { 
    
  private Queue<Customer> q;
  private Customer c;
  private Event[] emptyEvent = new Event[] {};
  private String queueType;

  public JoinQueueEvent(Customer c, Queue q, String queueType) { 
    super(c.getTime());
    this.c = c;
    this.q = q;
    this.queueType=queueType;
  }

  public Event[] simulate() { 
      boolean t=this.q.enq(this.c);
      if (!t) { 
//          System.out.println("Error during enq");
      }
//      System.out.println(t);
      return this.emptyEvent;
  }

  private Event[] joinQ() { 
    System.out.println(String.format("%s: %s joined %s queue %s", 
          super.toString(), this.queueType, this.c, this.q));
    this.q.enq(this.c);
    return emptyEvent;
  }

  @Override
  public String toString() { 
      return String.format("%s: %s joined %s queue %s", 
            super.toString(), this.c, this.queueType, this.q);
  }

}
