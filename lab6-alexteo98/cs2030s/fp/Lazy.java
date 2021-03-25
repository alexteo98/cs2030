package cs2030s.fp;

/**
 * A wrapper around a Maybe object that might contain 
 * an item or might not. Does not evaluate the Producer until called upon.
 *
 * @author Alex Teo (Lab 16A)
 * @version CS2030S AY 20/21 Sem2
 */
public class Lazy<T> {

  /** A producer of type T to produce the element. */
  private Producer<T> producer;

  /** The wrapped item Maybe that might or might not contain an item. */
  private Maybe<T> value = Maybe.none();

  /** 
   * Factory method to create the Lazy object using an item of type T.
   *
   * @param <T> The type of item to be wrapped.
   * @param v Item of type t to be wrapped with a Maybe object.
   * @return A Lazy object wrapped around a Maybe object of the parameter.
   */
  public static final <T> Lazy<T> of(T v) {  
    return new Lazy<T>(v);
  }

  /**
   * Private Constructor for Lazy Object.
   * Wraps given argument in a Maybe object.
   *
   * @param v The item to be wrapped.
   */
  private Lazy(T v) { 
    this.value = Maybe.of(v);
  }

  /**
   * Factory method to create the Lazy object using an item of type T
   * using a producer of type T.
   *
   * @param <T> The type of item to be wrapped.
   * @param v Producer of type T to be evaluated later.
   * @return A Lazy object with the producer stored.. 
   */
  public static final <T> Lazy<T> of(Producer<T> v) { 
    return new Lazy<T>(v);
  }

  /**
   * Private Contructor for Lazy Object.
   * Stores the given producer to be evaluated later.
   *
   * @param v The producer to be stored and evaluated later.
   */
  private Lazy(Producer<T> v) { 
    this.producer = v;
  }

  /**
   * Returns the stored value.
   * Checks if there is a value cached in the Maybe Object.
   * Evaluates the producer if it is empty and caches it.
   *
   * @return The value stored or evaluated.
   */
  public T get() { 
    this.value = Maybe.some(this.value.<T>orElseGet(this.producer));
    return this.value.get();
  }

  /**
   * Maps the current value to a new value using a Transformer.
   * The function is stored as a producer and is only evaluated when called by get().
   *
   * @param <U> The type that the transformer transforms this to.
   * @param t The Transformer function to be applied to the value.
   * @return A Lazy object that stores the evaluation.
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> t) {  
    Producer<U> p = () -> t.transform(this.get());
    return Lazy.<U>of(p);
  }

  /**
   * Maps the current value to a new value using a Transformer.
   * The function is stored as a producer and is only evaluated when called by get().
   * Instead of returning a value wrapped around twice, returns the producer wrapped only once.
   *
   * @param <U> The type that the transformer transforms this to.
   * @param t The Transformer function to be applied to the value.
   * @return A Lazy object that stores the evaluation but only wraps it once.
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> t) { 
    Producer<U> p = () -> t.transform(this.get()).get();  
    return Lazy.<U>of(p);
  }

  /**
   * Filters the contents of this.
   * Stores the operation in a Lazy boolean object but only evaluates it when called upon.
   *
   * @param bc BooleanCondition to test the contents of this against.
   * @return Lazy of type boolean of the result of the test.
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> bc) { 
    Producer<Boolean> p = () -> bc.test(this.get());
    return Lazy.<Boolean>of(p);
  }

  /**
   * Combines this with another instance of Lazy. Given Lazy(y), 
   * stores an operation f(x, y) in a producer and wraps it with an Lazy Object.
   *
   * @param <S> The type of the other Lazy object.
   * @param <R> The type to be returned after calling f(x, y).
   * @param lazy The other lazy object to combime with.
   * @param cmb Combiner to combine the two Lazy objects with.
   * @return A Lazy Object wrapped around the producer of f(x, y).
   */
  public <S, R> Lazy<R> combine(Lazy<? extends S> lazy, 
      Combiner<? super T, ? super S, ? extends R> cmb) { 
    Producer<R> p = () -> cmb.combine(this.get(), lazy.get());
    return Lazy.<R>of(p);
  }

  /**
   * Comapares this item with the argument given.
   *
   * @param o An object to compare this item to.
   * @return True if the items are equal according to their equals representation, False otherwise.
   */
  @Override
  public boolean equals(Object o) { 
    if (o instanceof Lazy) { 
      // safe to Suppress Warning as it is of type Lazy and Object is the  most general type.
      @SuppressWarnings("unchecked")
      Lazy<Object> lazy = (Lazy<Object>) o;
      return this.get().equals(lazy.get());
    } else  { 
      return false;
    }
  }

  /**
   * Return the string representation of the item inside.
   *
   * @return The string representation of the item inside.
   */
  @Override
  public String toString() {  
    return this.value.map(String::valueOf).orElse("?");
  }
}
