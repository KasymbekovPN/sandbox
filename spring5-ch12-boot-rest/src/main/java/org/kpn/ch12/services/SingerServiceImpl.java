package org.kpn.ch12.services;

import com.google.common.collect.Lists;
import org.kpn.ch12.config.SingerRepository;
import org.kpn.ch12.entities.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerService")
@Transactional
public class SingerServiceImpl implements SingerService{
//package com.apress.prospring5.ch12.services;
//
//import com.apress.prospring5.ch12.entities.Singer;
//import com.apress.prospring5.ch12.repos.SingerRepository;
//import com.google.common.collect.Lists;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//    @Service("singerService")
//    @Transactional
//    public class SingerServiceImpl implements SingerService {
//

    @Autowired
    private SingerRepository singerRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findByFirstName(String firstName) {
        return singerRepository.findByFirstName(firstName);
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

    @Override
    public void delete(Singer singer) {
        singerRepository.delete(singer);
    }
}
