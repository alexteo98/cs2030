class Booking implements Comparable<Booking> { 

  private Car car;
  private Request req;
  private Service service; 
  private String s; 

    public Booking(Car car, Service service, Request req) throws IllegalArgumentException{

      this.car = car;
      this.req = req;
      this.service = service;

      if (car instanceof Cab){
        if (!(service instanceof JustRide) &&!(service instanceof TakeACab)) {
          s=String.format("%s does not provide the %s service.",car,service);
          throw new IllegalArgumentException(s);
        }
      } else if (car instanceof PrivateCar){
        if (!(service instanceof JustRide) &&!(service instanceof ShareARide)) {
          s=String.format("%s does not provide %s service.",car,service);
          throw new IllegalArgumentException(s);
        }
      }
    }

    public String getMessage(){
      return s;
    }

  public int getFare(){
    return this.service.computeFare(this.req);
  }

  public int getWaitingTime(){
    return this.car.getMinutesAway();
  }

  public int compareTo(Booking b){

    if (getFare()<b.getFare()){
      return -1;
    } else if (getFare()>b.getFare()){
      return 1;
    } else {
      if (getWaitingTime()<b.getWaitingTime()){
        return -1;
      } else if (getWaitingTime()>b.getWaitingTime()){
        return 1;
      } else{
        return 0;
      }
    }
  }
}
