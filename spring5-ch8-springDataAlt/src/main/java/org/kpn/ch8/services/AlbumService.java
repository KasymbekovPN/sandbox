package org.kpn.ch8.services;

import org.kpn.ch8.entities.Album;
import org.kpn.ch8.entities.Singer;

import java.util.List;

public interface AlbumService {
    List<Album> findBySinger(Singer singer);
    List<Album> findByTitle(String title);
}
