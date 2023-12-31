package Tests;

import Domain.Entitate;
import Repository.MemoryRepo;
import Repository.RepoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class MemoryRepoTest {
    private MemoryRepo<Entitate> memoryRepo;

    @BeforeEach
    void setUp() {
        memoryRepo = new MemoryRepo<>();
    }

    @Test
    void testAdd() throws RepoException, IOException {
        Entitate entitate = new Entitate(1);
        memoryRepo.add(entitate);
        assertTrue(memoryRepo.get().contains(entitate));
        assertThrows(RepoException.class, () -> {
            memoryRepo.add(entitate);
        });
    }

    @Test
    void testDelete() throws RepoException, IOException {
        Entitate entitate = new Entitate(1);

        memoryRepo.add(entitate);
        assertTrue(memoryRepo.exista(1));
        memoryRepo.del(1);

        assertFalse(memoryRepo.exista(1));

        assertThrows(RepoException.class, () -> {
            memoryRepo.del(1);
        });
    }

    @Test
    void testGetByID() throws RepoException, IOException {
        Entitate entitate = new Entitate(1);

        memoryRepo.add(entitate);

        assertEquals(entitate, memoryRepo.getbyID(1));
        assertNull(memoryRepo.getbyID(2));
    }

    @Test
    void testGetAll() throws RepoException, IOException {
        Entitate entitate1 = new Entitate(1);

        Entitate entitate2 = new Entitate(2);

        memoryRepo.add(entitate1);
        memoryRepo.add(entitate2);

        assertEquals(2, memoryRepo.get().size());
    }

    @Test
    void testDeleteAll() throws RepoException, IOException {
        Entitate entitate1 = new Entitate(1);

        Entitate entitate2 = new Entitate(2);

        memoryRepo.add(entitate1);
        memoryRepo.add(entitate2);

        memoryRepo.delall();

        assertTrue(memoryRepo.get().isEmpty());
    }
}