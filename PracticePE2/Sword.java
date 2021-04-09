class Sword extends Tickable { 

  public Sword() { 
    super(); 
  }

  public Sword(Integer n) { 
    super(n);
  }

  public Sword tick() { 
      return new Sword(super.getTick()+1);
  }

  @Override
  public String toString() { 
    return "\nSword is shimmering."; 
  }
}
