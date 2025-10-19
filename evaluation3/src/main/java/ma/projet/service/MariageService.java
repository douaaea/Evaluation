package ma.projet.service;

import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class MariageService implements IDao<Mariage> {
    @Override
    public void create(Mariage o) { Session s=HibernateUtil.getSessionFactory().openSession(); Transaction t=s.beginTransaction(); s.save(o); t.commit(); s.close(); }
    @Override
    public void update(Mariage o) { Session s=HibernateUtil.getSessionFactory().openSession(); Transaction t=s.beginTransaction(); s.update(o); t.commit(); s.close(); }
    @Override
    public void delete(Mariage o) { Session s=HibernateUtil.getSessionFactory().openSession(); Transaction t=s.beginTransaction(); s.delete(o); t.commit(); s.close(); }
    @Override
    public Mariage findById(int id) { Session s=HibernateUtil.getSessionFactory().openSession(); Mariage m=s.get(Mariage.class,id); s.close(); return m; }
    @Override
    public List<Mariage> findAll() { Session s=HibernateUtil.getSessionFactory().openSession(); List<Mariage> l=s.createQuery("from Mariage",Mariage.class).list(); s.close(); return l; }

    public List<Mariage> mariagesEntre(Date d1, Date d2) {
        Session s=HibernateUtil.getSessionFactory().openSession();
        List<Mariage> l = s.createQuery("from Mariage m where m.dateDebut between :d1 and :d2", Mariage.class)
                .setParameter("d1", d1).setParameter("d2", d2).list();
        s.close(); return l;
    }
}
