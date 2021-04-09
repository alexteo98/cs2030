/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */
public class Pair<T> implements SourceList<T> { 

  private T first;
  private SourceList<T> rest;

  public Pair(T first, SourceList<T> rest) { 
    this.first = first;
    this.rest = rest;
  }

  public int length() { 
    return 1 + this.rest.length();
  }

  public T getFirst() { 
    return this.first;
  }
  public SourceList<T> getRest() { 
    return this.rest;
  }
  public SourceList<T> filter(BooleanCondition<? super T> predicate) { 
    if (predicate.test(this.first)) { 
      return new Pair<>(this.first, this.rest.filter(predicate));
    } else {
      return this.rest.filter(predicate);
    }
  }

  public <R> SourceList<R> map(Transformer<? super T, ? extends R> tr) { 
      return new Pair<R>(tr.transform(this.first), this.rest.map(tr));
  }

  @Override
  public boolean equals(Object o) { 
    if (o instanceof Pair) { 
      @SuppressWarnings("unchecked")
      Pair<?> p = (Pair<?>) o;
      return this.first.equals(p.first) && this.rest.equals(p.rest);
    } else  { 
      return false;
    }
  }

  @Override
  public String toString() { 
      return String.format("%s, %s", this.first, this.rest);
  }
}
