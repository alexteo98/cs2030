class Box<T> { 

  /**
   * This class implements a Box.
   *
   * @author Alex Teo (Lab16A)
   * @version CS2030S AY20/21 Semester 2
   */

  private final T itemInside;
  public static final Box<?> EMPTY_BOX = new Box<>(null);

  private Box(T item) { 
    this.itemInside = item;
  }

  public static <S> Box<S> of(S item) {
    if (item == null) { 
      return null;
    } else {
      return new Box<S>(item);
    }
  }

  public <U> Box<U> map(Transformer<? super T, U> transform) { 

    if (!isPresent()) {
      Box<U> emptyBox = empty();
      return emptyBox;
    } else { 
      return new Box<U>(transform.transform(this.itemInside));
    }
  }

  public Box<T> filter(BooleanCondition<? super T> condition) { 

    Box<T> temp = empty();

    if (!this.isPresent()) { 
      return temp;
    } else { 
      if (condition.test(itemInside)) { 
        return (Box<T>) this;
      } else { 
        return temp;
      }
    }
  }

  public static <S> Box<S> ofNullable(S item) {
    if (item == null) {
      return empty();
    } else {
      return (Box<S>) new Box<S>(item);
    }
  }

  public boolean isPresent() { 
    Box<T> temp = new Box<T>(this.itemInside);
    if (temp.equals(EMPTY_BOX)) { 
      return false;
    } else { 
      return true;
    }
  }

  public static <S> Box<S> empty() { 
    // An empty box can be casted into any type of box.
    @SuppressWarnings("unchecked")
    Box<S> temp = (Box<S>) EMPTY_BOX;
    return temp;
  }

  @Override
  public boolean equals(Object item) { 
    if (item instanceof Box) { 
      @SuppressWarnings("unchecked")
      Box<T> comparator = (Box<T>) item;
      if (this.itemInside == null) { 
        return this.itemInside == comparator.itemInside;
      } else { 
        return this.itemInside.equals(comparator.itemInside);
      }
    } else { 
      return false;
    }
  }

  @Override
  public String toString() { 
    String str = "";
    if (this.itemInside != null) { 
      str = itemInside.toString();
    }
    return String.format("[%s]", str);
  }
}
