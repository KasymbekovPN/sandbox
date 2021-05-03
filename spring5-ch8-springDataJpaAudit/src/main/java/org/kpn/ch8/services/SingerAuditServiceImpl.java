package org.kpn.ch8.services;

import com.google.common.collect.Lists;
import org.kpn.ch8.entities.SingerAudit;
import org.kpn.ch8.repos.SingerAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerAuditService")
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService{

    @Autowired
    private SingerAuditRepository singerAuditRepository;

    @Transactional(readOnly = true)
    @Override
    public List<SingerAudit> findAll() {
        return Lists.newArrayList(singerAuditRepository.findAll());
    }

    @Override
    public SingerAudit findById(Long id) {
        return singerAuditRepository.findById(id).get();
    }

    @Override
    public SingerAudit save(SingerAudit singer) {
        return singerAuditRepository.save(singer);
    }
}
