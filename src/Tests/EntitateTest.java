package Tests;

import Domain.Entitate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntitateTest {

    private Entitate entitate;

    @BeforeEach
    public void setUp() {
        entitate = new Entitate(10);
    }

    @Test
    public void test() {
        entitate.setID(20);
        assert entitate.getID() == 20;
    }
}