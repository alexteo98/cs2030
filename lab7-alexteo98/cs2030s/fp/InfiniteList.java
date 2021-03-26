package cs2030s.fp;

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
    return new InfiniteList<R>(Lazy.of(() -> this.head.get().map(mapper)), Lazy.of(() -> this.tail.get().map(mapper)));
  }

  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    if (this.head.get().filter(predicate) == Maybe.none()) { 
        return new InfiniteList<T>(Lazy.of(() -> Maybe.none()), Lazy.of(() -> this.tail.get().filter(predicate)));
    } else { 
    return new InfiniteList<T>(Lazy.of(() -> this.head.get()), Lazy.of(() -> this.tail.get().filter(predicate)));

    }
/*    if (this.head.get().filter(predicate) != Maybe.none()) { 
       return new InfiniteList<>(Lazy.of(() -> Maybe.of(this.head())), Lazy.of(() -> this.tail().filter(predicate)));
    } else  { 
        return new InfiniteList<>(Lazy.of(() -> Maybe.none()), Lazy.of(() -> this.tail.get().filter(predicate)));
    }
  */  

//    return new InfiniteList<>(Lazy.of(Lazy.of(this.head.filter(predicate))), Lazy.of(() -> this.tail().filter(predicate)));
  }

  public static <T> InfiniteList<T> empty() {
    // TODO
    return new InfiniteList<>();
  }

  public InfiniteList<T> limit(long n) {
    // TODO
    return new InfiniteList<>();
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    return new InfiniteList<>();
  }

  public boolean isEmpty() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    // TODO
    return null;
  }

  public long count() {
    // TODO
    return 0;
  }

  public List<T> toList() {
    // TODO
    return List.of();
  }

  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
