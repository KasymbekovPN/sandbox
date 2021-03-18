package org.kpn.ch7.dao;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.SessionFactory;
import org.kpn.ch7.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.plaf.metal.MetalButtonUI;
import java.util.List;

@Transactional
@Repository("singerDao")
public class SingerDaoImpl implements SingerDao{

    private static final Logger logger = LoggerFactory.getLogger(SingerDaoImpl.class);

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return (List<Singer>) sessionFactory.getCurrentSession().createQuery("from Singer s").list();
    }

    @Override
    public List<Singer> findAllWithAlbum() {
        return (List<Singer>) sessionFactory.getCurrentSession()
                .getNamedQuery("Singer.findAllWithAlbum").list();
    }

    @Override
    public Singer findById(Long id) {
        return (Singer) sessionFactory.getCurrentSession()
                .getNamedQuery("Singer.findById")
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public Singer save(Singer singer) {
        sessionFactory.getCurrentSession().saveOrUpdate(singer);
        logger.info("Singer saved with id: {}", singer.getId());
        return singer;
    }

    @Override
    public void delete(Singer singer) {
        sessionFactory.getCurrentSession().delete(singer);
        logger.info("Singer deleted with id : {}", singer.getId());
    }
}
