package com.crud.library.repository;

import com.crud.library.domain.Lending;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LendingRepository extends CrudRepository<Lending,Long> {
    @Override
    Lending save(Lending lending);
}
