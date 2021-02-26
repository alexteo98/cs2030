class LongerThan implements BooleanCondition<String> { 
  private int len;

  public LongerThan(int len) { 
      this.len=len;
  }

  public boolean test(String s) { 
      return (s.length()>this.len);
  }
}
