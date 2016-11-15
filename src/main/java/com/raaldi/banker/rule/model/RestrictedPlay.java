package com.raaldi.banker.rule.model;

import com.raaldi.banker.util.model.AbstractModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bk_restricted_play")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "RestrictedPlay")
@NamedQueries({ @NamedQuery(name = "RestrictedPlay.findAll", query = "SELECT c FROM RestrictedPlay c") })
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RestrictedPlay extends AbstractModel {

  private static final long serialVersionUID = -3936838944769073574L;

  @Id
  @SequenceGenerator(name = "bk-restricted-play-seq-gen", sequenceName = "bk_restricted_play_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bk-restricted-play-seq-gen")
  @Column(name = "restricted_play_id")
  private long restrictedPlayid;

  @NotNull
  @Column(name = "restricted_play_name", nullable = false, insertable = true, updatable = false)
  private String playName;

  @NotEmpty
  @ElementCollection
  @CollectionTable(name = "bk_restricted_play_lottery", joinColumns = { @JoinColumn(name = "restricted_play_id") })
  private Set<RestrictedPlayLottery> lotteries = new HashSet<RestrictedPlayLottery>();

  @NotEmpty
  @ElementCollection
  @CollectionTable(name = "bk_restricted_play_number", joinColumns = {
      @JoinColumn(name = "restricted_play_id") }, uniqueConstraints = @UniqueConstraint(columnNames = {
          "restricted_play_id", "restricted_number" }))
  private Set<RestrictedPlayNumber> numbers = Collections.emptySet();

  @NotNull
  @Column(name = "start_date", nullable = false, columnDefinition = "timestamp")
  private LocalDateTime startDate;

  @NotNull
  @Column(name = "end_date", nullable = false, columnDefinition = "timestamp")
  private LocalDateTime endDate;

  @NotNull
  @Column(name = "active", nullable = false, columnDefinition = "boolean default false")
  private boolean active;
}
