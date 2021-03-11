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

  private static final Maybe<?> NONE = new None();


  public static final class None extends Maybe<Object> { 

    private Object item;

    public None() { 
      this.item =  null;
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
    public boolean equals(Object compareTo) { 
      if (compareTo instanceof Some) { 
        Some<T> s = (Some<T>) compareTo;  

        if (this.item==null){ 
          System.out.println("1");
          return this.item==s.item;
        } else { 
          return this.item.equals(s.item);
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
