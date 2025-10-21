package ma.projet.test;

import ma.projet.classes.Categorie;
import ma.projet.classes.Produit;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.service.ProduitService;
import ma.projet.service.CommandeService;
import ma.projet.service.LigneCommandeService;
import ma.projet.util.HibernateConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class TestGestionStock {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateConfig.class);

        ProduitService produitService = context.getBean(ProduitService.class);
        CommandeService commandeService = context.getBean(CommandeService.class);
        LigneCommandeService ligneService = context.getBean(LigneCommandeService.class);

        // Creation des categories
        Categorie categorie1 = new Categorie();
        categorie1.setCode("ORDI");
        categorie1.setLibelle("Ordinateurs");

        Categorie categorie2 = new Categorie();
        categorie2.setCode("PERI");
        categorie2.setLibelle("Peripheriques");

        Categorie categorie3 = new Categorie();
        categorie3.setCode("IMPR");
        categorie3.setLibelle("Imprimantes");

        // Creation des produits
        Produit prod1 = new Produit();
        prod1.setReference("HP340");
        prod1.setPrix(150f);
        prod1.setCategorie(categorie1);

        Produit prod2 = new Produit();
        prod2.setReference("DL920");
        prod2.setPrix(95f);
        prod2.setCategorie(categorie2);

        Produit prod3 = new Produit();
        prod3.setReference("AS780");
        prod3.setPrix(220f);
        prod3.setCategorie(categorie1);

        Produit prod4 = new Produit();
        prod4.setReference("MX450");
        prod4.setPrix(75f);
        prod4.setCategorie(categorie2);

        Produit prod5 = new Produit();
        prod5.setReference("LX880");
        prod5.setPrix(180f);
        prod5.setCategorie(categorie3);

        produitService.create(prod1);
        produitService.create(prod2);
        produitService.create(prod3);
        produitService.create(prod4);
        produitService.create(prod5);

        // Creation des commandes
        Commande commande1 = new Commande();
        commande1.setDate(LocalDate.of(2024, 2, 10));
        commandeService.create(commande1);

        Commande commande2 = new Commande();
        commande2.setDate(LocalDate.of(2024, 4, 18));
        commandeService.create(commande2);

        Commande commande3 = new Commande();
        commande3.setDate(LocalDate.of(2024, 6, 25));
        commandeService.create(commande3);

        // Creation des lignes de commande
        LigneCommandeProduit ligne1 = new LigneCommandeProduit();
        ligne1.setProduit(prod1);
        ligne1.setCommande(commande1);
        ligne1.setQuantite(10);

        LigneCommandeProduit ligne2 = new LigneCommandeProduit();
        ligne2.setProduit(prod2);
        ligne2.setCommande(commande1);
        ligne2.setQuantite(20);

        LigneCommandeProduit ligne3 = new LigneCommandeProduit();
        ligne3.setProduit(prod3);
        ligne3.setCommande(commande2);
        ligne3.setQuantite(8);

        LigneCommandeProduit ligne4 = new LigneCommandeProduit();
        ligne4.setProduit(prod4);
        ligne4.setCommande(commande2);
        ligne4.setQuantite(15);

        LigneCommandeProduit ligne5 = new LigneCommandeProduit();
        ligne5.setProduit(prod5);
        ligne5.setCommande(commande3);
        ligne5.setQuantite(12);

        ligneService.create(ligne1);
        ligneService.create(ligne2);
        ligneService.create(ligne3);
        ligneService.create(ligne4);
        ligneService.create(ligne5);

        System.out.println("========================================");
        System.out.println("Donnees initiales creees avec succes");
        System.out.println("========================================\n");

        // Test 1: Affichage des produits par categorie
        System.out.println("--- Liste des produits de la categorie: " + categorie1.getLibelle() + " ---");
        List<Produit> produitsOrdinateurs = produitService.findByCategorie(categorie1);
        for (Produit p : produitsOrdinateurs) {
            System.out.printf("Reference: %-8s | Prix: %-8.2f DH | Categorie: %s%n",
                    p.getReference(), p.getPrix(), p.getCategorie().getLibelle());
        }

        System.out.println("\n--- Liste des produits de la categorie: " + categorie2.getLibelle() + " ---");
        List<Produit> produitsPeripheriques = produitService.findByCategorie(categorie2);
        for (Produit p : produitsPeripheriques) {
            System.out.printf("Reference: %-8s | Prix: %-8.2f DH | Categorie: %s%n",
                    p.getReference(), p.getPrix(), p.getCategorie().getLibelle());
        }

        // Test 2: Affichage des produits par commande
        System.out.println("\n--- Produits de la commande numero: " + commande1.getId() + " ---");
        produitService.findByCommande(commande1.getId());

        System.out.println("\n--- Produits de la commande numero: " + commande2.getId() + " ---");
        produitService.findByCommande(commande2.getId());

        // Test 3: Produits commandes entre deux dates
        System.out.println("\n--- Produits commandes entre le 2024-01-01 et le 2024-12-31 ---");
        LocalDate dateDebut = LocalDate.of(2024, 1, 1);
        LocalDate dateFin = LocalDate.of(2024, 12, 31);
        produitService.findByDateCommande(dateDebut, dateFin);

        // Test 4: Produits dont le prix depasse 100 DH
        System.out.println("\n--- Produits avec un prix superieur a 100 DH ---");
        produitService.findProduitsPrixSuperieur(100f);


        context.close();
    }
}
