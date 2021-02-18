class Box<T> { 
  private final T item;
  private final Box<? super T> EMPTY_BOX;

  private Box(T item) { 
    this.item = item;
  }

  public static <S> Box<S> of(S item) { 
    if (item != null) { 
      return new Box(item);
    } else { 
      return null;
    }
  }

  public static <S> Box<S> ofNullable(S item) { 
    if (item != null) { 
      return new Box(item);
    } else { 
      return empty();
    }
  } 

  public static <S> Box<S> empty() { 
    return (Box<S>) new Box.of(null);
  }

  public boolean isPresent() { 
    Box compare1 = new Box(this.item);
    Box compare2 = empty();
    if (compare1.equals(compare2)) { 
          return true;
      } else { 
          return false;
      }
  }

  @Override
  public boolean equals(Object item) { 
    if (item instanceof Box) { 
      Box<T> comparator = (Box<T>) item;
      return this.item.equals(comparator.item);
    } else { 
      return false;
    }
  }

  @Override
  public String toString() { 
    return String.format("[%s]", item);
  }
}
