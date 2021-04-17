package cs2030s.fp;
import cs2030s.fp.Producer;
import cs2030s.fp.Transformer;

public class SuccessTry<T> extends Try<T> { 
  private T value;  
  public SuccessTry(T value) { 
    this.value = value;
  }

  public T get() { 
    return this.value;
  }

  @Override
  public boolean equals(Object o) { 
    if (o instanceof SuccessTry) { 
      @SuppressWarnings("unchecked")
      SuccessTry<T> compareTo = (SuccessTry<T>) o;  
      if (this.value == null) { 
        return this.value == compareTo.value;
      } else { 
        return this.value.equals(compareTo.value);
      }
    } else  { 
      return false;
    }
  }

  @Override
  public <R> Try<R> map(Transformer<? super T, ? extends R> transformer) { 
      try { 
          return new SuccessTry<>(transformer.transform(this.value));
      } catch (Throwable t) { 
          return new FailTry<R>(t);
      }
  }

  @Override
  public <R> Try<R> flatmap(Transformer<? super T, ? extends Try<? extends R>> transformer) { 
      try { 
          return new SuccessTry<R>(transformer.transform(this.value).get());
      } catch (Throwable t) { 
          return new FailTry<R>(t);
      }
  }

  @Override
  public Try<T> recover(Producer<? extends T> p) { 
      return new SuccessTry<>(this.value);
  }
}
