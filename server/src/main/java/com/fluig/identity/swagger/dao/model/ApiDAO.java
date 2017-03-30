package com.fluig.identity.swagger.dao.model;

import com.fluig.identity.swagger.api.model.exception.BaseDaoException;

import java.util.List;
import java.util.Optional;

/**
 * Created by paulo.francisco on 29/03/2017.
 */
public interface ApiDAO {
    ApiEntity insert(ApiEntity entity) throws BaseDaoException;
    ApiEntity update(ApiEntity entity) throws BaseDaoException;
    void remove(ApiEntity entity) throws BaseDaoException;
    Optional<ApiEntity> findById(Integer id);
    List<ApiEntity> findAll();
}
