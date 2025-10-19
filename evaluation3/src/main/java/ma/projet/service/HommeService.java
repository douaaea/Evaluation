package ma.projet.service;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

public class HommeService implements IDao<Homme> {
    @Override
    public void create(Homme o) { Session s=HibernateUtil.getSessionFactory().openSession(); Transaction t=s.beginTransaction(); s.save(o); t.commit(); s.close(); }
    @Override
    public void update(Homme o) { Session s=HibernateUtil.getSessionFactory().openSession(); Transaction t=s.beginTransaction(); s.update(o); t.commit(); s.close(); }
    @Override
    public void delete(Homme o) { Session s=HibernateUtil.getSessionFactory().openSession(); Transaction t=s.beginTransaction(); s.delete(o); t.commit(); s.close(); }
    @Override
    public Homme findById(int id) { Session s=HibernateUtil.getSessionFactory().openSession(); Homme h=s.get(Homme.class,id); s.close(); return h; }
    @Override
    public List<Homme> findAll() { Session s=HibernateUtil.getSessionFactory().openSession(); List<Homme> l=s.createQuery("from Homme",Homme.class).list(); s.close(); return l; }

    public List<Femme> epousesEntreDates(int idHomme, Date d1, Date d2) {
        Session s=HibernateUtil.getSessionFactory().openSession();
        List<Femme> list = s.createQuery(
                "select m.femme from Mariage m where m.homme.id=:hid and m.dateDebut between :d1 and :d2",
                Femme.class).setParameter("hid", idHomme).setParameter("d1", d1).setParameter("d2", d2).list();
        s.close(); return list;
    }

    public long hommesMarieAQuatreFemmesEntre(Date d1, Date d2) {
        Session s=HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Mariage> m = cq.from(Mariage.class);
        cq.select(cb.countDistinct(m.get("homme")));
        Predicate p1 = cb.greaterThanOrEqualTo(m.get("dateDebut"), d1);
        Predicate p2 = cb.lessThanOrEqualTo(m.get("dateDebut"), d2);
        cq.where(cb.and(p1,p2));
        cq.groupBy(m.get("homme"));
        cq.having(cb.greaterThanOrEqualTo(cb.countDistinct(m.get("femme")), 4L));
        List<Long> res = s.createQuery(cq).getResultList();
        s.close();
        return res.size();
    }

    public List<Mariage> mariagesDetailHomme(int idHomme) {
        Session s=HibernateUtil.getSessionFactory().openSession();
        List<Mariage> list = s.createQuery(
                "from Mariage m join fetch m.femme where m.homme.id=:hid order by m.dateDebut",
                Mariage.class).setParameter("hid", idHomme).list();
        s.close(); return list;
    }
}
