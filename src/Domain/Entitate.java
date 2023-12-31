package Domain;

import java.io.Serializable;

public class Entitate implements Serializable {
    protected int ID;

    public Entitate(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
