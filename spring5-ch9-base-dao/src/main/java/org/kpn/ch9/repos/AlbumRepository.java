package org.kpn.ch9.repos;

import org.kpn.ch9.entities.Album;
import org.kpn.ch9.entities.Singer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    List<Album> findBySinger(Singer singer);
}
