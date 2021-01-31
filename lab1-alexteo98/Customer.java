class Customer{

 /**
 * This class implements a customer.
 *
 * @author Alex Teo (Lab16A)
 * @version CS2030S AY20/21 Semester 2
 */

  // ----- Data ---------------------------
  private int customerID=0;
  private static int lastCustomerID=0;
  private int currentCounter=0;
  //private double serviceTime = 0;

  // ----- Constructors ------------------
  public Customer(){
    this.customerID=lastCustomerID;
    lastCustomerID++;
  }

  // ----- Getter and Setters ------------
  public int getCustomerID(){
    return this.customerID;
  }

  public void setCounter(int n){
    currentCounter=n;
  }
 // public void setTime(double Time){
 //   this.serviceTime=Time;
 // }

  //public double getServiceTime(){
  //  return this.serviceTime;
  //}


  //public void addServiceTime(double t){
  //  this.serviceTime+=t;
  //}

}