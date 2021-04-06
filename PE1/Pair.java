/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */
public class Pair<T> implements SourceList<T> { 
  private T first;
  private SourceList<T> second;
  //private currentLen = 0;

  public Pair(T first, SourceList<T> second) { 
    this.first = first;
    this.second = second;
  }

  @Override
  public T getFirst() { 
    return this.first;
  }

  @Override
  public SourceList<T> getSecond() { 
    return this.second;
  }

  @Override
  public String toString() { 
    return this.first + ", " + this.second;
  }
  // Write your code here
  
  public int length() { 
    return 1 + getSecond().length();
  }

  public boolean equals(Object p) { 
    if (p instanceof Pair) { 
      @SuppressWarnings("unchecked")
      Pair p1 = (Pair) p;
      if (p1.getFirst().equals(this.getFirst())) { 
        return p1.getSecond().equals(this.getSecond());
      } else  { 
        return false;
      }
    } else  { 
      return false;
    }
  }

  public SourceList<T> filter(BooleanCondition<? super T> bc) { 
    if (bc.test(this.first)) {  
      return new pair<T>(this.getFirst(), this.getSecond().filter(bc));
    } else  { 
      return this.getSecond().filter(bc);
    }
  }

}
