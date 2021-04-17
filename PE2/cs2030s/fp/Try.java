package cs2030s.fp;

import cs2030s.fp.Producer;
import cs2030s.fp.Transformer;

public abstract class Try<T> { 
    public static <S> Try<S> of(Producer<S> p) { 
       try { 
          return new SuccessTry<>(p.produce());
       } catch (Throwable t){ 
          return new FailTry<>(t);
       }
    }

    public static <S> Try<S> success(S s) { 
        return new SuccessTry<>(s);
    }

    public static <S> Try<S> failure(Throwable t) { 
        return new FailTry<>(t);
    }

    public abstract T get() throws Throwable;
    public abstract boolean equals(Object o);
    public abstract <R> Try<R> map(Transformer<? super T, ? extends R> transformer);
    public abstract <R> Try<R> flatmap(Transformer<? super T, ? extends Try<? extends R>> transformer);
    public abstract Try<T> recover(Producer<? extends T> p);
}
