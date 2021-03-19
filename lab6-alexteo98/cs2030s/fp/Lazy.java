package cs2030s.fp;

public class Lazy<T> {
  private Producer<T> producer;
  private Maybe<T> value = null;

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
      value = Maybe.of(this.producer.produce());
      return value.orElse(null);
    } else { 
      return value.orElse(null);
    }
  }

  public <U> Lazy<U> map(Transformer<? super T,? extends U> t) { 
    Producer<U> p = () -> t.transform(this.get());
    return Lazy.<U>of(p);
  }

  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> t) { 
    Producer<U> p = () -> t.transform(this.get()).get();  
    return Lazy.<U>of(p);
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
