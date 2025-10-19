package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Projet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    // Chef de projet (Employe)
    @ManyToOne
    private Employe chef;

    public Projet() {}
    public Projet(String nom, Date dateDebut, Date dateFin, Employe chef) {
        this.nom = nom; this.dateDebut = dateDebut; this.dateFin = dateFin; this.chef = chef;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public Employe getChef() { return chef; }
    public void setChef(Employe chef) { this.chef = chef; }
}
