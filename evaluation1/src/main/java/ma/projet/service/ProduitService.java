package ma.projet.service;

import ma.projet.classes.*;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {

    @Override
    public void create(Produit o) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.save(o);
        t.commit();
        s.close();
    }

    @Override
    public void update(Produit o) {}
    @Override
    public void delete(Produit o) {}
    @Override
    public Produit findById(int id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Produit p = s.get(Produit.class, id);
        s.close();
        return p;
    }
    @Override
    public List<Produit> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Produit> list = s.createQuery("from Produit").list();
        s.close();
        return list;
    }

    public List<Produit> getByCategorie(int idCat) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Produit> list = s.createQuery("from Produit where categorie.id=:id")
                .setParameter("id", idCat).list();
        s.close();
        return list;
    }

    public List<Produit> getBetween(Date d1, Date d2) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Produit> list = s.createQuery(
                        "select distinct l.produit from LigneCommandeProduit l where l.commande.date between :d1 and :d2")
                .setParameter("d1", d1).setParameter("d2", d2).list();
        s.close();
        return list;
    }

    public List<LigneCommandeProduit> getByCommande(int idCmd) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<LigneCommandeProduit> list = s.createQuery(
                        "from LigneCommandeProduit where commande.id=:id")
                .setParameter("id", idCmd).list();
        s.close();
        return list;
    }

    public List<Produit> getPrixSup100() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Produit> list = s.createNamedQuery("Produit.prixSup100").list();
        s.close();
        return list;
    }
}
