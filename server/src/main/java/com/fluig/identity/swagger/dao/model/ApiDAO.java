package com.fluig.identity.swagger.dao.model;

import java.util.Optional;

/**
 * Created by paulo.francisco on 29/03/2017.
 */
public interface ApiDAO {

    Optional<ApiEntity> findById(Integer id);
}
