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
public class RestrictPlayLottery implements Serializable {

  private static final long serialVersionUID = -751469974986603513L;

  @NotNull
  @Column(name = "lottery_name", nullable = false, insertable = true, updatable = false)
  private String lotteryName;

}
