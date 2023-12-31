package Domain;

import java.io.Serializable;

public class Programare extends Entitate implements Serializable {
    private Pacient pacient;
    private String data;
    private int ora;
    private String scopulprogramarii;

    public Programare(int id, Pacient pacient, String data, int ora, String scopulprogramarii) {
        super(id);
        this.pacient = pacient;
        this.data = data;
        this.ora = ora;
        this.scopulprogramarii = scopulprogramarii;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }

    public String getScopulprogramarii() {
        return scopulprogramarii;
    }

    public void setScopulprogramarii(String scopulprogramarii) {
        this.scopulprogramarii = scopulprogramarii;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return this.ID + " " + this.pacient.getNume() + " " + this.data + " " + this.ora + " " + this.scopulprogramarii;
    }
}
