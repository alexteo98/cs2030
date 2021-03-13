package cs2030s.fp;

/**
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @author Alex Teo (Lab 16A)
 */

public abstract class Maybe<T> { 

  protected T item1;
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
      return new Some<U>(t.transform(item1));  
    }
  }

  public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> t) { 
    if (item1==null) { 
        return none();
    }
    Maybe<?> transformed = t.transform(item1);
    if (transformed instanceof None) { 
      return none();
    } else { 
      // instance of Some 
      Some<U> s = (Some<U>) transformed;
      if (s.item1 instanceof None) { 
          return none();
      } else if (s.item1 instanceof Some) { 
          return (Some<U>)s.item1;
      } else  { 
          return new Some<U> (s.item1);
      }
    }
  }


  private static final Maybe<?> NONE = new None();

  public Maybe<T> filter(BooleanCondition<? super T> bc) { 
    Maybe<T> temp=(Maybe<T>) none();  
    if (this instanceof None) { 
      return temp;
    } else  { 
      if (item1==null) { 
        return this;
      } else if (bc.test(item1)) { 
        return this;
      } else  { 
        return temp;
      }
    }
  }

  public static final class None extends Maybe<Object> { 

    private Object item;

    public None() { 
      item1 =  null;
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
      item1 = t;
    }

    @Override
    public <U extends T> T orElse(U u) { 
      return item1;
    }

    @Override
    public <U extends T> T orElseGet(Producer<U> p) { 
      return item1;
    }

    @Override
    public boolean equals(Object compareTo) { 
      if (compareTo instanceof Some) { 
        Some<T> s = (Some<T>) compareTo;  

        if (item1==null){ 
          System.out.println("1");
          return item1==s.item1;
        } else { 
          return item1.equals(s.item1);
        }
      } else  { 
        return false;
      }
    }

    @Override
    public String toString() { 
      return String.format("[%s]", item1);
    }
  }
}
