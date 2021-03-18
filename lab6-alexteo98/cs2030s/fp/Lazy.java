package cs2030s.fp;

public class Lazy<T> {
  private Producer<T> producer;
  private Maybe<T> value;

  public static final <T> Lazy<T> of(T v) { 
    return new Lazy<T> (v);
  }

  private Lazy(T v) { 
    this.value = Maybe.of(v);
  }

  public static final <T> Lazy<T> of (Producer<T> v) { 
    return new Lazy<T> (v);
  }

  private Lazy(Producer<T> v) { 
    this.producer = v;
  }

  public T get() { 
    if (value == null) { 
        value = Maybe.of(producer.produce());
        return value.orElse(null);
    } else { 
        return value.orElse(null);
    }
  }

  public Lazy<T> map(Transformer<? super T, ? extends T> t) { 
      return Lazy.<T>of()
  }

  @Override
  public String toString() {  
    if (this.value == null) { 
        return "?";
    } else { 
        return String.format("%s", value.orElse(null));
    }
  }
}
