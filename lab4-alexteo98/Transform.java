interface Transformer<T, U super T> { 
    public abstract U(T t) { 
        return (U)t;
    }
}
