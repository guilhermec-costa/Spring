package com.me.SpringApp.domain.repositories;

public interface IUserRepositoryMemory<T> extends BaseRepositoryMemory<T> {
   public void insert(T user); 
}
