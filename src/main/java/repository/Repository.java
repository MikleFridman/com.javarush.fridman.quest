package repository;

import java.util.List;

public interface Repository<T> {
    public T findById(long id);
    public List<T> findAll();
}
