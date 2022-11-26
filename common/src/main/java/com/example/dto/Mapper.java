package com.example.dto;

public interface Mapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

}
