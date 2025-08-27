package filter;

public interface Filter<T, R> {
    R process(T input);
}