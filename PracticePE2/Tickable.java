abstract class Tickable { 
  private int tick = 0;  
  
  public Tickable(int n) { 
      this.tick = n;
  }

  public Tickable() { 
  }

  public int getTick() { 
    return this.tick;
  }

  public abstract Tickable tick();
}
