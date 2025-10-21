package ma.projet.test;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import ma.projet.util.HibernateUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;

public class TestGestionMariages {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(HibernateUtil.class);

        FemmeService serviceFemme = applicationContext.getBean(FemmeService.class);
        HommeService serviceHomme = applicationContext.getBean(HommeService.class);
        MariageService serviceUnion = applicationContext.getBean(MariageService.class);

        System.out.println("========== Enregistrement des femmes ==========");

        Femme femme1 = new Femme();
        femme1.setCin("CIN001");
        femme1.setNom("Bennani");
        femme1.setPrenom("Latifa");
        femme1.setDateNaissance(LocalDate.of(1963, 5, 18));

        Femme femme2 = new Femme();
        femme2.setCin("CIN002");
        femme2.setNom("Chakir");
        femme2.setPrenom("Zineb");
        femme2.setDateNaissance(LocalDate.of(1972, 2, 22));

        Femme femme3 = new Femme();
        femme3.setCin("CIN003");
        femme3.setNom("Idrissi");
        femme3.setPrenom("Hanane");
        femme3.setDateNaissance(LocalDate.of(1978, 8, 14));

        Femme femme4 = new Femme();
        femme4.setCin("CIN004");
        femme4.setNom("Tazi");
        femme4.setPrenom("Rachida");
        femme4.setDateNaissance(LocalDate.of(1966, 12, 7));

        Femme femme5 = new Femme();
        femme5.setCin("CIN005");
        femme5.setNom("Fassi");
        femme5.setPrenom("Nadia");
        femme5.setDateNaissance(LocalDate.of(1974, 4, 25));

        Femme femme6 = new Femme();
        femme6.setCin("CIN006");
        femme6.setNom("Mansouri");
        femme6.setPrenom("Amina");
        femme6.setDateNaissance(LocalDate.of(1969, 10, 30));

        Arrays.asList(femme1, femme2, femme3, femme4, femme5, femme6).forEach(serviceFemme::create);

        System.out.println("========== Enregistrement des hommes ==========");

        Homme homme1 = new Homme();
        homme1.setNom("Alaoui");
        homme1.setPrenom("Hassan");

        Homme homme2 = new Homme();
        homme2.setNom("Benjelloun");
        homme2.setPrenom("Youssef");

        Homme homme3 = new Homme();
        homme3.setNom("Zaari");
        homme3.setPrenom("Mehdi");

        Homme homme4 = new Homme();
        homme4.setNom("Sefiani");
        homme4.setPrenom("Khalid");

        Arrays.asList(homme1, homme2, homme3, homme4).forEach(serviceHomme::create);

        System.out.println("========== Enregistrement des mariages ==========");

        Mariage union1 = new Mariage();
        union1.setHomme(homme1);
        union1.setFemme(femme1);
        union1.setDateDebut(LocalDate.of(1988, 6, 15));
        union1.setNbrEnfant(3);

        Mariage union2 = new Mariage();
        union2.setHomme(homme1);
        union2.setFemme(femme2);
        union2.setDateDebut(LocalDate.of(1993, 3, 20));
        union2.setNbrEnfant(2);

        Mariage union3 = new Mariage();
        union3.setHomme(homme1);
        union3.setFemme(femme3);
        union3.setDateDebut(LocalDate.of(1998, 12, 10));
        union3.setNbrEnfant(4);

        Mariage union4 = new Mariage();
        union4.setHomme(homme1);
        union4.setFemme(femme4);
        union4.setDateDebut(LocalDate.of(1987, 1, 5));
        union4.setDateFin(LocalDate.of(1988, 6, 14));
        union4.setNbrEnfant(0);

        Mariage union5 = new Mariage();
        union5.setHomme(homme2);
        union5.setFemme(femme1);
        union5.setDateDebut(LocalDate.of(1990, 8, 12));
        union5.setNbrEnfant(2);

        Mariage union6 = new Mariage();
        union6.setHomme(homme2);
        union6.setFemme(femme2);
        union6.setDateDebut(LocalDate.of(1994, 11, 8));
        union6.setNbrEnfant(3);

        Mariage union7 = new Mariage();
        union7.setHomme(homme2);
        union7.setFemme(femme5);
        union7.setDateDebut(LocalDate.of(1999, 5, 18));
        union7.setNbrEnfant(1);

        Mariage union8 = new Mariage();
        union8.setHomme(homme2);
        union8.setFemme(femme6);
        union8.setDateDebut(LocalDate.of(2002, 9, 22));
        union8.setNbrEnfant(2);

        Mariage union9 = new Mariage();
        union9.setHomme(homme3);
        union9.setFemme(femme3);
        union9.setDateDebut(LocalDate.of(2008, 4, 5));
        union9.setNbrEnfant(2);

        Mariage union10 = new Mariage();
        union10.setHomme(homme3);
        union10.setFemme(femme4);
        union10.setDateDebut(LocalDate.of(2011, 7, 30));
        union10.setNbrEnfant(1);

        Mariage union11 = new Mariage();
        union11.setHomme(homme4);
        union11.setFemme(femme5);
        union11.setDateDebut(LocalDate.of(2005, 2, 14));
        union11.setNbrEnfant(0);

        Arrays.asList(union1, union2, union3, union4, union5, union6, union7, union8, union9, union10, union11)
                .forEach(serviceUnion::create);

        System.out.println("\n================================================");
        System.out.println("         EXECUTION DES REQUETES");
        System.out.println("================================================\n");

        System.out.println(">>> Affichage : Toutes les femmes enregistrees");
        serviceFemme.findAll().forEach(f -> System.out.printf("CIN: %-8s | Nom: %-20s | Prenom: %-20s | Naissance: %-12s%n",
                f.getCin(), f.getNom(), f.getPrenom(), f.getDateNaissance()));

        Femme femmeLaPlusAgee = serviceFemme.findAll().stream()
                .min((fA, fB) -> fA.getDateNaissance().compareTo(fB.getDateNaissance()))
                .orElse(null);
        System.out.println("\n>>> Resultat : Femme ayant la date de naissance la plus ancienne");
        if (femmeLaPlusAgee != null) {
            System.out.printf("Identite : %s %s %s (Nee le : %s)%n",
                    femmeLaPlusAgee.getCin(),
                    femmeLaPlusAgee.getNom(),
                    femmeLaPlusAgee.getPrenom(),
                    femmeLaPlusAgee.getDateNaissance());
        }

        System.out.println("\n>>> Consultation : Epouses de M. " + homme1.getNom() + " " + homme1.getPrenom() + " sur periode donnee");
        serviceHomme.afficherEpousesEntreDates(homme1.getId(), LocalDate.of(1985, 1, 1), LocalDate.of(2020, 12, 31));

        System.out.println("\n>>> Calcul : Nombre total d'enfants de Mme " + femme1.getNom() + " sur periode definie");
        int totalEnfants = serviceFemme.nombreEnfantsEntreDates(femme1.getId(),
                LocalDate.of(1985, 1, 1), LocalDate.of(2015, 12, 31));
        System.out.println("Total enfants : " + totalEnfants);

        System.out.println("\n>>> Liste : Femmes ayant contracte au minimum deux mariages");
        serviceFemme.femmesMarieesAuMoinsDeuxFois().forEach(f ->
                System.out.printf("CIN: %-8s | Identite: %-20s %-20s%n",
                        f.getCin(), f.getNom(), f.getPrenom()));

        System.out.println("\n>>> Statistique : Hommes maries a exactement quatre femmes (periode 1985-2020)");
        serviceHomme.afficherNombreHommesAvecQuatreFemmes(
                LocalDate.of(1985, 1, 1), LocalDate.of(2020, 12, 31));

        System.out.println("\n>>> Details : Ensemble des mariages de M. " + homme1.getNom() + " " + homme1.getPrenom());
        serviceUnion.afficherMariagesAvecDetails(homme1.getId());

        applicationContext.close();
    }
}
