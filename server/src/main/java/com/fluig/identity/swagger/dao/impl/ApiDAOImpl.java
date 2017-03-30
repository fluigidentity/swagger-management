package com.fluig.identity.swagger.dao.impl;

import com.fluig.identity.swagger.api.model.exception.BaseDaoException;
import com.fluig.identity.swagger.dao.model.ApiDAO;
import com.fluig.identity.swagger.dao.model.ApiEntity;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

/**
 * Created by paulo.francisco on 29/03/2017.
 */
@RequestScoped
public class ApiDAOImpl implements ApiDAO{

    private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public ApiEntity insert(ApiEntity entity) throws BaseDaoException {

        try {
            em.persist(entity);
        } catch (Exception e){
            LOG.error("Error to persiste object", e);
            throw new BaseDaoException(BaseDaoException.GENERIC_PERSISTENCE_ERROR, e);
        }

        return entity;
    }

    @Override
    public ApiEntity update(ApiEntity entity) throws BaseDaoException {

        try {
            em.merge(entity);
        } catch (Exception e){
            LOG.error("Error to update object", e);
            throw new BaseDaoException(BaseDaoException.GENERIC_PERSISTENCE_ERROR, e);
        }

        return entity;
    }

    @Override
    public void remove(ApiEntity entity) throws BaseDaoException {
        try {
            em.remove(entity);
        } catch (Exception e){
            LOG.error("Error to remove object", e);
            throw new BaseDaoException(BaseDaoException.GENERIC_PERSISTENCE_ERROR, e);
        }
    }

    @Override
    public Optional<ApiEntity> findById(Integer id) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("findById - id:" + id);
        }

        ApiEntity entity = em.find(ApiEntity.class, id);

        if (LOG.isDebugEnabled()) {
            LOG.debug("findById - result: " + entity);
        }

        return Optional.ofNullable(entity);
    }

    @Override
    public List<ApiEntity> findAll() {
        LOG.debug("findAll");

        List<ApiEntity> result = em.createNamedQuery("ApiEntity.findAll", ApiEntity.class).getResultList();

        if (LOG.isDebugEnabled()) {
            LOG.debug("findAll - result: " + result);
        }

        return result;
    }
}
