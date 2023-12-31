package Service;

import Domain.Pacient;
import Repository.IRepository;
import Repository.RepoException;

import java.io.IOException;
import java.util.ArrayList;

public class PacientiService {
    private IRepository<Pacient> pacienti;

    public PacientiService(IRepository<Pacient> pacienti) {
        this.pacienti = pacienti;
    }

    public void add(int id, String nume, String prenume, int varsta) throws RepoException, IOException {
        Pacient pacient = new Pacient(id, nume, prenume, varsta);
        pacienti.add(pacient);
    }

    public void del(int id) throws RepoException, IOException {
        pacienti.del(id);
    }

    public Pacient getbyID(int id) {
        return this.pacienti.getbyID(id);
    }

    public ArrayList<Pacient> get() {
        return pacienti.get();
    }

    public void delall() throws RepoException, IOException {
        pacienti.delall();
    }
}