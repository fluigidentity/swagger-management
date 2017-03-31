package com.fluig.identity.swagger.service.model;

import com.fluig.identity.swagger.api.model.exception.BaseDaoException;

import java.util.List;
import java.util.Optional;

/**
 * Created by paulo.francisco on 29/03/2017.
 */
public interface ApiService {

    ApiDTO insert(ApiDTO dto) throws ApiException;
    void remove(Integer id) throws ApiException;
    Optional<ApiDTO> findById(Integer id);
    List<ApiDTO> findAll();
}
