package com.services;

import com.pojos.Type;

import java.util.Set;

public interface TypeService {
    public Set<Type> getActiveType(String name, int page);
}
