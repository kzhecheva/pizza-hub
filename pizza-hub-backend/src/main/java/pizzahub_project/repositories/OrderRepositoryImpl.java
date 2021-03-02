package pizzahub_project.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pizzahub_project.models.CartOrder;
import pizzahub_project.repositories.contracts.OrderRepository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CartOrder> showAll() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM CartOrder where checkedOut = true";
            Query<CartOrder> query = session.createQuery(hql, CartOrder.class);
            return query.list();
        }
    }

    @Override
    public CartOrder getCurrentOrder() {
        Transaction transaction = null;
        CartOrder cartOrder = getUncompleteCart();
        if (cartOrder == null) {
            addEmptyCart();
            cartOrder = getUncompleteCart();
        }
        return cartOrder;
    }

    private CartOrder getUncompleteCart() {
        Transaction transaction = null;
        CartOrder cartOrder = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "from CartOrder where checkedOut = :checkedOut";
            Query query = session.createQuery(hql);
            query.setParameter("checkedOut", false);
            List results = query.getResultList();

            if (results != null && !results.isEmpty()) {
                cartOrder = (CartOrder) results.get(0);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return cartOrder;
    }

    private void addEmptyCart() {
        Transaction transaction = null;
        CartOrder cartOrder = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            cartOrder = new CartOrder();
            session.save(cartOrder);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateOrder(CartOrder cartOrder) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(cartOrder);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}
