package cs2030s.fp;

import java.util.NoSuchElementException;

/**
 * This abstract class implements an object of Maybe, whereby it could contain an element,
 * "Some" or it could not contain en element, "None".
 *
 * <p>CS2030S Lab 5
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
   * @param <S> The type of object to be wrapped.
   * @param item The item of type S to be wrapped.
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
   * @param <S> The type of item to be wrapped.
   * @param item of type S to be wrapped with an instance of Some.
   * @return An instance of Some wrapped around an object of type S.
   */
  public static <S> Some<S> some(S item) { 
    return new Some<S>(item);
  }

  /**
   * Returns a static reference to a None Object casted to Maybe of type S.
   *
   * @param <S> Type of Maybe NONE should be casted to.
   * @return None object casted to type S.
   */
  public static <S> Maybe<S> none() { 
    @SuppressWarnings("unchecked")
    Maybe<S> temp = (Maybe<S>) NONE;
    return temp;
  }

  /**
   * Abstract Method to get element T if it is an instance of Some, returns the parameter otherwise.
   *
   * @param <U> The type to be returned that must be a subtype of T.
   * @param u element which is a subtype of T.
   * @return The element of type T if it is an instance of Some, 
    The parameter of type U if it is an instance of None.
   */
  public abstract <U extends T> T orElse(U u);

  /**
   * Abstract Method to get element T if it is an instance of Some, 
   * returns the element produced by argument otherwise.
   *
   * @param <U> The type to be returned that must be a subtype of T.
   * @param p Producer to produce an element if it is an instance of None.
   * @return The element of type T if it is an instance of Some, 
    otherwise the element of type U produced by parameter p if it is an instance of None.
   */
  public abstract <U extends T> T orElseGet(Producer<U> p);

  /**
   * Changes the current item of type T into type U with a Transformer.
   * Transformer must have a type of ? super T, ? extends U.
   *
   * @param <U> The type of Maybe to be returned after transforming.
   * @param t Transformer to change current element of type T to element of type U.
   * @return An instance of None if used on an instance of None, or
    An instance of Some wrapped around the item transformed by the transformer argument.
   */
  public <U> Maybe<U> map(Transformer<? super T, ? extends U> t) {  
    if (this == NONE) { 
      return none();
    } else {
      return new Some<U>(t.transform(this.get()));
    }
  }

  /**
   * Changes the current item of type T into type U with a Transformer.
   * Transforms the element of type T into type U such that it does not get wrapped twice.
   * Transformer must have a type of (? super T, ? extends Maybe(? extends U)).
   *
   * @param <U> The type if Maybe to be returned after transforming.
   * @param t Transformer to change current element of type T to element of type U.
   * @return An instance of None if used on an instance of None. or
   An instance of Some wrapped around the item transformed by the transformer argument.
   */
  public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> t) { 
    if (this == NONE) { 
      return none();
    } else  { 
      @SuppressWarnings("unchecked")
      Maybe<U> m = (Maybe<U>) t.transform(this.get());
      return m;
    }
  }

  /**
   * Static None object that all None instances refer to.
   */
  private static final Maybe<Object> NONE = new None();

  /**
   * Tests the element inside with the given BooleanCondition.
   *
   * @param bc Boolean Condition Object to test the element with.
   * @return None object if the element fails the test or this is an instance of None, .
   Otherwise the original object if it passes the test.
   */
  public Maybe<T> filter(BooleanCondition<? super T> bc) { 
    Maybe<T> temp = none();  
    if (this == NONE) { 
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


  /**
   * This class implements a None object, whereby it does not contain anything inside.
   */
  public static final class None extends Maybe<Object> { 

    /**
     * Does not retuan an item, throws a NoSuchElementException.
     *
     * @throws NoSuchElementException As None does not have an element inside.
     */
    @Override
    protected Object get() throws NoSuchElementException { 
      throw new NoSuchElementException();
    }

    /**
     * Private constrcutor for None, does nothing.
     */
    private None() {
    }

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

    /**
     * Returns the String representation of None.
     *
     * @return Always return "[]" as None has nothing inside.
     */
    @Override
    public String toString() { 
      return "[]";
    }
  }

  /**
   * This class implements a Some object, whereby it contains an item of type T inside.
   */
  public static final class Some<T> extends Maybe<T> { 

    /** The item of type T that is inside Some. */
    private T item;

    /**
     * Private Constructor of Some. Initialises the item inside with the parameter.
     *
     * @param t Item of type T the be wrapped by the Some object.
     */
    private Some(T t) { 
      this.item = t;
    }

    /**
     * Gets the item inside.
     *
     * @return The item inside the Some object.
     */
    @Override
    protected T get() { 
      return this.item;
    }

    /**
     * Gets the item inside.
     *
     * @param u The item u to be returned if this is a none object.(Not used)
     * @return The item inside the Some object.
     */   
    @Override
    public <U extends T> T orElse(U u) { 
      return this.get();
    }

    /**
     * Gets the item inside.
     *
     * @param p The producer to produce an item of type U if this is a none object.(Not used)
     * @return The item inside the Some object.
     */
    @Override
    public <U extends T> T orElseGet(Producer<U> p) { 
      return this.get();
    }

    /**
     * Compares if the item is equal to this.
     *
     * @param compareTo The item to be compared to.
     * @return True if the item are equal according to their equals implementation, False otherwise.
     */
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

    /**
     * Gets the String representation of the item inside.
     *
     * @return String representation of the item inside.
     */
    @Override
    public String toString() { 
      return String.format("[%s]", this.item);
    }
  }
}
