package org.kpn.ch8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Service("jpaSingerService")
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class SingerServiceImpl implements SingerService {

    private static final Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);
    private static final String ALL_SINGER_NATIVE_QUERY =
            "select id, first_name, last_name, birth_date, version from singer";

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return em.createNamedQuery(Singer.FIND_ALL, Singer.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllWithAlbum() {
        return em.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        return em.createNamedQuery(Singer.FIND_SINGER_BY_ID, Singer.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public Singer save(Singer singer) {
        if (null == singer.getId()){
            logger.info("Inserting new singer");
            em.persist(singer);
        } else {
            logger.info("Updating existing singer");
            em.merge(singer);
        }
        logger.info("Singer saved with id : {}", singer.getId());

        return singer;
    }

    @Override
    public void delete(Singer singer) {
        Singer mergedSinger = em.merge(singer);
        em.remove(mergedSinger);
        logger.info("Singer with id {} deleted successfully", mergedSinger.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllByNativeQuery() {
        return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY, "singerResult").getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findByCriteriaQuery(String firstName, String lastName) {
        logger.info("Finding singer for firstname {} and lastName {}", firstName, lastName);

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Singer> criteriaQuery = criteriaBuilder.createQuery(Singer.class);
        Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
        singerRoot.fetch(Singer_.albums, JoinType.LEFT);
        singerRoot.fetch(Singer_.instruments, JoinType.LEFT);

        criteriaQuery.select(singerRoot).distinct(true);

        Predicate criteria = criteriaBuilder.conjunction();

        if (null != firstName){
            Predicate p = criteriaBuilder.equal(singerRoot.get(Singer_.firstName), firstName);
            criteria = criteriaBuilder.and(criteria, p);
        }

        if (null != lastName){
            Predicate p = criteriaBuilder.equal(singerRoot.get(Singer_.lastName), lastName);
            criteria = criteriaBuilder.and(criteria, p);
        }

        criteriaQuery.where(criteria);

        return em.createQuery(criteriaQuery).getResultList();
    }
}
