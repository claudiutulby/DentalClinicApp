package UI;

import Domain.Pacient;
import Domain.Programare;
import Repository.RepoException;
import Service.PacientiService;
import Service.ProgramariService;

import java.io.IOException;
import java.util.Scanner;

public class CLI {
    private PacientiService pacienti;
    private ProgramariService programari;
    private static Scanner scanner = new Scanner(System.in);
    public CLI(PacientiService pacienti, ProgramariService programari) {
        this.pacienti = pacienti;
        this.programari = programari;
        System.out.println(" info - pentru a vedea comenzile | win - interfață grafică");
        boolean ok = true;
        while (ok) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            switch (input) {
                case "add patient": {
                    addpac();
                    break;
                }
                case "add appointment": {
                    addprog();
                    break;
                }
                case "show patients": {
                    showpac();
                    break;
                }
                case "show appointments": {
                    showprog();
                    break;
                }
                case "del patient": {
                    delpac();
                    break;
                }
                case "del appointment": {
                    delprog();
                    break;
                }
                case "del all patients": {
                    delallpac();
                    break;
                }
                case "del all appointments": {
                    delallprog();
                    break;
                }
                case "info": {
                    info();
                    break;
                }
                case "win": {
                    new GUI(pacienti, programari);
                    break;
                }
                case "exit": {
                    ok = false;
                }
                default:
                    System.out.println("Comandă incorectă");
            }
        }
    }

    private void info() {
        System.out.println
                ("""
                add patient
                add appointment
                show patients
                show appointments
                del patient
                del appointment
                del all patients
                del all appointments
                exit
                win (interfață grafică)
                """);
    }

    private void delallpac() {
        try {
            pacienti.delall();
        } catch (RepoException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void delallprog() {
        try {
            programari.delall();
        } catch (RepoException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void delprog() {
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            programari.del(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Programarea cu id-ul: " + id + " a fost șters");
    }

    private void delpac() {
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            pacienti.del(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Pacientul cu id-ul: " + id + " a fost șters");
    }

    private void showprog() {
        for (Programare prog : this.programari.get()) {
            System.out.println(prog.toString());
        }
    }

    private void showpac() {
        for (Pacient pacient : this.pacienti.get()) {
            System.out.println(pacient.toString());
        }
    }

    private void addpac() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        System.out.print("Nume: ");
        String nume = scanner.next();
        System.out.print("Prenume: ");
        String prenume = scanner.next();
        System.out.print("Varsta: ");
        int varsta = scanner.nextInt();
        scanner.nextLine();
        try {
            this.pacienti.add(id, nume, prenume, varsta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addprog() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        System.out.print("ID Pacient: ");
        int idpac = scanner.nextInt();
        System.out.print("Data: ");
        String data = scanner.next();
        System.out.print("Ora: ");
        int ora = scanner.nextInt();
        System.out.print("Scopul: ");
        String scopul = scanner.next();
        scanner.nextLine();
        try {
            this.programari.add(id, this.pacienti.getbyID(idpac), data, ora, scopul);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
