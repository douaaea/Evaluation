package ma.projet.test;

import ma.projet.classes.*;
import ma.projet.service.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TestApp {
    public static void main(String[] args) throws Exception {
        CategorieService cs = new CategorieService();
        ProduitService ps = new ProduitService();
        CommandeService cmdS = new CommandeService();
        LigneCommandeService lcs = new LigneCommandeService();

        Categorie c1 = new Categorie("C01", "Ordinateurs");
        Categorie c2 = new Categorie("C02", "Écrans");
        cs.create(c1);
        cs.create(c2);

        Produit p1 = new Produit("ES12", 120, c1);
        Produit p2 = new Produit("ZR85", 100, c1);
        Produit p3 = new Produit("EE85", 200, c2);
        ps.create(p1);
        ps.create(p2);
        ps.create(p3);

        Commande cmd = new Commande(new SimpleDateFormat("yyyy-MM-dd").parse("2013-03-14"));
        cmdS.create(cmd);
        lcs.create(new LigneCommandeProduit(p1, cmd, 7));
        lcs.create(new LigneCommandeProduit(p2, cmd, 14));
        lcs.create(new LigneCommandeProduit(p3, cmd, 5));

        System.out.println("Commande : " + cmd.getId() + "    Date : " + cmd.getDate());
        System.out.println("Référence\tPrix\tQuantité");

        for (LigneCommandeProduit l : ps.getByCommande(cmd.getId())) {
            System.out.println(l.getProduit().getReference() + "\t" +
                    l.getProduit().getPrix() + " DH\t" + l.getQuantite());
        }

        System.out.println("\nProduits > 100 DH :");
        for (Produit p : ps.getPrixSup100())
            System.out.println(p.getReference() + " - " + p.getPrix() + " DH");
    }
}
