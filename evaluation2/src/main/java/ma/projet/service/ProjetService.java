package ma.projet.service;

import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProjetService implements IDao<Projet> {

    @Override
    public void create(Projet o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction(); s.save(o); t.commit(); s.close();
    }
    @Override public void update(Projet o) {}
    @Override public void delete(Projet o) {}
    @Override
    public Projet findById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Projet p = s.get(Projet.class, id); s.close(); return p;
    }
    @Override
    public List<Projet> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Projet> list = s.createQuery("from Projet", Projet.class).list(); s.close(); return list;
    }

    /** Tâches planifiées pour un projet (les dates prévues dans Tache) */
    public List<Tache> tachesPlanifiees(int idProjet) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Tache> list = s.createQuery(
                        "from Tache t where t.projet.id = :pid", Tache.class)
                .setParameter("pid", idProjet).list();
        s.close(); return list;
    }

    /** Tâches réalisées (avec dates réelles) pour un projet */
    public List<EmployeTache> tachesRealiseesAvecDates(int idProjet) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<EmployeTache> list = s.createQuery(
                        "from EmployeTache et where et.tache.projet.id = :pid", EmployeTache.class)
                .setParameter("pid", idProjet).list();
        s.close(); return list;
    }
}
