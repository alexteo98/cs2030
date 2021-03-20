package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @author Alex Teo (Lab 16A)
 */

public abstract class Maybe<T> { 

  /**
   * Abstract method to get the element inside of its subtypes Some and None.
   *
   * @return Element T inside of Some.
   * @throws NoSuchElementException When called on instance of None.
   */
  protected abstract T get();

  /**
   * Factory method to create an item wrapped around Maybe.
   *
   * @return None Object if given Null, Some Object otherwise.
   */
  public static <S> Maybe<S> of(S item) { 
    if (item == null) { 
      return none();
    } else  { 
      return some(item);
    }
  }

  /**
   * Calls the constructor of Some with type S.
   *
   * @param item of type S to be wrapped with an instance of Some.
   * @return An instance of Some wrapped around an object of type S.
   */
  public static <S> Some<S> some(S item) { 
    return new Some<S>(item);
  }


  /**
   * Returns a static reference to a None Object casted to Maybe of type S
   *
   * @return None object casted to type Maybe<S>.
   */
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
    /*if (this instanceof None) { 
      return none();
      } else { 
    // instanceof Some
    }*/

    try { 
      if (this.get() == null) { 
        return none();
      } else  { 
        @SuppressWarnings("unchecked")
        Maybe<U> m = (Maybe<U>) t.transform(this.get());
        return m;
      }

    } catch(NoSuchElementException e) { 
      return none();
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

  public static final class None extends Maybe<Object> { 

    /**
     * @throws NoSuchElementException As None does not have an element inside.
     */
    @Override
    protected Object get() throws NoSuchElementException { 
      throw new NoSuchElementException();
    }

    private None() { }

    /**
     * Does nothing and returns the element U given as parameter.
     * 
     * @param u The element to return.
     * @return u The element returned.
     */
    @Override
    public <U> U orElse(U u) { 
      return u;
    }

    /**
     * Does nothing and returns the element produced by the producer given as the parameter.
     *
     * @param p The Producer of type U that will be executed.
     * @return p The element of type U produced by the producer.
     */
    @Override
    public <U> U orElseGet(Producer<U> p) { 
      return p.produce();
    }

    /**
     * Compares if this is equal to the Maybe object given.
     *
     * @param compareTo the Maybe Object to be compared to.
     * @return True if compareTo is a None object, False otherwise.
     */
    public boolean equals(Maybe<Object> compareTo) { 
      return compareTo instanceof None;
    }

    @Override
    public String toString() { 
      return "[]";
    }
  }

  public static final class Some<T> extends Maybe<T> { 

    private T item;

    private Some(T t) { 
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
