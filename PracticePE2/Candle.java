class Candle extends Tickable { 

  public Candle() { 
    super(); 
  }

  public Candle(Integer n) { 
    super(n);
  }

  public Candle tick() { 
      return new Candle(super.getTick()+1);
  }

  @Override
  public String toString() { 
    int i = super.getTick();
    if (i == 0) { 
        return "\nCandle flickers.";
    } else if (i == 1) { 
        return "\nCandle is getting shorter.";
    } else if (i == 2) { 
        return "\nCandle is about to burn out.";
    } else { 
        return "\nCandle has burned out.";
    }
  }
}
