package ma.projet.service;

import ma.projet.classes.EmployeTache;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeTacheService implements IDao<EmployeTache> {

    @Override
    public void create(EmployeTache o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction(); s.save(o); t.commit(); s.close();
    }
    @Override public void update(EmployeTache o) {}
    @Override public void delete(EmployeTache o) {}
    @Override
    public EmployeTache findById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        EmployeTache e = s.get(EmployeTache.class, id); s.close(); return e;
    }
    @Override
    public List<EmployeTache> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<EmployeTache> list = s.createQuery("from EmployeTache", EmployeTache.class).list(); s.close(); return list;
    }
}
