package ma.projet.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("FEMME")
@NamedQuery(
        name = "Femme.marieeAuMoinsDeuxFois",
        query = "select f from Femme f where (select count(m) from Mariage m where m.femme = f) >= 2"
)
@SqlResultSetMapping(
        name = "TotalInt",
        columns = @ColumnResult(name = "total", type = Integer.class)
)
@NamedNativeQuery(
        name = "Femme.nbrEnfantsEntre",
        query = "SELECT COALESCE(SUM(nbrEnfant),0) AS total FROM Mariage " +
                "WHERE femme_id = :id AND dateDebut BETWEEN :d1 AND :d2",
        resultSetMapping = "TotalInt"
)
public class Femme extends Personne {
    public Femme() {}
    public Femme(String nom, String prenom) { this.nom = nom; this.prenom = prenom; }
}
