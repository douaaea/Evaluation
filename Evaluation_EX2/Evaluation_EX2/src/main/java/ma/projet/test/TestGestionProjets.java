package ma.projet.test;

import ma.projet.service.EmployeService;
import ma.projet.service.EmployeTacheService;
import ma.projet.service.ProjetService;
import ma.projet.service.TacheService;
import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.util.HibernateUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class TestGestionProjets {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext(HibernateUtil.class);

        EmployeService employeDao = appContext.getBean(EmployeService.class);
        EmployeTacheService affectationDao = appContext.getBean(EmployeTacheService.class);
        ProjetService projetDao = appContext.getBean(ProjetService.class);
        TacheService tacheDao = appContext.getBean(TacheService.class);

        System.out.println("========== Initialisation des employes ==========");

        Employe employe1 = new Employe();
        employe1.setNom("Alaoui");
        employe1.setPrenom("Karim");
        employeDao.create(employe1);

        Employe employe2 = new Employe();
        employe2.setNom("Benjelloun");
        employe2.setPrenom("Fatima");
        employeDao.create(employe2);

        Employe employe3 = new Employe();
        employe3.setNom("Chakir");
        employe3.setPrenom("Mohamed");
        employeDao.create(employe3);

        System.out.println("========== Creation des projets ==========");

        Projet premierProjet = new Projet();
        premierProjet.setNom("Systeme ERP");
        premierProjet.setChef(employe1);
        premierProjet.setDateDebut(LocalDate.of(2024, 5, 10));
        premierProjet.setDateFin(LocalDate.of(2025, 3, 20));
        projetDao.create(premierProjet);

        Projet secondProjet = new Projet();
        secondProjet.setNom("Application mobile");
        secondProjet.setChef(employe2);
        secondProjet.setDateDebut(LocalDate.of(2024, 7, 5));
        secondProjet.setDateFin(LocalDate.of(2025, 1, 15));
        projetDao.create(secondProjet);

        Projet troisiemeProjet = new Projet();
        troisiemeProjet.setNom("Site web e-commerce");
        troisiemeProjet.setChef(employe3);
        troisiemeProjet.setDateDebut(LocalDate.of(2024, 6, 1));
        troisiemeProjet.setDateFin(LocalDate.of(2024, 12, 30));
        projetDao.create(troisiemeProjet);

        System.out.println("========== Ajout des taches planifiees ==========");

        Tache tachePlanif1 = new Tache();
        tachePlanif1.setNom("Analyse des besoins");
        tachePlanif1.setProjet(premierProjet);
        tachePlanif1.setPrix(650f);
        tachePlanif1.setDateDebut(LocalDate.of(2024, 5, 15));
        tachePlanif1.setDateFin(null);
        tacheDao.create(tachePlanif1);

        Tache tachePlanif2 = new Tache();
        tachePlanif2.setNom("Design interface");
        tachePlanif2.setProjet(secondProjet);
        tachePlanif2.setPrix(920f);
        tachePlanif2.setDateDebut(LocalDate.of(2024, 7, 12));
        tachePlanif2.setDateFin(null);
        tacheDao.create(tachePlanif2);

        Tache tachePlanif3 = new Tache();
        tachePlanif3.setNom("Configuration serveur");
        tachePlanif3.setProjet(troisiemeProjet);
        tachePlanif3.setPrix(480f);
        tachePlanif3.setDateDebut(LocalDate.of(2024, 6, 8));
        tachePlanif3.setDateFin(null);
        tacheDao.create(tachePlanif3);

        System.out.println("========== Ajout des taches terminees ==========");

        Tache tacheComplete1 = new Tache();
        tacheComplete1.setNom("Developpement backend");
        tacheComplete1.setProjet(premierProjet);
        tacheComplete1.setPrix(2200f);
        tacheComplete1.setDateDebut(LocalDate.of(2024, 6, 1));
        tacheComplete1.setDateFin(LocalDate.of(2024, 8, 15));
        tacheDao.create(tacheComplete1);

        Tache tacheComplete2 = new Tache();
        tacheComplete2.setNom("Tests qualite");
        tacheComplete2.setProjet(premierProjet);
        tacheComplete2.setPrix(1350f);
        tacheComplete2.setDateDebut(LocalDate.of(2024, 8, 20));
        tacheComplete2.setDateFin(LocalDate.of(2024, 9, 10));
        tacheDao.create(tacheComplete2);

        Tache tacheComplete3 = new Tache();
        tacheComplete3.setNom("Integration API");
        tacheComplete3.setProjet(secondProjet);
        tacheComplete3.setPrix(1780f);
        tacheComplete3.setDateDebut(LocalDate.of(2024, 8, 5));
        tacheComplete3.setDateFin(LocalDate.of(2024, 9, 25));
        tacheDao.create(tacheComplete3);

        System.out.println("========== Affectation des taches aux employes ==========");

        EmployeTache affectation1 = new EmployeTache();
        affectation1.setEmploye(employe1);
        affectation1.setTache(tachePlanif1);
        affectationDao.create(affectation1);

        EmployeTache affectation2 = new EmployeTache();
        affectation2.setEmploye(employe1);
        affectation2.setTache(tacheComplete1);
        affectationDao.create(affectation2);

        EmployeTache affectation3 = new EmployeTache();
        affectation3.setEmploye(employe2);
        affectation3.setTache(tachePlanif2);
        affectationDao.create(affectation3);

        EmployeTache affectation4 = new EmployeTache();
        affectation4.setEmploye(employe1);
        affectation4.setTache(tacheComplete2);
        affectationDao.create(affectation4);

        EmployeTache affectation5 = new EmployeTache();
        affectation5.setEmploye(employe2);
        affectation5.setTache(tacheComplete3);
        affectationDao.create(affectation5);

        EmployeTache affectation6 = new EmployeTache();
        affectation6.setEmploye(employe3);
        affectation6.setTache(tachePlanif3);
        affectationDao.create(affectation6);

        System.out.println("\n================================================");
        System.out.println("         DEMARRAGE DES TESTS");
        System.out.println("================================================\n");

        System.out.println(">>> Consultation : Taches affectees a l'employe " + employe1.getNom());
        employeDao.afficherTachesParEmploye(employe1.getId());

        System.out.println("\n>>> Consultation : Projets supervises par " + employe1.getNom());
        employeDao.afficherProjetsGeresParEmploye(employe1.getId());

        System.out.println("\n>>> Consultation : Taches prevues pour le projet " + premierProjet.getNom());
        projetDao.afficherTachesPlanifieesParProjet(premierProjet.getId());

        System.out.println("\n>>> Consultation : Taches accomplies du projet " + premierProjet.getNom());
        projetDao.afficherTachesRealiseesParProjet(premierProjet.getId());

        System.out.println("\n>>> Consultation : Taches dont le budget depasse 1000 DH");
        tacheDao.afficherTachesPrixSuperieurA1000();

        System.out.println("\n>>> Consultation : Taches effectuees sur une periode donnee");
        LocalDate periodeDebut = LocalDate.of(2024, 6, 1);
        LocalDate periodeFin = LocalDate.of(2024, 9, 30);
        System.out.println("Periode : du " + periodeDebut + " au " + periodeFin);
        tacheDao.afficherTachesEntreDates(periodeDebut, periodeFin);

        System.out.println("\n================================================");
        System.out.println("         FIN DES TESTS - SUCCES");
        System.out.println("================================================");

        appContext.close();
    }
}
