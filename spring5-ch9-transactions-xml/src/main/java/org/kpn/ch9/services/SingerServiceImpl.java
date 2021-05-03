package org.kpn.ch9.services;

import com.google.common.collect.Lists;
import org.kpn.ch9.entities.Singer;
import org.kpn.ch9.repos.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("singerService")
@Repository
public class SingerServiceImpl implements SingerService{

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Override
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Override
    public Singer findById(Long id) {
        return singerRepository.findById(id).get();
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public long countAll() {
        return singerRepository.countAllSingers();
    }
}
