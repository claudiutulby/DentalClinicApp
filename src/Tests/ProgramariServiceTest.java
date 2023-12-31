package Tests;

import Domain.Pacient;
import Domain.Programare;
import Repository.IRepository;
import Repository.MemoryRepo;
import Service.ProgramariService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProgramariServiceTest {
    private ProgramariService programariService;
    private IRepository<Programare> repo;

    @BeforeEach
    public void setUp() {
        repo = new MemoryRepo<>();
        programariService = new ProgramariService(repo);
    }

    @Test
    public void test() throws Exception {
        // Verificam add-ul
        Pacient pacient = new Pacient(1, "Florea", "Florin", 23);
        programariService.add(1, pacient, "2023/11/25", 10, "Consultatie");

        Programare programare = programariService.getbyID(1);
        assert(programariService.get().contains(programare));
        assertEquals(pacient, programare.getPacient());
        assertEquals("2023/11/25", programare.getData());
        assertEquals(10, programare.getOra());
        assertEquals("Consultatie", programare.getScopulprogramarii());

        // Verificam metoda delall
        programariService.delall();
        assertFalse(programariService.get().contains(programare));

        // Verificam metoda del
        programariService.add(1, pacient, "2023/11/25", 10, "Consultatie");
        programariService.del(1);
        assertFalse(programariService.get().contains(programare));

        // Verificam daca se arunca exceptie atunci cand vrem sa facem 2 progrmari in acelasi timp
        programariService.add(1, pacient, "2023/11/25", 10, "Consultatie");
        assertThrows(Exception.class, () -> {
            programariService.add(2, pacient, "2023/11/25", 10, "Consultatie");
        });

        // Verificam daca se arunca exceptie atunci cand punem o ora gresita
        assertThrows(Exception.class, () -> {
            programariService.add(1, pacient, "2024/11/25", 25, "Consultatie");
        });

        // Verificam metoda suprapunere
        assert(programariService.suprapunere("12/12/2012", 12) == programariService.suprapunere("12/12/2012", 12));
    }
}
