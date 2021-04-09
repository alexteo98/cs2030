class Troll extends Tickable { 

  public Troll() { 
    super(); 
  }

  public Troll(Integer n) { 
    super(n);
  }

  public Troll tick() { 
      return new Troll(super.getTick()+1);
  }

  @Override
  public String toString() { 
    int i = super.getTick();
    if (i == 0) { 
        return "\nTroll lurks in the shadows.";
    } else if (i == 1) { 
        return "\nTroll is getting hungry.";
    } else if (i == 2) { 
        return "\nTroll is VERY hungry.";
    } else if (i == 3) { 
        return "\nTroll is SUPER HUNGRY and is about to ATTACK!";
    } else { 
        return "\nTroll attacks!";
    }
  }
}
