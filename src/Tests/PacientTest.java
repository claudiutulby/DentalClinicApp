package Tests;

import Domain.Pacient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PacientTest {
    private Pacient pacient;

    @BeforeEach
    public void setUp() {
        pacient = new Pacient(1, "Doe", "John", 30);
    }

    @Test
    public void testGetNume() {
        assertEquals("Doe", pacient.getNume());
    }

    @Test
    public void testGetPrenume() {
        assertEquals("John", pacient.getPrenume());
    }

    @Test
    public void testGetVarsta() {
        assertEquals(30, pacient.getVarsta());
    }

    @Test
    public void testToString() {
        assertEquals("1 Doe John 30", pacient.toString());
    }

    @Test
    public void testSetNume() {
        pacient.setNume("Smith");
        assertEquals("Smith", pacient.getNume());
    }

    @Test
    public void testSetPrenume() {
        pacient.setPrenume("Jane");
        assertEquals("Jane", pacient.getPrenume());
    }

    @Test
    public void testSetVarsta() {
        pacient.setVarsta(40);
        assertEquals(40, pacient.getVarsta());
    }
}
