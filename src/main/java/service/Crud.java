package service;

import java.util.List;

public interface Crud<E> {
    void create(E e);
    List<E> display();
    void update(E e);
    void delete(int id);

}
