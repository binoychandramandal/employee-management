package com.bookmyshow.service.impl;

import com.bookmyshow.entity.Actor;
import com.bookmyshow.repository.ActorRepository;
import com.bookmyshow.service.ActorService;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl extends BaseServiceImpl<Actor, Long> implements ActorService {
    private ActorRepository actorRepository;
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
        setJpaRepository(actorRepository);
    }
}
