package org.kpn.ch18.repos;

import org.kpn.ch18.entities.Singer;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository extends CrudRepository<Singer, Long> {
}
