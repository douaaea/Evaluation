package ma.projet.service;

import ma.projet.classes.Categorie;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class CategorieService implements IDao<Categorie> {
    @Override
    public void create(Categorie o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.save(o);
        t.commit();
        s.close();
    }

    @Override
    public void update(Categorie o) {}
    @Override
    public void delete(Categorie o) {}
    @Override
    public Categorie findById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Categorie c = s.get(Categorie.class, id);
        s.close();
        return c;
    }

    @Override
    public List<Categorie> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Categorie> list = s.createQuery("from Categorie").list();
        s.close();
        return list;
    }
}
