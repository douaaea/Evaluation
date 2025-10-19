package ma.projet.beans;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("HOMME")
public class Homme extends Personne {
    public Homme() {}
    public Homme(String nom, String prenom) { this.nom = nom; this.prenom = prenom; }
}
