class LastDigitsOfHashCode implements Transformer<Object, Integer> { 

  private int digits;

  public LastDigitsOfHashCode(int k) { 
    this.digits=k;
  }

@Override
  public Integer transform(Object o) { 
    int hCode =Math.abs( o.hashCode());
    int divisor =(int)Math.pow(10, this.digits);
    return (Integer) hCode%divisor;
  }
}
