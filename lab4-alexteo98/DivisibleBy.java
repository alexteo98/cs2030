class DivisibleBy<T extends Integer> implements BooleanCondition<T> { 
    
  private T divisor;

  public DivisibleBy(T number) { 
      this.divisor = number;
  }

  public boolean test(T t) { 
      return (t%this.divisor==0);
  }

}
