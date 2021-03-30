package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InfiniteList<T> {

  private final Lazy<Maybe<T>> head;
  private final Lazy<InfiniteList<T>> tail;

  InfiniteList() { 
    head = null; 
    tail = null;
  }

  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    Producer<InfiniteList<T>> p = () -> InfiniteList.<T>generate(producer);

    return new InfiniteList<>(Lazy.of(() -> Maybe.some(producer.produce())), Lazy.of(p));
  }

  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    Lazy<Maybe<T>> first = Lazy.of(Maybe.some(seed));
    Producer<InfiniteList<T>> p = () -> InfiniteList.iterate(next.transform(seed), next);
    Lazy<InfiniteList<T>> rest = Lazy.of(p);
    return new InfiniteList<>(first, rest);
  }

  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(() -> Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  public T head() { 
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  public InfiniteList<T> tail() {

    if (this.head.get() == Maybe.none()) { 
        return this.tail.get().tail();
    } else { 
       return this.tail.get();
    }
 }

  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {    
   return new InfiniteList<R>(Lazy.of(() -> this.head.get().map(mapper)),
        Lazy.of(() -> this.tail.get().map(mapper)));
   
  }

  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
   if (this.head.get().filter(predicate) == Maybe.none()) { 
        return new InfiniteList<T>(Lazy.of(() -> Maybe.none()),
            Lazy.of(() -> this.tail.get().filter(predicate)));
    } else { 
    return new InfiniteList<T>(Lazy.of(() -> this.head.get()),
        Lazy.of(() -> this.tail.get().filter(predicate)));
    }
  }

  public static <T> InfiniteList<T> empty() {
    return new EmptyList<T>();
  }

  public InfiniteList<T> limit(long n) {
    if (n <= 0) { 
        return empty();
    } else { 
        return new InfiniteList<T>(this.head(),() -> this.tail().limit(n-1));
    }
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
 
    Producer<InfiniteList<T>> p = () ->  { 
      System.out.println("Testing:" + this.head().toString());  
      if (predicate.test(this.head())) { 
            return this.tail().takeWhile(predicate);
        } else { 
            return empty();
        }
    };

    if (predicate.test(this.head())) { 
//        return new InfiniteList<T>(Lazy.of(() -> this.head.get().filter(predicate)),
//        Lazy.of(p));
          return new InfiniteList<T>(this.head(), () -> this.tail().takeWhile(predicate));
    } else { 
        return empty();
    }
  }

  public boolean isEmpty() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
//    if (this.tail().isEmpty()) { 
//        return identity;
//    } else { 
        return this.tail().reduce(accumulator.combine(identity, this.head()), accumulator);
//    }
  }

  public long count() {
    return 1 + this.tail().count();
  }

  public List<T> toList() {
    List<T> ls = new ArrayList<>();
    ls.add(this.head());
    ls.addAll(this.tail().toList());
    return ls;
  }

  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  public static class EmptyList<T> extends InfiniteList<T> { 
      private EmptyList() {}

      @Override
      public T head() throws NoSuchElementException { 
          throw new NoSuchElementException();
      }

      @Override
      public InfiniteList<T> filter(BooleanCondition<? super T> predicate) { 
          return empty();
      }

      @Override
      public boolean isEmpty() { 
          return true;
      }

      @Override
      public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) { 
          return empty();
      }

      @Override
      public InfiniteList<T> limit(long n) { 
          return empty();
      }

      @Override
      public List<T> toList() { 
          return new ArrayList<T>();
      }

      @Override
      public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate)  { 
          return empty();
      }

      @Override
      public long count() { 
          return 0;
      }

      @Override
      public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
          return identity;
      }

 }
}
