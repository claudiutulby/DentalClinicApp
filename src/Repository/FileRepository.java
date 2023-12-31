package Repository;

import Domain.Entitate;
import Domain.IEntitateFactory;

import java.io.*;
import java.util.Scanner;

public class FileRepository<T extends Entitate> extends MemoryRepo<T> {
    private File fisier;
    private IEntitateFactory<T> factory;
    public FileRepository(String filename, IEntitateFactory<T> factory) throws Exception {
        super();
        this.fisier = new File(filename);
        this.factory = factory;
        try {
            load();
        } catch (IOException ex) {
            throw new Exception("Eroare la deschidere fisierului: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void add(T entitate) throws RepoException, IOException {
        super.add(entitate);
        save();
    }

    @Override
    public void del(int id) throws RepoException, IOException {
        super.del(id);
        save();
    }

    public void load() throws FileNotFoundException, IOException, RepoException {
        Scanner scanner = new Scanner(fisier);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            T entitate = factory.toEntity(line);
            add(entitate);
        }
        scanner.close();
    }

    public void save() throws IOException {
        FileWriter fileWriter = new FileWriter(fisier);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (T entitate : this.entitati)
            printWriter.println(entitate.toString());

        printWriter.close();
    }
}
