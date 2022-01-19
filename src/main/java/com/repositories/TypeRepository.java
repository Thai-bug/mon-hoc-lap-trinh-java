package com.repositories;

import com.pojos.Type;

import java.util.Set;

public interface TypeRepository {
    public Set<Type> getActiveType(String name, int page);

}
