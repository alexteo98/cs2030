class LongerThan implements BooleanCondition<String> { 

  /**
   * This class implements an Boolean Condition to
   * check if the length of a string is longer than k.
   *
   * @author Alex Teo (Lab16A)
   * @version CS2030S AY20/21 Semester 2
   */

  private int len;

  public LongerThan(int len) { 
    this.len = len;
  }

  public boolean test(String s) { 
    return (s.length() > this.len);
  }
}
