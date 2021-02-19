class Box<T>  { 
    private final T itemInside;
    public static <S> Box<S super T> EMPTY_BOX = new Box<S> (null);

    private Box(T item) { 
        this.itemInside = item;
    }

    public static <S> Box of(S item) {
      if (item == null) { 
          return null;
      } else {
        return new Box(item);
      }
    }

    public static <S> Box ofNullable(S item) {
      if (item == null) { 
          return null;
      } else {
        return empty();
      }
    }
    public static <S> Box empty() { 
        return (Box<T>) EMPTY_BOX;
    }

    @Override
    public boolean equals(Object item) { 
        if (item instanceof Box) { 
            Box<T> comparator = (Box<T>) item;
            return this.itemInside.equals(comparator.itemInside);
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
