package com.codebodhi.sqslib.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "prime_factor")
@Getter
@Setter
public class PrimeFactor {
  @Id private Integer input;

  @Column(name = "seq_no", nullable = false)
  private Integer seqNum;

  @Column(name = "create_time", nullable = false)
  private LocalDateTime createTime;

  @Lob
  @Column(name = "prime_factors", columnDefinition = "TEXT")
  private String primeFactor;

  public PrimeFactor() {}

  public PrimeFactor(Integer input, Integer seqNum, LocalDateTime createTime, String primeFactor) {
    this.input = input;
    this.seqNum = seqNum;
    this.createTime = createTime;
    this.primeFactor = primeFactor;
  }
}
