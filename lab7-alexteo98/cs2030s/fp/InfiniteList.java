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
    return new InfiniteList<>(Lazy.of(() -> this.head.get().filter(predicate)), 
        Lazy.of (() -> this.tail.get().filter(predicate)));
  }

  public static <T> InfiniteList<T> empty() {
    return new EmptyList<T>();
  }

  public InfiniteList<T> limit(long n) {
    if (n <= 0) { 
      return empty();
    } else { 
      if (this.head.get() == Maybe.none()) { 
        return this.tail.get().limit(n);
      } else { 
        return new InfiniteList<T>(this.head, Lazy.of(() -> this.tail.get().limit(n-1)));
      }
    }
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {

    Lazy<Boolean> result = Lazy.of(() -> this.head()).filter(predicate);
    Lazy<Maybe<T>> h = Lazy.of(() -> result.get() ? Maybe.some(this.head()) : Maybe.none());
    Lazy<InfiniteList<T>> t = Lazy.of(() -> result.get() ?
        this.tail().takeWhile(predicate) : empty());

    return new InfiniteList(h, t);
  }

  public boolean isEmpty() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    return this.tail().reduce(accumulator.combine(identity, this.head()), accumulator);
  }

  public long count() {
    if (this.head.get() == Maybe.none()) { 
      return this.tail.get().count();
    } else { 
      return 1 + this.tail.get().count();
    }
  }

  public List<T> toList() {
    List<T> ls = new ArrayList<>();
    if (this.head.get() == Maybe.none()) {  }
    else { 
      ls.add(this.head());
    }
    ls.addAll(this.tail.get().toList());
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
    public InfiniteList<T> tail() { 
      return empty();
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
