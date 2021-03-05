class JustRide implements Service{  

  private final int SURCHARGE = 500;
  private final int CENTS_PER_KM = 22;
  private Request req;

  public JustRide(){
    super();
  }

  public int computeFare(Request req){ 
    int totalFare = 0;  
    this.req = req;
    
    totalFare += this.req.getDistance() * this.CENTS_PER_KM;
    if (peak()){ 
      totalFare += this.SURCHARGE;
    }

    return totalFare;
  }

  public boolean peak(){
    if (this.req.getTime() >= 600 && this.req.getTime() <= 900){ 
      return true;
    }

    return false;
  }

  @Override
  public String toString(){
    return "JustRide";
  }

}
