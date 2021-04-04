package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class implements a Lazy Infinite List.
 *
 * The elements of the list are lazy, and will only be evaluated when called upon.
 * The list is recursively defined, with a special tail "EmptyList" to denote the end of the list.
 *
 * CS2030S Lab7
 * AY20/21 Semester 2
 */
public class InfiniteList<T> {

  /** The head of the List.*/
  private final Lazy<Maybe<T>> head;

  /** The recursively defined tail of the list. */
  private final Lazy<InfiniteList<T>> tail;

  /**
   * Private Constructor of Infinite List.
   */
  InfiniteList() { 
    head = null; 
    tail = null;
  }

  /**
   * Method to generate a list with a function.
   *
   * @param <T> The type of Infinite List to create.
   * @param producer The function to generate the next element of the list.
   *
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    Producer<InfiniteList<T>> p = () -> InfiniteList.<T>generate(producer);
    return new InfiniteList<>(Lazy.of(() -> Maybe.some(producer.produce())), Lazy.of(p));
  }

  /**
   * Method to generate a list with a transformer.
   *
   * @param <T> The type of Infinite List to create.
   * @param seed The value of the first element of the list.
   * @param next The transformer to generate the next element of the list.
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    Lazy<Maybe<T>> first = Lazy.of(Maybe.some(seed));
    Producer<InfiniteList<T>> p = () -> InfiniteList.iterate(next.transform(seed), next);
    Lazy<InfiniteList<T>> rest = Lazy.of(p);
    return new InfiniteList<>(first, rest);
  }

  /**
   * Constructor for Infinite List using the first element and a function.
   *
   * @param head The value of the first element of the list.
   * @param tail The function to generate the next element of the list.
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head = Lazy.of(() -> Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  /**
   * Constructor for Infinite List using a Lazy object of type T and
   * a Lazy object of type Infinite List to generate the tail.
   *
   * @param head The Lazy object of the head of the list.
   * @param tail The Lazy object that will generate the rest of the list.
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

  /**
   * Gets the first element of the Infinite List.
   *
   * @return The first element of the list.
   */
  public T head() { 
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  /**
   * Gets the rest of the Infinite List excluding empty elements.
   *
   * @return The tail of the Infinite List.
   */
  public InfiniteList<T> tail() {
    if (this.head.get() == Maybe.none()) { 
      return this.tail.get().tail();
    } else { 
      return this.tail.get();
    }
  }

  /**
   * Maps all elements of the List with a function.
   * Intermediate operation.
   *
   * @param <R> The resulting type of the element after the function.
   * @param mapper The function applied onto elements of the Infinite List.
   *
   * @return The new Infinite List after the map operation.
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {    
    return new InfiniteList<R>(Lazy.of(() -> this.head.get().map(mapper)),
        Lazy.of(() -> this.tail.get().map(mapper)));

  }

  /**
   * Filters the Infinite List with a predicate.
   * Intermediate operation.
   *
   * @param predicate The predicate to filter the elements of the list.
   *
   * @return The new Infinite List with elements that makes the predicate true.
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<>(Lazy.of(() -> this.head.get().filter(predicate)), 
        Lazy.of (() -> this.tail.get().filter(predicate)));
  }

  /**
   * Returns an EmptyList marking the end of the Infinite List.
   *
   * @return The Empty List object.
   */
  public static <T> InfiniteList<T> empty() {
    return new EmptyList<T>();
  }

  /**
   * Truncates the infinite list to a maximum length.
   * Short-circuiting intermediate operation.
   *
   * @param n The upper limit of which the infinite list is allowed.
   *
   * @return The infinite list with a maximum length of n. 
   *  If the infinite list is shorter, it returns unchanged.
   */
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

  /**
   * Truncates the infinite list as soon as the predicates returns false.
   *
   * @param predicate The predicate to test the elements on.
   *
   * @return The new infinite list that will terminate upon encountering an element which makes the predicare false.
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {

    Lazy<Boolean> result = Lazy.of(() -> this.head()).filter(predicate);
    Lazy<Maybe<T>> h = Lazy.of(() -> result.get() ? Maybe.some(this.head()) : Maybe.none());
    Lazy<InfiniteList<T>> t = Lazy.of(() -> result.get() ?
        this.tail().takeWhile(predicate) : empty());

    return new InfiniteList(h, t);
  }

  /**
   * Returns whether the infinite list is empty or not.
   *
   * @return always return false.
   */
  public boolean isEmpty() {
    return false;
  }


  /**
   * Reduces the infinite list into a value  using a binary function.
   * An initial value is given and the binary function is applied on the
   * initial value and the head of the infinite list until the infinite list terminates.
   * Terminal operation.
   *
   * @param <U> The type of the result after applying the binary function.
   * @param identity The initial value the binary function is applied on with the head,
   * @param accumulator The binary function.
   *
   * @return The final value the infinite list is reduced to.
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    return this.tail().reduce(accumulator.combine(identity, this.head()), accumulator);
  }

  /**
   * Returns the number of elements in the infinte list.
   * Terminal operation.
   *
   * @return The total number of elements in the infinite list.
   */
  public long count() {
    if (this.head.get() == Maybe.none()) { 
      return this.tail.get().count();
    } else { 
      return 1 + this.tail.get().count();
    }
  }

  /**
   * Convertes the infinite list into a list.
   * Terminal operation.
   *
   * @return The list containing all elements in the infinite list.
   */
  public List<T> toList() {
    List<T> ls = new ArrayList<>();
    if (this.head.get() == Maybe.none()) {  }
    else { 
      ls.add(this.head());
    }
    ls.addAll(this.tail.get().toList());
    return ls;
  }

  /**
   * Returns the string representation of each element.
   *
   * @return The string representation of all elements of the infinite list.
   */
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
