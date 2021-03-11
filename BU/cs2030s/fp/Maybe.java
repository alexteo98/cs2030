package cs2030s.fp;

/**
 * CS2030S Lab 5
 * AY20/21 Semester 2
 *
 * @author Alex Teo (Lab 16A)
 */

public abstract class Maybe<T>  { 
  static class None extends Maybe { 

    public None() { 
      itemInside=null;
    }

    @Override
    public String toString() { 
      return "[]";
    }

   // @Override
    public Boolean equals(Maybe m) { 
      if (m instanceof None) { 
        return true;
      } else  { 
        return false;
      }
    }
  }
  static class Some<T> extends Maybe<T> { 

    public Some(T item) { 
      itemInside=item;
    }

    @Override
    public String toString() { 
      return String.format("[%s]", itemInside);
    }

  //  @Override
    public Boolean equals(Maybe m) { 
      if (m instanceof Some) { 
        @SuppressWarnings("unchecked")
        Some s = (Some<T>) m;
        return itemInside==s.itemInside;
      } else  { 
          return false;
      }
    }
  }

  protected T itemInside;
  public static final Maybe<?> NONE = new None();

//  public Boolean equals();
  public static <S> Maybe<S> of(S item) { 
    if (item == null) { 
      Maybe M = (Maybe) NONE;
      return M;
    } else  { 
      return new Some(item);
    }
  }
  public Maybe none() { 
      return NONE;
  }
}
