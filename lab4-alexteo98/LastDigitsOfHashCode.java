class LastDigitsOfHashCode implements Transformer<Object, Integer> { 

  /**
   * This class implements a Transformer to transform
   * an Object to the last k digits of that object's
   * hash code.
   *
   * @author Alex Teo (Lab16A)
   * @version CS2030S AY20/21 Semester 2
   */

  private int digits;

  public LastDigitsOfHashCode(int k) { 
    this.digits = k;
  }

  @Override
  public Integer transform(Object o) { 
    int hashed = Math.abs(o.hashCode());
    int divisor = (int) Math.pow(10, this.digits);
    return (Integer) hashed % divisor;
  }
}
