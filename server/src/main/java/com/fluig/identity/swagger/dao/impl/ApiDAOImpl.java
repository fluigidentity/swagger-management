package com.fluig.identity.swagger.dao.impl;

import com.fluig.identity.swagger.dao.model.ApiDAO;
import com.fluig.identity.swagger.dao.model.ApiEntity;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import java.lang.invoke.MethodHandles;
import java.util.Optional;

/**
 * Created by paulo.francisco on 29/03/2017.
 */
@RequestScoped
public class ApiDAOImpl implements ApiDAO{

    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Optional<ApiEntity> findById(Integer id) {
        LOG.info("findById - id: " + id);
        return Optional.empty();
    }
}
