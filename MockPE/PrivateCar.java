class PrivateCar implements Car {

  private String plate;
  private int minutesAway;

  public PrivateCar(String plate,int minutes){
    this.plate = plate;
    this.minutesAway = minutes;
  }

  public int getMinutesAway(){
    return this.minutesAway;
  }


  public String toString(){
    String minsAway;
    if (this.minutesAway>1){
      minsAway=String.format("%d mins away",this.minutesAway);
    } else { 
      minsAway=String.format("%d min away",this.minutesAway);

    }
    return String.format("PrivateCar %s (%s)",this.plate,minsAway);

  }
}
