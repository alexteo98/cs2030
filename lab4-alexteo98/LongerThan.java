class LongerThan<T extends String> implements BooleanCondition<T> { 
  private int len;

  public LongerThan(int len) { 
      this.len=len;
  }

  public boolean test(T s) { 
      return (s.length()>this.len);
  }
}
