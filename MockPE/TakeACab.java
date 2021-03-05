class TakeACab implements Service{ 
  
   private final int CENTS_PER_KM = 33;
   private final int BOOKING_FEE = 200;
    
   private Request req;

  public TakeACab(){
    super();
  }

  public int computeFare(Request req){ 
     int totalFare=0;
     this.req=req;
     totalFare += this.req.getDistance() * this.CENTS_PER_KM;
     totalFare += BOOKING_FEE;
     return totalFare;
  }

  @Override
  public String toString(){ 
    return "TakeACab";
  }

}
