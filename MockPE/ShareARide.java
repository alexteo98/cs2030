class ShareARide implements Service { 
  private Request req;
  private final int CENTS_PER_KM = 50;
  private final int SURCHARGE = 500;

  public ShareARide(){
    super();
  }

  public int computeFare(Request req){
    int totalFare = 0;
    this.req = req;
    totalFare += this.req.getDistance() * this.CENTS_PER_KM;
    if (peak()){ 
      totalFare+=this.SURCHARGE;
    }
    return totalFare/this.req.getNoOfPassengers();
  }

  public boolean peak(){
    if (this.req.getTime() >= 600 && this.req.getTime() <= 900){ 
      return true;
    }
    return false;
  }

  @Override
  public String toString(){
    return "ShareARide";
  }
}

