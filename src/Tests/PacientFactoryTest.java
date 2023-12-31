package Tests;

import Domain.Pacient;
import Domain.PacientFactory;
import Domain.IEntitateFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PacientFactoryTest {
    @Test
    public void testToEntity() {
        // Arrange
        String line = "1 Saligny Anghel 23";
        IEntitateFactory<Pacient> factory = new PacientFactory<>();

        // Act
        Pacient pacient = factory.toEntity(line);

        // Assert
        assertEquals(1, pacient.getID());
        assertEquals("Saligny", pacient.getNume());
        assertEquals("Anghel", pacient.getPrenume());
        assertEquals(23, pacient.getVarsta());
    }
}
