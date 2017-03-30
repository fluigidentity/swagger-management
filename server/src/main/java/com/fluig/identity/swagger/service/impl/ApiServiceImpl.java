package com.fluig.identity.swagger.service.impl;

import com.fluig.identity.swagger.api.model.utils.ConverterUtils;
import com.fluig.identity.swagger.dao.model.ApiDAO;
import com.fluig.identity.swagger.dao.model.ApiEntity;
import com.fluig.identity.swagger.service.model.ApiDTO;
import com.fluig.identity.swagger.service.model.ApiService;
import org.jboss.logging.Logger;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by paulo.francisco on 29/03/2017.
 */
@RequestScoped
@Transactional
public class ApiServiceImpl implements ApiService {

    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    private ApiDAO apiDAO;

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Optional<ApiDTO> findById(Integer id) {
        LOG.info("findById - id: " + id);
        apiDAO.findById(id);
        return Optional.empty();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ApiDTO> findAll() {
        return apiDAO.findAll().stream().map(e -> ConverterUtils.toDto(e)).collect(Collectors.toList());
    }
}
