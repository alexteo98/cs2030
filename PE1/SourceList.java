/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author A0000000X
 */
interface SourceList<T> {
  T getFirst();
  SourceList<T> getRest();
 
  int length();
  SourceList<T> filter(BooleanCondition<? super T> predicate);
  <R> SourceList<R> map(Transformer<? super T, ? extends R> tr);
}
