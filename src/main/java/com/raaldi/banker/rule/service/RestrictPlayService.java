package com.raaldi.banker.rule.service;

import com.raaldi.banker.rule.model.RestrictedPlay;
import com.raaldi.banker.rule.repository.RestrictPlayRepository;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Restrict play service provides access to the restrict play repository. */
@NoArgsConstructor
@Service("restrictPlayService")
@Transactional
public class RestrictPlayService implements ModelService<RestrictedPlay> {

  /** The restrict play repository. */
  private RestrictPlayRepository repository;

  @Autowired
  public void setRepository(final RestrictPlayRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final RestrictedPlay model) {
    repository.save(model);
  }

  @Override
  public RestrictedPlay findOne(final long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<RestrictedPlay> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final RestrictedPlay model) {
    return this.exists(model.getRestrictedPlayid());
  }

  @Override
  public boolean exists(final long id) {
    return repository.exists(id);
  }

}
