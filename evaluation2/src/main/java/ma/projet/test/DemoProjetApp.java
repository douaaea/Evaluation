package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DemoProjetApp {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fr = new SimpleDateFormat("dd MMMM yyyy", new Locale("fr"));

        EmployeService es = new EmployeService();
        ProjetService prs = new ProjetService();
        TacheService ts = new TacheService();
        EmployeTacheService ets = new EmployeTacheService();

        // 1) Employé chef de projet
        Employe chef = new Employe("Dupont", "Amal", "0600000000");
        es.create(chef);

        // 2) Projet
        Projet p = new Projet("Gestion de stock", df.parse("2013-01-14"), df.parse("2013-06-30"), chef);
        prs.create(p);

        // 3) Tâches planifiées
        Tache t1 = new Tache("Analyse",       df.parse("2013-02-01"), df.parse("2013-02-28"), 1500, p);
        Tache t2 = new Tache("Conception",    df.parse("2013-03-01"), df.parse("2013-03-20"), 1200, p);
        Tache t3 = new Tache("Développement", df.parse("2013-04-01"), df.parse("2013-05-10"), 8000, p);
        ts.create(t1); ts.create(t2); ts.create(t3);

        // 4) Réalisations (dates réelles)
        ets.create(new EmployeTache(chef, t1, df.parse("2013-02-10"), df.parse("2013-02-20")));
        ets.create(new EmployeTache(chef, t2, df.parse("2013-03-10"), df.parse("2013-03-15")));
        ets.create(new EmployeTache(chef, t3, df.parse("2013-04-10"), df.parse("2013-04-25")));

        // === Affichages demandés ===

        // A) EmployeService: tâches réalisées par l'employé
        System.out.println("\nTâches réalisées par " + chef.getNom() + " :");
        for (EmployeTache et : es.tachesRealiseesParEmploye(chef.getId())) {
            System.out.println("- " + et.getTache().getNom() + " (" + fr.format(et.getDateDebutReelle()) + " → " + fr.format(et.getDateFinReelle()) + ")");
        }

        // B) EmployeService: projets gérés par l'employé
        System.out.println("\nProjets gérés par " + chef.getNom() + " :");
        es.projetsGeresParEmploye(chef.getId()).forEach(x -> System.out.println("- " + x.getNom()));

        // C) ProjetService: tâches planifiées du projet
        System.out.println("\nTâches planifiées pour le projet " + p.getNom() + " :");
        for (Tache t : prs.tachesPlanifiees(p.getId())) {
            System.out.println("- " + t.getNom() + " (" + fr.format(t.getDateDebut()) + " → " + fr.format(t.getDateFin()) + ")");
        }

        // D) ProjetService: tâches réalisées avec dates réelles
        System.out.println("\nProjet : " + p.getId() + "      Nom : " + p.getNom() + "     Date début : " + fr.format(p.getDateDebut()));
        System.out.println("Liste des tâches:");
        System.out.println("Num Nom            Date Début Réelle   Date Fin Réelle");
        for (EmployeTache et : prs.tachesRealiseesAvecDates(p.getId())) {
            String num = String.valueOf(et.getTache().getId());
            String nom = et.getTache().getNom();
            String ddr = fr.format(et.getDateDebutReelle());
            String dfrS = fr.format(et.getDateFinReelle());
            System.out.printf("%-3s %-14s %-18s %s%n", num, nom, ddr, dfrS);
        }

        // E) TacheService: prix > 1000 (requête nommée)
        System.out.println("\nTâches avec prix > 1000 DH :");
        for (Tache t : ts.prixSup1000()) {
            System.out.println("- " + t.getNom() + " : " + (int)t.getPrix() + " DH");
        }

        // F) TacheService: tâches réalisées entre deux dates
        Date d1 = df.parse("2013-03-01");
        Date d2 = df.parse("2013-04-30");
        System.out.println("\nTâches réalisées entre " + fr.format(d1) + " et " + fr.format(d2) + " :");
        for (EmployeTache et : ts.realiseesEntre(d1, d2)) {
            System.out.println("- " + et.getTache().getNom() + " (" + fr.format(et.getDateDebutReelle()) + " → " + fr.format(et.getDateFinReelle()) + ")");
        }
    }
}
