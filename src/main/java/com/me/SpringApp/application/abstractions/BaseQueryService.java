package com.me.SpringApp.application.abstractions;

import java.util.List;
import java.util.Optional;

public interface BaseQueryService<T> {
	
	<Q extends Query> Optional<T> findOne(Q query);
	<Q extends Query> List<T> findAll(Q query);
}
