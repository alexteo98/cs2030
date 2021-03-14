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
    if (item==null) { 
      return none();
    } else  { 
      return some(item);
    }
  }
  public static <S> Some<S> some(S item) { 
    return new Some(item);
  }

  public static <S> Maybe<S> none() { 
    return (Maybe<S>) NONE;
  }

  public abstract <U extends T> T orElse(U u);
  public abstract <U extends T> T orElseGet(Producer<U> p);

  public <U> Maybe<U> map(Transformer<? super T, ? extends U> t) {  
    if (this instanceof None) { 
      return none();
    } else { 
      return new Some<U>(t.transform(this.get()));  
    }
  }

  public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> t) { 
    try{
      if (this.get()==null) { 
        return none();
      }
      Maybe<?> transformed = t.transform(this.get());
      if (transformed instanceof None) { 
        return none();
      } else { 
        // instance of Some 
        Some<U> s = (Some<U>) transformed;
        if (s.get() instanceof None) { 
          return none();
        } else if (s.get() instanceof Some) { 
          return (Some<U>)s.get();
        } else  { 
          return new Some<U> (s.get());
        }
      }
    } catch (NoSuchElementException e) { 
      return none();
    }
  }


  private static final Maybe<?> NONE = new None();

  public Maybe<T> filter(BooleanCondition<? super T> bc) { 
    Maybe<T> temp=(Maybe<T>) none();  
    if (this instanceof None) { 
      return temp;
    } else  { 
      if (this.get()==null) { 
        return this;
      } else if (bc.test(this.get())) { 
        return this;
      } else  { 
        return temp;
      }
    }
  }

  public static final class None extends Maybe<Object> { 

    private Object item;

    @Override
    protected Object get() throws NoSuchElementException { 
      throw new NoSuchElementException();
    }

    public None() { 
      this.item =  null;
    }

    @Override
    public <U> U orElse(U u) { 
      return u;
    }

    @Override
    public <U> U orElseGet(Producer<U> p) { 
      return p.produce();
    }

    public boolean equals(Maybe compareTo) { 
      return compareTo instanceof None;
    }

    @Override
    public String toString() { 
      return "[]";
    }
  }

  public static final class Some<T> extends Maybe<T> { 
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
        Some<T> s = (Some<T>) compareTo;  

        if (this.get()==null){ 
          return this.get()==s.get();
        } else { 
          return this.get().equals(s.get());
        }
      } else  { 
        return false;
      }
    }

    @Override
    public String toString() { 
      return String.format("[%s]", this.item);
    }
  }
}
