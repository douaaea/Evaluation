package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Commande {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date date;

    public Commande() {}
    public Commande(Date date) { this.date = date; }

    public int getId() { return id; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
