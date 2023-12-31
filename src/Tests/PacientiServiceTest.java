package Tests;

import Domain.Pacient;
import Repository.IRepository;
import Repository.MemoryRepo;
import Repository.RepoException;
import Service.PacientiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class PacientiServiceTest {
    private PacientiService pacientiService;
    private IRepository<Pacient> pacientiRepo;

    @BeforeEach
    public void setUp() {
        pacientiRepo = new MemoryRepo<>();
        pacientiService = new PacientiService(pacientiRepo);
    }

    @Test
    public void testAddPacient() throws RepoException, IOException {
        pacientiService.add(1, "John", "Doe", 30);
        Pacient pacient = pacientiService.getbyID(1);
        assertNotNull(pacient);
        assertEquals("John", pacient.getNume());
    }

    @Test
    public void testDeletePacient() throws RepoException, IOException {
        pacientiService.add(2, "Jane", "Smith", 25);
        pacientiService.del(2);
        Pacient deletedPacient = pacientiService.getbyID(2);
        assertNull(deletedPacient);
        pacientiService.add(2, "Jane", "Smith", 25);
        pacientiService.delall();
        ArrayList<Pacient> listagoala = new ArrayList<>();
        assert(Objects.equals(pacientiService.get(), listagoala));
    }
}
