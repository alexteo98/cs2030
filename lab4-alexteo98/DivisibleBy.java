class DivisibleBy implements BooleanCondition<Integer> { 

  /**
   * This class implements a Boolean Condition to check if
   * an Integer is divisible by another Integer.
   *
   * @author Alex Teo (Lab16A)
   * @version CS2030S AY20/21 Semester 2
   */

  private Integer divisor;

  public DivisibleBy(Integer number) { 
    this.divisor = number;
  }

  public boolean test(Integer t) { 
    return (t % this.divisor == 0);
  }

}
