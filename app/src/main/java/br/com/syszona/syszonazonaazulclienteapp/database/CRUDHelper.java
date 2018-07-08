package br.com.syszona.syszonazonaazulclienteapp.database;

import java.util.List;

public interface CRUDHelper<T> {
    //reflection java
    public void create(T obj);
    public T findById(int id);
    public List<T> findAll();
    public int update(T obj);
    public void delete(T obj);
}
