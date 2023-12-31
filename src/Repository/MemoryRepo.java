package Repository;
import Domain.Entitate;

import java.io.IOException;
import java.util.ArrayList;

public class MemoryRepo<T extends Entitate> implements IRepository<T> {
    protected ArrayList<T> entitati;

    public MemoryRepo() {
        entitati = new ArrayList<>();
    }

    @Override
    public void add(T entitate) throws RepoException, IOException {
        for (T ent : this.entitati)
            if (ent.getID() == entitate.getID())
                throw new RepoException("Există deja entitate cu acest id!");
        entitati.add(entitate);
    }

    @Override
    public void del(int id) throws RepoException, IOException {
        T entitate = getbyID(id);
        if (entitate != null)
            entitati.remove(entitate);
        else throw new RepoException("Nu există vreo entitate cu acest id!");
    }

    @Override
    public T getbyID(int id) {
        for (T entitate : entitati)
            if (entitate.getID() == id)
                return entitate;
        return null;
    }

    @Override
    public ArrayList<T> get() {
        return entitati;
    }

    @Override
    public boolean exista(int id) {
        for (Entitate entitate : entitati)
            if (entitate.getID() == id)
                return true;
        return false;
    }

    public void delall() throws RepoException, IOException {
        while (!entitati.isEmpty())
            entitati.remove(entitati.get(0));
    }
}
