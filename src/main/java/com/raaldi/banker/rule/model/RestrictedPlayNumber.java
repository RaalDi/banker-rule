package com.raaldi.banker.rule.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RestrictedPlayNumber implements Serializable {

  private static final long serialVersionUID = -2499724899416829464L;

  @NotNull
  @Column(name = "restricted_number", nullable = false)
  private int number;
}
