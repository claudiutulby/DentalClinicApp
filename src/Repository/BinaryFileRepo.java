package Repository;

import Domain.Entitate;

import java.io.*;
import java.util.ArrayList;

public class BinaryFileRepo<T extends Entitate> extends MemoryRepo<T> {
    private String fileName;

    public BinaryFileRepo(String fileName) {
        super();
        this.fileName = fileName;
        try {
             loadFile();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(T o) throws RepoException, IOException {
        super.add(o);
        saveFile();
    }

    @Override
    public void del(int id) throws RepoException, IOException {
        super.del(id);
        saveFile();
    }

    @Override
    public void delall() throws RepoException, IOException {
        super.delall();
        saveFile();
    }

    private void loadFile() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        this.entitati = (ArrayList<T>) ois.readObject();
    }

    private void saveFile() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this.entitati);
        }
    }
}
