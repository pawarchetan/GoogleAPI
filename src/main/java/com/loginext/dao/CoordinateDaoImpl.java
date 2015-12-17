package com.loginext.dao;

import com.loginext.model.Coordinate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoordinateDaoImpl implements CoordinateDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Coordinate> getAllCoordinates() throws Exception {
        return getCurrentSession().createQuery("from Coordinate").list();
    }
}
