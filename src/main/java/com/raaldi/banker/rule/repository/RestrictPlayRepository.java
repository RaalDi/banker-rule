package com.raaldi.banker.rule.repository;

import com.raaldi.banker.rule.model.RestrictPlay;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("restrictPlayRepository")
public interface RestrictPlayRepository extends CrudRepository<RestrictPlay, Long> {
}
