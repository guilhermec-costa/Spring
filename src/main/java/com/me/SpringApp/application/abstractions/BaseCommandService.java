package com.me.SpringApp.application.abstractions;

public interface BaseCommandService<T> {
	T register(T instance);
}
