package org.kpn.ch16.services;

import com.google.common.collect.Lists;
import org.kpn.ch16.entities.Singer;
import org.kpn.ch16.repos.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("singerService")
public class SingerServiceImpl implements SingerService{

    private SingerRepository singerRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        return singerRepository.findById(id).get();
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Singer> findAllByPage(Pageable pageable) {
        return singerRepository.findAll(pageable);
    }

    @Autowired
    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }
}
