package com.fluig.identity.swagger.service.impl;

import com.fluig.identity.swagger.api.model.exception.BaseDaoException;
import com.fluig.identity.swagger.api.model.utils.ConverterUtils;
import com.fluig.identity.swagger.dao.model.ApiDAO;
import com.fluig.identity.swagger.dao.model.ApiEntity;
import com.fluig.identity.swagger.service.model.ApiDTO;
import com.fluig.identity.swagger.service.model.ApiException;
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
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ApiDTO insert(ApiDTO dto) throws BaseDaoException {
        ApiEntity entity = null;

        try {
            entity = apiDAO.insert(ConverterUtils.toEntity(dto));
        } catch (BaseDaoException e) {
            LOG.error("Error to insert object", e);
            throw new BaseDaoException(ApiException.API_INSERT_ERROR, e);
        }

        return ConverterUtils.toDto(entity);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(Integer id) throws ApiException {
        try {
            Optional<ApiEntity> entity = apiDAO.findById(id);
            if(!entity.isPresent()){
                throw new ApiException(ApiException.API_NOT_FOUND);
            }

            apiDAO.remove(entity.get());
        } catch (BaseDaoException e) {
            LOG.error("Error to remove object", e);
            throw new ApiException(ApiException.API_REMOVE_ERROR, e);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Optional<ApiDTO> findById(Integer id) {
        LOG.info("findById - id: " + id);

        Optional<ApiEntity> entity = apiDAO.findById(id);
        if(entity.isPresent()){
            return Optional.of(ConverterUtils.toDto(entity.get()));
        }

        return Optional.empty();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ApiDTO> findAll() {
        return apiDAO.findAll().stream().map(e -> ConverterUtils.toDto(e)).collect(Collectors.toList());
    }
}
