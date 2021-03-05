class Request{ 
  
  private int distance;
  private int noOfPassengers;
  private int time;
  
  public Request(int dist, int noOfPassengers, int time){ 
    this.distance = dist;
    this.noOfPassengers = noOfPassengers;
    this.time = time;
  }

  public int getDistance(){ 
    return this.distance;
  }

  public int getTime(){
    return this.time;
  }

  public int getNoOfPassengers(){ 
    return this.noOfPassengers;
  }
    
}
