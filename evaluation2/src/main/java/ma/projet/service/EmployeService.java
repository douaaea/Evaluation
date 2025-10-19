package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeService implements IDao<Employe> {

    @Override
    public void create(Employe o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction(); s.save(o); t.commit(); s.close();
    }
    @Override public void update(Employe o) {}
    @Override public void delete(Employe o) {}
    @Override
    public Employe findById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Employe e = s.get(Employe.class, id); s.close(); return e;
    }
    @Override
    public List<Employe> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Employe> list = s.createQuery("from Employe", Employe.class).list(); s.close(); return list;
    }

    /** Tâches réalisées par un employé (via EmployeTache) */
    public List<EmployeTache> tachesRealiseesParEmploye(int idEmploye) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<EmployeTache> list = s.createQuery(
                        "from EmployeTache et where et.employe.id = :id", EmployeTache.class)
                .setParameter("id", idEmploye).list();
        s.close(); return list;
    }

    /** Projets gérés (où il est chef) */
    public List<ma.projet.classes.Projet> projetsGeresParEmploye(int idEmploye) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<ma.projet.classes.Projet> list = s.createQuery(
                        "from Projet p where p.chef.id = :id", ma.projet.classes.Projet.class)
                .setParameter("id", idEmploye).list();
        s.close(); return list;
    }
}
