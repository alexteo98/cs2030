class ServiceEndEvent extends Event{
  
  private Counter ctr;
  private Customer c;

  public ServiceEndEvent(Customer c,Counter ctr,double time){
    super(time);
    this.c=c;
    this.ctr=ctr;
    ctr.releaseCounter();
  }

 

  public Event[] simulate(){
    double time=super.getTime();
    return new Event[] {new DepartureEvent(c,time)};

  }
  
  @Override
  public String toString(){
    return String.format(": Customer %d service done (by Counter %d)", c.getCustomerID(), ctr.getCounterID());
  }
}
