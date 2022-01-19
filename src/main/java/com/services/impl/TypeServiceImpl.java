package com.services.impl;

import com.pojos.Type;
import com.repositories.TypeRepository;
import com.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service()
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Override
    public Set<Type> getActiveType(String name, int page) {
        return typeRepository.getActiveType(name, page);
    }
}
