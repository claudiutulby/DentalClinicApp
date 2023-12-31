package Repository;

import Domain.Entitate;

import java.io.IOException;
import java.util.ArrayList;

public interface IRepository<T extends Entitate> {
    void add(T entitate) throws RepoException, IOException;
    void del(int id) throws RepoException, IOException;
    T getbyID(int id);
    ArrayList<T> get();
    boolean exista(int id);

    void delall() throws RepoException, IOException;
}
