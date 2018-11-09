package com.example.sqltest.repository;


public interface CommonRepository {

    <T> void save(T t) throws Exception;

    <T> void update(T t) throws Exception;

    <T> void delete(T t) throws Exception;
}
