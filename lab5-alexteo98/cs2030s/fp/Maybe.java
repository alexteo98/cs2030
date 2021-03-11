package cs2030s.fp;

/**
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @author Alex Teo (Lab 16A)
 */

public abstract class Maybe<T> { 
 
  public static <S> Maybe<S> of(S item) { 
      if (item==null) { 
          return some(item);
      } else  { 
          return none();
      }
  }
  public static <S> Some<S> some(S item) { 
      return new Some(item);
  }

  public static <S> Maybe<S> none() { 
      return (Maybe<S>) NONE;
  }

  private static final Maybe<?> NONE = new None();
  //}

  public static final class None extends Maybe<Object> { 

    private Object item;

    public None() { 
      this.item =  null;
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
  public String toString() { 
    return String.format("[%s]", this.item);
  }
}
}
