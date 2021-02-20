class Box<T>  { 
  private final T itemInside;
  public static final Box<?> EMPTY_BOX = new Box(null);

  private Box(T item) { 
    this.itemInside = item;
  }

  public static <S> Box<S> of(S item) {
    if (item == null) { 
      return null;
    } else {
      return (Box<S>)new Box<S>(item);
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
    Box<T> temp = new Box(this.itemInside);
    if (temp.equals(EMPTY_BOX)) { 
      return false;
    } else { 
      return true;
    }
  }

  public static <S> Box<S> empty() { 
    // Null Box can be casted into any box.
    @SuppressWarnings("unchecked")
    Box<S> temp = (Box<S>) EMPTY_BOX;
    return temp;
  }

  @Override
  public boolean equals(Object item) { 
    if (item instanceof Box) { 
      Box<T> comparator = (Box<T>) item;
      if (this.itemInside == null) { 
        return this.itemInside == comparator.itemInside;
      } else{
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
