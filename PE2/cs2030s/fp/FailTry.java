package cs2030s.fp;
import cs2030s.fp.Producer;
import cs2030s.fp.Transformer;

public class FailTry<T> extends Try<T> { 
    private Throwable t;

    public FailTry(Throwable t) { 
        this.t = t;
    } 

    public T get() throws Throwable { 
        throw t;
    }

    @Override
    public boolean equals(Object o) { 
        if (o instanceof FailTry) { 
            @SuppressWarnings("unchecked")
            FailTry compareTo = (FailTry) o;
            return this.t.getMessage()==(compareTo.t.getMessage());
        } else  { 
            return false;
        }
    }

    @Override
    public <R> Try<R> map(Transformer<? super T, ? extends R> transformer) { 
        return new FailTry<R>(this.t);
    }

    @Override
    public <R> Try<R> flatmap(Transformer<? super T, ? extends Try<? extends R>> transformer) { 
        return new FailTry<R>(this.t);
    }

    @Override
    public Try<T> recover(Producer<? extends T> p) { 
        return new SuccessTry<>(p.produce());
    }
}
