package org.kpn.ch16.repos;

import org.kpn.ch16.entities.Singer;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository extends CrudRepository<Singer, Long> {
}
