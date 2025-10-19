package ma.projet.test;

import ma.projet.beans.*;
import ma.projet.service.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class DemoEtatCivilApp {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fr = new SimpleDateFormat("dd/MM/yyyy", new Locale("fr"));

        HommeService hs = new HommeService();
        FemmeService fs = new FemmeService();
        MariageService ms = new MariageService();

        List<Femme> femmes = new ArrayList<>();
        femmes.add(new Femme("ALAMI", "KARIMA"));
        femmes.add(new Femme("RAMI", "SALIMA"));
        femmes.add(new Femme("ALI", "AMAL"));
        femmes.add(new Femme("ALAOUI", "WAFA"));
        femmes.add(new Femme("SAFI", "SARA"));
        femmes.add(new Femme("NASSER", "HIBA"));
        femmes.add(new Femme("KHADI", "LINA"));
        femmes.add(new Femme("OMARI", "NAWAL"));
        femmes.add(new Femme("FARIS", "SOUKAINA"));
        femmes.add(new Femme("ZAKI", "ILHAM"));
        for (Femme f : femmes) fs.create(f);

        List<Homme> hommes = new ArrayList<>();
        hommes.add(new Homme("SAFI", "SAID"));
        hommes.add(new Homme("BENALI", "YASSINE"));
        hommes.add(new Homme("TAHRI", "OMAR"));
        hommes.add(new Homme("RAFIQ", "ANAS"));
        hommes.add(new Homme("MANSOUR", "ADIL"));
        for (Homme h : hommes) hs.create(h);

        Homme said = hommes.get(0);

        ms.create(new Mariage(said, femmes.get(0), df.parse("1989-09-03"), df.parse("1990-09-03"), 0));
        ms.create(new Mariage(said, femmes.get(1), df.parse("1990-09-03"), null, 4));
        ms.create(new Mariage(said, femmes.get(2), df.parse("1995-09-03"), null, 2));
        ms.create(new Mariage(said, femmes.get(3), df.parse("2000-11-04"), null, 3));

        ms.create(new Mariage(hommes.get(1), femmes.get(4), df.parse("2010-01-01"), null, 1));
        ms.create(new Mariage(hommes.get(2), femmes.get(5), df.parse("2012-02-02"), null, 2));
        ms.create(new Mariage(hommes.get(2), femmes.get(6), df.parse("2013-03-03"), null, 0));
        ms.create(new Mariage(hommes.get(2), femmes.get(7), df.parse("2014-04-04"), null, 1));
        ms.create(new Mariage(hommes.get(2), femmes.get(8), df.parse("2015-05-05"), null, 2));

        System.out.println("Liste des femmes :");
        for (Femme f : fs.findAll()) System.out.println("- " + f.getNom() + " " + f.getPrenom());

        Femme plusAgee = fs.findAll().get(0);
        System.out.println("\nFemme la plus âgée (par défaut ici) : " + plusAgee.getNom() + " " + plusAgee.getPrenom());

        System.out.println("\nÉpouses de " + said.getNom() + " " + said.getPrenom() + " entre 1990 et 2001 :");
        for (Femme f : hs.epousesEntreDates(said.getId(), df.parse("1990-01-01"), df.parse("2001-01-01")))
            System.out.println("- " + f.getNom() + " " + f.getPrenom());

        System.out.println("\nNbr enfants de " + femmes.get(1).getNom() + " " + femmes.get(1).getPrenom() + " entre 1980 et 2020 : "
                + fs.nombreEnfantsEntre(femmes.get(1).getId(), df.parse("1980-01-01"), df.parse("2020-12-31")));

        System.out.println("\nFemmes mariées au moins deux fois :");
        for (Femme f : fs.marieeDeuxFoisOuPlus()) System.out.println("- " + f.getNom() + " " + f.getPrenom());

        System.out.println("\nHommes mariés à quatre femmes entre 2012 et 2016 : " +
                hs.hommesMarieAQuatreFemmesEntre(df.parse("2012-01-01"), df.parse("2016-12-31")));

        System.out.println("\nNom : " + said.getNom() + " " + said.getPrenom());
        System.out.println("Mariages En Cours :");
        int i=1;
        for (Mariage m : hs.mariagesDetailHomme(said.getId())) {
            if (m.getDateFin()==null)
                System.out.println(i++ + ". Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom()
                        + "   Date Début : " + fr.format(m.getDateDebut()) + "    Nbr Enfants : " + m.getNbrEnfant());
        }
        System.out.println("\nMariages échoués :");
        i=1;
        for (Mariage m : hs.mariagesDetailHomme(said.getId())) {
            if (m.getDateFin()!=null)
                System.out.println(i++ + ". Femme : " + m.getFemme().getNom() + " " + m.getFemme().getPrenom()
                        + "   Date Début : " + fr.format(m.getDateDebut())
                        + "    Date Fin : " + fr.format(m.getDateFin())
                        + "    Nbr Enfants : " + m.getNbrEnfant());
        }
    }
}
