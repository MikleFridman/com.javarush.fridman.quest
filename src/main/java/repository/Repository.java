package repository;

public interface Repository<T> {
    public T findById(long id);
}
