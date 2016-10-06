package com.raaldi.banker.rule.controller.api;

import com.raaldi.banker.rule.model.RestrictPlay;
import com.raaldi.banker.util.service.ModelService;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
/** Address service provides access to the address repository. */
@NoArgsConstructor
@RestController
@RequestMapping(value = "restrict-plays")
public class RestrictPlayRestController {

  @Autowired
  ModelService<RestrictPlay> service;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Iterable<RestrictPlay>> getAll() {
    Iterable<RestrictPlay> restrictPlays = service.findAll();
    if (restrictPlays.iterator().hasNext()) {
      return new ResponseEntity<Iterable<RestrictPlay>>(restrictPlays, HttpStatus.OK);
    }
    // You many decide to return HttpStatus.NOT_FOUND
    return new ResponseEntity<Iterable<RestrictPlay>>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RestrictPlay> get(@PathVariable("id") final long id) {
    log.info(String.format("Fetching RestrictPlay with id %s", id));
    RestrictPlay restrictPlay = service.findOne(id);
    if (restrictPlay == null) {
      log.info(String.format("RestrictPlay with id %s not found", id));
      return new ResponseEntity<RestrictPlay>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<RestrictPlay>(restrictPlay, HttpStatus.OK);
  }

  @RequestMapping(value = "/restric-play", method = RequestMethod.POST)
  public ResponseEntity<Void> create(@RequestBody final RestrictPlay restrictPlay,
      final UriComponentsBuilder uriBuilder) {
    log.info(String.format("Creating RestrictPlay %s", restrictPlay.toString()));

    if (service.exists(restrictPlay)) {
      log.info(String.format("A RestrictPlay with name %s already exist", restrictPlay.toString()));
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    service.save(restrictPlay);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(uriBuilder.path("/{id}").buildAndExpand(restrictPlay.getId()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity<RestrictPlay> update(@PathVariable("id") final long id,
      @RequestBody final RestrictPlay restrictPlay) {
    log.info(String.format("Updating RestrictPlay %s", id));

    RestrictPlay currentRestrictPlay = service.findOne(id);

    if (currentRestrictPlay == null) {
      log.info(String.format("RestrictPlay with id %s not found", id));
      return new ResponseEntity<RestrictPlay>(HttpStatus.NOT_FOUND);
    }

    currentRestrictPlay.setActive(restrictPlay.isActive());
    currentRestrictPlay.setEndDate(restrictPlay.getEndDate());
    currentRestrictPlay.setLotteries(restrictPlay.getLotteries());
    currentRestrictPlay.setNumbers(restrictPlay.getNumbers());
    currentRestrictPlay.setPlayName(restrictPlay.getPlayName());
    currentRestrictPlay.setStartDate(restrictPlay.getStartDate());

    /**
     * TODO: Update entity model service
     */
    // userService.updateRestrictPlay(currentRestrictPlay);
    return new ResponseEntity<RestrictPlay>(currentRestrictPlay, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<RestrictPlay> delete(@PathVariable("id") final long id) {
    log.info(String.format("Fetching & Deleting RestrictPlay with id %s", id));

    RestrictPlay restrictPlay = service.findOne(id);
    if (restrictPlay == null) {
      log.info(String.format("Unable to delete. RestrictPlay with id %s not found", id));
      return new ResponseEntity<RestrictPlay>(HttpStatus.NOT_FOUND);
    }
    /**
     * TODO: Addres delete method to service
     */
    // userService.deleteRestrictPlayById(id);
    return new ResponseEntity<RestrictPlay>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<RestrictPlay> deleteAll() {
    log.info("Deleting All RestrictPlays");

    /**
     * TODO: Addres delete all method to service
     */
    // userService.deleteAllRestrictPlays();
    return new ResponseEntity<RestrictPlay>(HttpStatus.NO_CONTENT);
  }
}
