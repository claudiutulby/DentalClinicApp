package Tests;

import Domain.Entitate;
import Repository.BinaryFileRepo;
import Repository.RepoException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryFileRepoTest {
    private final String fileName = "binary.bin";
    private BinaryFileRepo<Entitate> repo;
    BinaryFileRepo<Entitate> invalidRepo;

    @BeforeEach
    public void setUp() throws RepoException, IOException {
        repo = new BinaryFileRepo<>(fileName);
        repo.delall();
    }

    @Test
    public void testAdd() throws RepoException, IOException {
        Entitate entity = new Entitate(1);
        repo.add(entity);

        ArrayList<Entitate> entities = repo.get();
        assertTrue(entities.contains(entity));
    }

    @Test
    public void testDelete() throws RepoException, IOException {
        Entitate entity = new Entitate(3); // Initialize with appropriate data
        repo.add(entity);

        repo.del(3);

        ArrayList<Entitate> entities = repo.get();
        assertFalse(entities.contains(entity));
    }

    @Test
    public void testDeleteAll() throws RepoException, IOException {
        Entitate entity1 = new Entitate(1);
        Entitate entity2 = new Entitate(2);
        repo.add(entity1);
        repo.add(entity2);

        repo.delall();

        ArrayList<Entitate> entities = repo.get();
        assertEquals(0, entities.size());
    }

    @Test
    public void exceptie() {
        assertThrows(RuntimeException.class, () -> {
            invalidRepo = new BinaryFileRepo<>("DC:\\invalidFileName.bin");
        });
    }
}
