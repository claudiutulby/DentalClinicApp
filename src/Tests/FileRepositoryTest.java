package Tests;

import Domain.Pacient;
import Domain.PacientFactory;
import Repository.FileRepository;
import Repository.RepoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileRepositoryTest {
    String filename = "src/Tests/filerepo.txt";
    FileRepository<Pacient> fileRepo;
    FileRepository<Pacient> fileRepoEx;

    @BeforeEach
    public void setUp() throws Exception {
        fileRepo = new FileRepository<>(filename, new PacientFactory<>());
    }

    @Test
    public void adddel() throws IOException, RepoException {
        fileRepo.delall();
        Pacient pacient = new Pacient(1, "Hatz", "John", 23);
        fileRepo.add(pacient);
        assert fileRepo.get().contains(pacient);
        fileRepo.del(1);
        assertFalse(fileRepo.get().contains(pacient));
        fileRepo.add(pacient);
    }

    @Test
    public void exceptie() {
        assertThrows(Exception.class, () -> {
            fileRepoEx = new FileRepository<>("fisier.txt", new PacientFactory<>());
        });
    }
}
