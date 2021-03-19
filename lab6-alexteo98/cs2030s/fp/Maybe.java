package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @author Alex Teo (Lab 16A)
 */

public abstract class Maybe<T> { 

  protected abstract T get();

  public static <S> Maybe<S> of(S item) { 
    if (item == null) { 
      return none();
    } else  { 
      return some(item);
    }
  }

  public static <S> Some<S> some(S item) { 
    return new Some<S>(item);
  }

  public static <S> Maybe<S> none() { 
    @SuppressWarnings("unchecked")
    Maybe<S> temp = (Maybe<S>) NONE;
    return temp;
  }

  public abstract <U extends T> T orElse(U u);

  public abstract <U extends T> T orElseGet(Producer<U> p);

  public <U> Maybe<U> map(Transformer<? super T, ? extends U> t) {  
    /*if (this instanceof None) { 
      return none();
    } else { 
      return new Some<U>(t.transform(this.get()));  
    }*/

    try { 
        return new Some<U>(t.transform(this.get()));
    } catch (NoSuchElementException e) { 
        return none();
    }
  }

  public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> t) {
    if (this instanceof None) { 
      return none();
    } else { 
      // instanceof Some
      if (this.get() == null) { 
        return none();
      } else  { 
        @SuppressWarnings("unchecked")
        Maybe<U> m = (Maybe<U>) t.transform(this.get());
        return m;
      }
    }
  }

  private static final Maybe<Object> NONE = new None();

  public Maybe<T> filter(BooleanCondition<? super T> bc) { 
    Maybe<T> temp = none();  
    if (this instanceof None) { 
      return temp;
    } else  { 
      if (this.get() == null) { 
        return this;
      } else if (bc.test(this.get())) { 
        return this;
      } else  { 
        return temp;
      }
    }
  }

  private static final class None extends Maybe<Object> { 

    @Override
    protected Object get() throws NoSuchElementException { 
      throw new NoSuchElementException();
    }

    public None() { }

    @Override
    public <U> U orElse(U u) { 
      return u;
    }

    @Override
    public <U> U orElseGet(Producer<U> p) { 
      return p.produce();
    }

    public boolean equals(Maybe<Object> compareTo) { 
      return compareTo instanceof None;
    }

    @Override
    public String toString() { 
      return "[]";
    }
  }

  private static final class Some<T> extends Maybe<T> { 

    private T item;

    public Some(T t) { 
      this.item = t;
    }

    @Override
    protected T get() { 
      return this.item;
    }

    @Override
    public <U extends T> T orElse(U u) { 
      return this.get();
    }

    @Override
    public <U extends T> T orElseGet(Producer<U> p) { 
      return this.get();
    }

    @Override
    public boolean equals(Object compareTo) { 
      if (compareTo instanceof Some) { 
        @SuppressWarnings("unchecked")
        Some<T> s = (Some<T>) compareTo;  
        if (this.get() == null) { 
          return this.get() == s.get();
        } else { 
          return this.get().equals(s.get());
        }
      } else { 
        return false;
      }
    }

    @Override
    public String toString() { 
      return String.format("[%s]", this.item);
    }
  }
}
