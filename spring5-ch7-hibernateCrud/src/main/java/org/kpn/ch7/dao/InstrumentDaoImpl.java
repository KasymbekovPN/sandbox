package org.kpn.ch7.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.kpn.ch7.entities.Instrument;

import javax.annotation.Resource;

@Transactional
@Repository("instrumentDao")
public class InstrumentDaoImpl implements InstrumentDao{

    private static final Logger logger = LoggerFactory.getLogger(InstrumentDaoImpl.class);
    private SessionFactory sessionFactory;

    @Override
    public Instrument save(Instrument instrument) {
        sessionFactory.getCurrentSession().saveOrUpdate(instrument);
        logger.info("Instrument saved with id: {}", instrument.getInstrumentId());
        return instrument;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
