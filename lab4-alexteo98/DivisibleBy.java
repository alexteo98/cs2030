class DivisibleBy implements BooleanCondition<Integer> { 
    
  private Integer divisor;

  public DivisibleBy(Integer number) { 
      this.divisor = number;
  }

  public boolean test(Integer t) { 
      return (t%this.divisor==0);
  }

}
