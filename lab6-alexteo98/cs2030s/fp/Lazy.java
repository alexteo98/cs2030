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

  public Lazy<Boolean> filter(BooleanCondition<? super T> bc) { 
    Producer<Boolean> p = () -> bc.test(this.get());
    return Lazy.<Boolean>of(p);
  }

  public <S, R> Lazy<R> combine(Lazy<? extends S> lazy, Combiner<? super T, ? super S, ? extends R> cmb) { 
    Producer<R> p = () -> cmb.combine(this.get(), lazy.get());
    return Lazy.<R>of(p);
  }

  @Override
  public boolean equals(Object o) { 
    if (o instanceof Lazy) { 
      // safe to Suppress Warning as it is of type Lazy and Object is the  most general type.
      @SuppressWarnings("unchecked")
      Lazy<Object> lazy = (Lazy<Object>) o;
      return this.get().equals(lazy.get());
    } else  { 
      return false;
    }
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
