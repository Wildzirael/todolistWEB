package todolist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wz on 01.03.17.
 */
@Repository
public class DbWorker {

    @Autowired
    private SessionFactory sessionFactory;

    public <T> Serializable create(final T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    public <T> T update(final T entity) {
        sessionFactory.getCurrentSession().update(entity);
        return entity;
    }

    public <T> void delete(final T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public <T> void delete(Serializable id, Class<T> entityClass) {
        T entity = fetchById(id, entityClass);
        delete(entity);
    }

    public <T> List<T> fetchAll(Class<T> entityClass) {
        return sessionFactory.getCurrentSession().createQuery(" FROM "+entityClass.getName()).list();
    }

    public <T> int getSizeWithFilter(Class<T> entityClass, String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM " + entityClass.getName() + (filter.isEmpty() ? "" : filter));
        return query.list().size();
    }

    public <T> List<T> fetchPageWithFilter(Class<T> entityClass, String filter, int offset, int maxSize) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM " + entityClass.getName() + (filter.isEmpty() ? "" : filter));
        query.setFirstResult(offset);
        query.setMaxResults(maxSize);
        return query.list();
    }

    public <T> List fetchAll(String query) {
        return sessionFactory.getCurrentSession().createSQLQuery(query).list();
    }

    public <T> T fetchById(Serializable id, Class<T> entityClass) {
        return (T)sessionFactory.getCurrentSession().get(entityClass, id);
    }
}
