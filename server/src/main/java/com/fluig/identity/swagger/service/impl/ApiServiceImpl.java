package com.fluig.identity.swagger.service.impl;

import com.fluig.identity.swagger.dao.model.ApiDAO;
import com.fluig.identity.swagger.service.model.ApiDTO;
import com.fluig.identity.swagger.service.model.ApiService;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.util.Optional;

/**
 * Created by paulo.francisco on 29/03/2017.
 */
@RequestScoped
public class ApiServiceImpl implements ApiService {

    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    private ApiDAO apiDAO;

    @Override
    public Optional<ApiDTO> findById(Integer id) {
        LOG.info("findById - id: " + id);
        apiDAO.findById(id);
        return Optional.empty();
    }
}
