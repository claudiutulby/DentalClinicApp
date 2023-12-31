package Domain;

import Repository.MemoryRepo;

import java.util.ArrayList;

public class PacientFactory<T extends Entitate> implements IEntitateFactory<Pacient> {
    @Override
    public Pacient toEntity(String line) {
        int id = Integer.parseInt(line.split(" ")[0]);
        String nume = line.split(" ")[1];
        String prenume = line.split(" ")[2];
        int varsta = Integer.parseInt(line.split(" ")[3]);

        return new Pacient(id, nume, prenume, varsta);
    }
}
