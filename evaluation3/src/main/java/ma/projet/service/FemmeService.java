package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class FemmeService implements IDao<Femme> {
    @Override
    public void create(Femme o) { Session s=HibernateUtil.getSessionFactory().openSession(); Transaction t=s.beginTransaction(); s.save(o); t.commit(); s.close(); }
    @Override
    public void update(Femme o) { Session s=HibernateUtil.getSessionFactory().openSession(); Transaction t=s.beginTransaction(); s.update(o); t.commit(); s.close(); }
    @Override
    public void delete(Femme o) { Session s=HibernateUtil.getSessionFactory().openSession(); Transaction t=s.beginTransaction(); s.delete(o); t.commit(); s.close(); }
    @Override
    public Femme findById(int id) { Session s=HibernateUtil.getSessionFactory().openSession(); Femme f=s.get(Femme.class,id); s.close(); return f; }
    @Override
    public List<Femme> findAll() { Session s=HibernateUtil.getSessionFactory().openSession(); List<Femme> l=s.createQuery("from Femme",Femme.class).list(); s.close(); return l; }

    public int nombreEnfantsEntre(int idFemme, Date d1, Date d2) {
        Session s=HibernateUtil.getSessionFactory().openSession();
        Object x = s.createNamedQuery("Femme.nbrEnfantsEntre")
                .setParameter("id", idFemme)
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .getSingleResult();
        s.close();
        return ((Number)x).intValue();
    }

    public List<Femme> marieeDeuxFoisOuPlus() {
        Session s=HibernateUtil.getSessionFactory().openSession();
        List<Femme> l = s.createNamedQuery("Femme.marieeAuMoinsDeuxFois", Femme.class).getResultList();
        s.close(); return l;
    }
}
