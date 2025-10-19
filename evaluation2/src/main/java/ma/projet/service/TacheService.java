package ma.projet.service;

import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;
import java.util.List;

public class TacheService implements IDao<Tache> {

    @Override
    public void create(Tache o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction(); s.save(o); t.commit(); s.close();
    }
    @Override public void update(Tache o) {}
    @Override public void delete(Tache o) {}
    @Override
    public Tache findById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Tache p = s.get(Tache.class, id); s.close(); return p;
    }
    @Override
    public List<Tache> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Tache> list = s.createQuery("from Tache", Tache.class).list(); s.close(); return list;
    }

    /** Prix > 1000 (requête nommée) */
    public List<Tache> prixSup1000() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Tache> list = s.createNamedQuery("Tache.prixSup1000", Tache.class).list();
        s.close(); return list;
    }

    /** Tâches réalisées (dates réelles) entre deux dates */
    public List<EmployeTache> realiseesEntre(Date d1, Date d2) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<EmployeTache> list = s.createQuery(
                        "from EmployeTache et where et.dateDebutReelle >= :d1 and et.dateFinReelle <= :d2",
                        EmployeTache.class)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();
        s.close(); return list;
    }
}
