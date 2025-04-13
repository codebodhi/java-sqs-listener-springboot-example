package com.codebodhi.sqslib.springboot;

import com.codebodhi.sqslib.springboot.entity.PrimeFactor;
import com.codebodhi.sqslib.springboot.model.PrimeFactorRequest;
import com.codebodhi.sqslib.springboot.repo.PrimeFactorRepo;
import com.codebodhi.sqslib.springboot.service.PrimeFactorFinder;
import com.codebodhi.sqslistener.SqsListener;
import com.codebodhi.sqslistener.SqsListenerConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrimeFactorReqListener extends SqsListener {
  private final PrimeFactorRepo primeFactorRepo;
  private final ObjectMapper objectMapper;

  public PrimeFactorReqListener(
      @Value("${prime-factor-queue}") String queueName,
      @Qualifier("primeFactorReqListenerConfig") SqsListenerConfig sqsListenerConfig,
      ObjectMapper objectMapper,
      PrimeFactorRepo primeFactorRepo) {
    // call super constructor
    super(queueName, sqsListenerConfig);

    this.objectMapper = objectMapper;
    this.primeFactorRepo = primeFactorRepo;
  }

  @Override
  @Transactional
  public void process(String message) throws JsonProcessingException {
    PrimeFactorRequest request = objectMapper.readValue(message, PrimeFactorRequest.class);
    final List<Integer> primeFactors = PrimeFactorFinder.getPrimeFactors(request.input());
    Integer input = request.input();
    Optional<PrimeFactor> primeFactor = primeFactorRepo.findById(input);
    if (primeFactor.isEmpty()) {
      primeFactorRepo.save(
          new PrimeFactor(
              request.input(), request.seqNum(), LocalDateTime.now(), primeFactors.toString()));
    } else {
      primeFactor.get().setPrimeFactor(primeFactors.toString());
    }
  }
}
