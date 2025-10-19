package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EmployeTache {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne private Employe employe;
    @ManyToOne private Tache tache;

    @Temporal(TemporalType.DATE)
    private Date dateDebutReelle;
    @Temporal(TemporalType.DATE)
    private Date dateFinReelle;

    public EmployeTache() {}
    public EmployeTache(Employe employe, Tache tache, Date ddr, Date dfr) {
        this.employe = employe; this.tache = tache; this.dateDebutReelle = ddr; this.dateFinReelle = dfr;
    }

    public int getId() { return id; }
    public Employe getEmploye() { return employe; }
    public Tache getTache() { return tache; }
    public Date getDateDebutReelle() { return dateDebutReelle; }
    public Date getDateFinReelle() { return dateFinReelle; }
    public void setDateDebutReelle(Date d) { this.dateDebutReelle = d; }
    public void setDateFinReelle(Date d) { this.dateFinReelle = d; }
}
