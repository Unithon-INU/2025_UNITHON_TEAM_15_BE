package com.unithon.team15_server.global.translate;

import org.springframework.data.repository.CrudRepository;

public interface TranslateRedisRepository extends CrudRepository<Translation, String> {
}