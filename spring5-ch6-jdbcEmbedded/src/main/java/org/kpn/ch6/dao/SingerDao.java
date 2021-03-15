package org.kpn.ch6.dao;

import org.kpn.ch6.entities.Singer;

public interface SingerDao {
    String findNameById(Long id);
    void insert(Singer singer);
}
