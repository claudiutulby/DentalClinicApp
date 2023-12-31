import Domain.*;
import Repository.*;
import Service.PacientiService;
import Service.ProgramariService;
import UI.CLI;
import UI.GUI;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    private static IRepository<Pacient> PacientRepository() {
        Properties properties = new Properties();
        try(InputStream is = new FileInputStream("src/settings.properties"))
        {
            properties.load(is);

            if(properties.getProperty("PatientsRepository").equals("text")) {
                return new FileRepository<Pacient>("src/pacienti.txt", new PacientFactory<Pacient>());
            }

            else if(properties.getProperty("PatientsRepository").equals("binary"))
                return new BinaryFileRepo<>("src/pacienti.bin");
            else if(properties.getProperty("PatientsRepository").equals("runtime")) {
                Pacient pac1 = new Pacient(1, "Saligny", "Anghel", 23);
                Pacient pac2 = new Pacient(2, "Tulbure", "Claudiu", 21);
                Pacient pac3 = new Pacient(3, "Popescu", "Ion", 69);
                Pacient pac4 = new Pacient(4, "Buzoian", "Bozoian", 39);
                Pacient pac5 = new Pacient(5, "Cobai", "Șîmore", 12);

                IRepository<Pacient> repo = new MemoryRepo<>();

                try {
                    repo.add(pac1);
                    repo.add(pac2);
                    repo.add(pac3);
                    repo.add(pac4);
                    repo.add(pac5);
                } catch (RepoException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Avertizare", JOptionPane.WARNING_MESSAGE);
                }

                return repo;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }

        return new MemoryRepo<>();
    }

    public static IRepository<Programare> ProgramareRepository() {
        Properties properties = new Properties();
        try(InputStream is = new FileInputStream("src/settings.properties")) {
            properties.load(is);
            if (properties.getProperty("AppointmentsRepository").equals("binary"))
                return new BinaryFileRepo<>("src/appointments.bin");
            else if (properties.getProperty("AppointmentsRepository").equals("runtime")) {
                return new MemoryRepo<>();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new MemoryRepo<>();
    }

    public static void main(String[] args) {
        IRepository<Pacient> pacienti = PacientRepository();
        IRepository<Programare> programari = ProgramareRepository();

        PacientiService pacserv = new PacientiService(pacienti);
        ProgramariService progserv = new ProgramariService(programari);

        // new GUI(pacserv, progserv);

        new CLI(pacserv, progserv);
    }
}
