package Service;

import Domain.Pacient;
import Domain.Programare;
import Repository.IRepository;
import Repository.RepoException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ProgramariService {
    private IRepository<Programare> programari;

    public ProgramariService(IRepository<Programare> programari) {
        this.programari = programari;
    }

    public void add(int id, Pacient pacient, String data, int ora, String scopulprogramarii) throws Exception {
        Programare programare = new Programare(id, pacient, data, ora, scopulprogramarii);
        if (ora > 0 && ora < 24){
            if (!suprapunere(data, ora))
                programari.add(programare);
            else throw new Exception("Se suprapune cu o programare existentă!");
        }
        else throw new Exception("Oră incorectă!");
    }

    public void del(int id) throws Exception {
        programari.del(id);
    }

    public boolean suprapunere(String data, int ora) {
        for (Programare programare : programari.get()) {
            if (Objects.equals(programare.getData(), data) && programare.getOra() == ora)
                return true;
        }
        return false;
    }

    public Programare getbyID(int id) {
        return this.programari.getbyID(id);
    }

    public ArrayList<Programare> get() {
        return programari.get();
    }

    public void delall() throws RepoException, IOException {
        programari.delall();
    }
}