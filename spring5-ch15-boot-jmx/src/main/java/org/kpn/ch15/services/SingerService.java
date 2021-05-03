package org.kpn.ch15.services;

import org.kpn.ch15.entities.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    List<Singer> findByFirstNameAndLastName(String firName, String lastName);
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(Singer singer);
}
