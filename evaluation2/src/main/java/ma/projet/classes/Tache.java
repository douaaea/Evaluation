package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQuery(name="Tache.prixSup1000", query="from Tache t where t.prix > 1000")
public class Tache {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private double prix;

    // chaque tâche appartient à un projet
    @ManyToOne
    private Projet projet;

    public Tache() {}
    public Tache(String nom, Date dateDebut, Date dateFin, double prix, Projet projet) {
        this.nom = nom; this.dateDebut = dateDebut; this.dateFin = dateFin; this.prix = prix; this.projet = projet;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }
}
