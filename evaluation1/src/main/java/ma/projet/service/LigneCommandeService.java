package ma.projet.service;

import ma.projet.classes.LigneCommandeProduit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class LigneCommandeService implements IDao<LigneCommandeProduit> {
    @Override
    public void create(LigneCommandeProduit o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.save(o);
        t.commit();
        s.close();
    }
    @Override
    public void update(LigneCommandeProduit o) {}
    @Override
    public void delete(LigneCommandeProduit o) {}
    @Override
    public LigneCommandeProduit findById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        LigneCommandeProduit l = s.get(LigneCommandeProduit.class, id);
        s.close();
        return l;
    }
    @Override
    public List<LigneCommandeProduit> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<LigneCommandeProduit> list = s.createQuery("from LigneCommandeProduit").list();
        s.close();
        return list;
    }
}
