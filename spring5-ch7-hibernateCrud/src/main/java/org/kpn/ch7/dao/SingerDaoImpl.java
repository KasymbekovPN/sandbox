package org.kpn.ch7.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.kpn.ch7.entities.Singer;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@SuppressWarnings("unchecked")
@Repository("singerDao")
public class SingerDaoImpl implements SingerDao{

    private static final Logger logger = LoggerFactory.getLogger(SingerDaoImpl.class);
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return sessionFactory.getCurrentSession().getNamedQuery(Singer.FIND_ALL).list();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllWithAlbum() {
        return sessionFactory.getCurrentSession().getNamedQuery(Singer.FIND_ALL_WITH_ALBUM).list();
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        return (Singer) sessionFactory
                .getCurrentSession()
                .getNamedQuery(Singer.FIND_SINGER_BY_ID)
                .setParameter("id", id)
                .uniqueResult();
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

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
