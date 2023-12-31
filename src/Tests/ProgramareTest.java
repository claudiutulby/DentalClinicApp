package Tests;

import Domain.Pacient;
import Domain.Programare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProgramareTest {
    private Programare programare;
    private Pacient pacient;

    @BeforeEach
    public void setUp() {
        pacient = new Pacient(1, "Popescu", "Ion", 35);
        programare = new Programare(1, pacient, "27/11/2023", 15, "Extragere măsea de minte");
    }

    @Test
    public void testGetPacient() {
        assertNotNull(programare.getPacient());
        assertEquals("Popescu", programare.getPacient().getNume());
        assertEquals(35, programare.getPacient().getVarsta());
    }

    @Test
    public void testGetOra() {
        assertEquals(15, programare.getOra());
    }

    @Test
    public void testGetScopulProgramarii() {
        assertEquals("Extragere măsea de minte", programare.getScopulprogramarii());
    }

    @Test
    public void testGetData() {
        assertEquals("27/11/2023", programare.getData());
    }

    @Test
    public void testSetPacient() {
        Pacient newPacient = new Pacient(2, "Ionescu", "Ana", 30);
        programare.setPacient(newPacient);
        assertEquals(newPacient, programare.getPacient());
    }

    @Test
    public void testSetOra() {
        programare.setOra(14);
        assertEquals(14, programare.getOra());
    }

    @Test
    public void testSetScopulProgramarii() {
        programare.setScopulprogramarii("Tratament carie");
        assertEquals("Tratament carie", programare.getScopulprogramarii());
    }

    @Test
    public void testSetData() {
        programare.setData("28/11/2023");
        assertEquals("28/11/2023", programare.getData());
    }

    @Test
    public void testToString() {
        String expectedString = "1 Popescu 27/11/2023 15 Extragere măsea de minte";
        assertEquals(expectedString, programare.toString());
    }
}
