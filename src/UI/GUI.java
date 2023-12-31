package UI;

import Domain.Pacient;
import Domain.Programare;
import Service.PacientiService;
import Service.ProgramariService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private PacientiService pacienti;
    private ProgramariService programari;
    private DefaultTableModel pacientiTableModel;
    private DefaultTableModel programariTableModel;
    private JFrame mainFrame;

    public GUI(PacientiService pacserv, ProgramariService proserv) {
        this.pacienti = pacserv;
        this.programari = proserv;
        pacientiTableModel = new DefaultTableModel(new Object[]{"ID", "Nume", "Prenume", "Vârstă"}, 0);
        programariTableModel = new DefaultTableModel(new Object[]{"ID", "Numele pacientului", "Data", "Ora", "Scop"}, 0);
        for (Pacient pacient : this.pacienti.get())
            pacientiTableModel.addRow(new Object[]{pacient.getID(), pacient.getNume(), pacient.getPrenume(), pacient.getVarsta()});
        for (Programare programare : this.programari.get())
            programariTableModel.addRow(new Object[]{programare.getID(), programare.getPacient().getNume() + ' ' + programare.getPacient().getPrenume(), programare.getData(), programare.getOra(), programare.getScopulprogramarii()});
        mainFrame();
    }

    private void mainFrame() {
        mainFrame = new JFrame("Cabinet stomatologic");
        mainFrame.setBounds(400, 150, 500, 200);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3));

        JButton vizualizarePacienti = new JButton("Vizualizare pacienți");
        vizualizarePacienti.setFocusable(false);
        vizualizarePacienti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPacientiFrame();
            }
        });

        JButton vizualizareProgramari = new JButton("Vizualizare programări");
        vizualizareProgramari.setFocusable(false);
        vizualizareProgramari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showProgramariFrame();
            }
        });

        JButton adaugaPacient = new JButton("Adaugă pacient");
        adaugaPacient.setFocusable(false);
        adaugaPacient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPacient();
            }
        });

        JButton stergePacient = new JButton("Șterge pacient");
        stergePacient.setFocusable(false);
        stergePacient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stergePacient();
            }
        });

        JButton adaugaProgramare = new JButton("Adaugă programare");
        adaugaProgramare.setFocusable(false);
        adaugaProgramare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProgramare();
            }
        });

        JButton stergeProgramare = new JButton("Șterge programare");
        stergeProgramare.setFocusable(false);
        stergeProgramare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stergeProgramare();
            }
        });

        panel.add(vizualizarePacienti);
        panel.add(vizualizareProgramari);
        panel.add(adaugaPacient);
        panel.add(stergePacient);
        panel.add(adaugaProgramare);
        panel.add(stergeProgramare);

        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    private void showPacientiFrame() {
        JFrame pacientiFrame = new JFrame("Pacienți");
        pacientiFrame.setBounds(400, 150, 400, 300);
        pacientiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTable pacientiTable = new JTable(pacientiTableModel);
        JScrollPane scrollPane = new JScrollPane(pacientiTable);

        pacientiFrame.add(scrollPane);
        pacientiFrame.setVisible(true);
    }

    private void showProgramariFrame() {
        JFrame programariFrame = new JFrame("Programări");
        programariFrame.setBounds(400, 150, 400, 300);
        programariFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTable programariTable = new JTable(programariTableModel);
        JScrollPane scrollPane = new JScrollPane(programariTable);

        programariFrame.add(scrollPane);
        programariFrame.setVisible(true);
    }

    private void addPacient() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Introduceți ID-ul: "));
        String nume = JOptionPane.showInputDialog("Introduceți numele: ");
        String prenume = JOptionPane.showInputDialog("Introduceți preumele: ");
        int varsta = Integer.parseInt(JOptionPane.showInputDialog("Introduceți vârsta: "));
        try {
            this.pacienti.add(id, nume, prenume, varsta);
            pacientiTableModel.addRow(new Object[]{id, nume, prenume, varsta});
        }
        catch (Exception exceptie) {
            JOptionPane.showMessageDialog(null, exceptie.getMessage(), "Avertizare", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void stergePacient() {
        String input = JOptionPane.showInputDialog("Introduceți ID-ul pacientului:");
        if (input != null) {
            Integer id = Integer.parseInt(input);
            try {
                this.pacienti.del(id);
                for (int row = 0; row < pacientiTableModel.getRowCount(); row++)
                    if (pacientiTableModel.getValueAt(row, 0) == id)
                        pacientiTableModel.removeRow(row);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Avertizare", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void addProgramare() {
        int id = Integer.parseInt(JOptionPane.showInputDialog("Introduceți ID-ul"));
        int idpacient = Integer.parseInt(JOptionPane.showInputDialog("Introduceți ID-ul pacientului: "));
        String data = JOptionPane.showInputDialog("Introduceți data progrmării: ");
        int ora = Integer.parseInt(JOptionPane.showInputDialog("Introduceți ora programării: "));
        String scopul = JOptionPane.showInputDialog("Scopul programării: ");
        try {
            Pacient pacient = this.pacienti.getbyID(idpacient);
            if (pacient != null) {
                this.programari.add(id, pacient, data, ora, scopul);
                programariTableModel.addRow(new Object[]{id, pacient.getNume() + ' ' + pacient.getPrenume(), data, ora, scopul});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Avertizare", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void stergeProgramare() {
        String input = JOptionPane.showInputDialog("Introduceți ID-ul programării:");
        if (input != null) {
            Integer id = Integer.parseInt(input);
            try {
                this.programari.del(id);
                for (int row = 0; row < programariTableModel.getRowCount(); row++)
                    if (programariTableModel.getValueAt(row, 0) == id)
                        programariTableModel.removeRow(row);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Avertizare", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
