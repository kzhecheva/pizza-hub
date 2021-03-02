package pizzahub_project.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pizzahub_project.models.Pizza;
import pizzahub_project.repositories.contracts.PizzaRepository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class PizzaRepositoryImpl implements PizzaRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public PizzaRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Pizza> showAll(){
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Pizza";
            Query<Pizza> query = session.createQuery( hql, Pizza.class);
            return query.list();
        }
    }

    @Override
    public Pizza getById(int id){
        Transaction transaction = null;
        Pizza pizza = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "from Pizza where pizzaId = :pizzaId";
            Query query = session.createQuery(hql);
            query.setParameter("pizzaId", id);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                pizza = (Pizza) results.get(0);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return pizza;
    }

    @Override
    public Pizza getByName(String name){
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Pizza where name = :name", Pizza.class);
            query.setParameter("name", name);
            try {
                return (Pizza) query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }
    }
}

