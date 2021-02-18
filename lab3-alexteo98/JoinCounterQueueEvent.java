class JoinCounterQueueEvent extends JoinQueueEvent { 
  private Counter ctr;
  private Customer c;

  public JoinCounterQueueEvent(Customer c, Counter ctr) { 
    super(c, ctr.getQueue());
    this.ctr = ctr;
    this.c = c;
  }

  @Override
  public String toString() { 
    return String.format("%s: %s joined counter queue (at %s)",
        super.toString(), this.c, this.ctr);
  }
}
