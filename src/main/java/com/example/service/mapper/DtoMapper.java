package com.example.service.mapper;

import java.util.Collection;

public interface DtoMapper<Dto, E> {
    Dto toDto(E e);
    E fromDto(Dto dto);
}
