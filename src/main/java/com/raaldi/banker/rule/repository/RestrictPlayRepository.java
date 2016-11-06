package com.raaldi.banker.rule.repository;

import com.raaldi.banker.rule.model.RestrictedPlay;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@CacheConfig(cacheNames = "RestrictedPlay")
@Repository("restrictedPlayRepository")
public interface RestrictPlayRepository extends CrudRepository<RestrictedPlay, Long> {
}
