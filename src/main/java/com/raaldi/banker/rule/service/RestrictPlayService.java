package com.raaldi.banker.rule.service;

import com.raaldi.banker.rule.model.RestrictPlay;
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
public class RestrictPlayService implements ModelService<RestrictPlay> {

  /** The restrict play repository. */
  private RestrictPlayRepository repository;

  @Autowired
  public void setRepository(final RestrictPlayRepository repository) {
    this.repository = repository;
  }

  @Override
  public void save(final RestrictPlay model) {
    repository.save(model);
  }

  @Override
  public RestrictPlay findOne(final long id) {
    return repository.findOne(id);
  }

  @Override
  public Iterable<RestrictPlay> findAll() {
    return repository.findAll();
  }

  @Override
  public boolean exists(final RestrictPlay model) {
    return this.exists(model.getId());
  }

  @Override
  public boolean exists(final long id) {
    return repository.exists(id);
  }

}
