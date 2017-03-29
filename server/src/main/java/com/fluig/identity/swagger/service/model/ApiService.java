package com.fluig.identity.swagger.service.model;

import java.util.Optional;

/**
 * Created by paulo.francisco on 29/03/2017.
 */
public interface ApiService {

    Optional<ApiDTO> findById(Integer id);
}
