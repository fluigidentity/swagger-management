package com.fluig.identity.swagger.test.unit;

import com.fluig.identity.swagger.api.model.exception.BaseDaoException;
import com.fluig.identity.swagger.api.model.utils.ConverterUtils;
import com.fluig.identity.swagger.dao.model.ApiDAO;
import com.fluig.identity.swagger.dao.model.ApiEntity;
import com.fluig.identity.swagger.service.impl.ApiServiceImpl;
import com.fluig.identity.swagger.service.model.ApiDTO;
import com.fluig.identity.swagger.service.model.ApiException;
import com.fluig.identity.swagger.service.model.ApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by paulo.francisco on 31/03/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApiServiceTest {

    @InjectMocks
    private ApiService service = spy(new ApiServiceImpl());

    @Mock
    private ApiDAO dao;

    @Test
    public void insertIdMustBeNull(){
        ApiDTO dto = new ApiDTO();
        dto.setId(1);

        try {
            service.insert(dto);
            fail();
        } catch (ApiException e) {
            assertThat(e.getCode(), equalTo(ApiException.ID_MUST_BE_NULL.getCode()));
        }
    }

    @Test
    public void insertBaseDaoException() throws BaseDaoException {
        when(dao.insert(any(ApiEntity.class))).thenThrow(new BaseDaoException(BaseDaoException.GENERIC_PERSISTENCE_ERROR));

        try {
            service.insert(new ApiDTO());
            fail();
        } catch (ApiException e) {
            assertThat(e.getCode(), equalTo(ApiException.API_INSERT_ERROR.getCode()));
        }
    }

    @Test
    public void insert() throws BaseDaoException, ApiException {
        ApiDTO dto = new ApiDTO();
        dto.setUrl("url");
        dto.setDescription("description");

        ApiEntity entity = ConverterUtils.toEntity(dto);
        entity.setId(1);
        dto.setId(1);

        when(dao.insert(any(ApiEntity.class))).thenReturn(entity);

        ApiDTO result = service.insert(new ApiDTO());
        assertThat(result, equalTo(dto));
    }

    @Test
    public void removeNotFound(){
        when(dao.findById(99)).thenReturn(Optional.empty());
        try {
            service.remove(99);
            fail();
        } catch (ApiException e) {
            assertThat(e.getCode(), equalTo(ApiException.API_NOT_FOUND.getCode()));
        }
    }

    @Test
    public void removeBaseDaoException() throws BaseDaoException {
        ApiEntity entity = new ApiEntity();
        entity.setId(99);

        when(dao.findById(entity.getId())).thenReturn(Optional.of(entity));
        doThrow(new BaseDaoException(BaseDaoException.GENERIC_PERSISTENCE_ERROR)).when(dao).remove(entity);
        try {
            service.remove(entity.getId());
            fail();
        } catch (ApiException e) {
            assertThat(e.getCode(), equalTo(ApiException.API_REMOVE_ERROR.getCode()));
        }
    }

    @Test
    public void remove() throws BaseDaoException, ApiException {
        ApiEntity entity = new ApiEntity();
        entity.setId(99);

        when(dao.findById(entity.getId())).thenReturn(Optional.of(entity));
        doNothing().when(dao).remove(entity);
        service.remove(entity.getId());

        verify(dao, times(1)).findById(entity.getId());
        verify(dao, times(1)).remove(entity);
    }

    @Test
    public void findByIdEmpty(){
        when(dao.findById(99)).thenReturn(Optional.empty());

        Optional<ApiDTO> dto = service.findById(99);
        assertThat(dto.isPresent(), equalTo(false));
    }

    @Test
    public void findById(){
        ApiDTO dto = new ApiDTO();
        dto.setId(1);
        dto.setUrl("url");
        dto.setDescription("description");

        ApiEntity entity = ConverterUtils.toEntity(dto);

        when(dao.findById(dto.getId())).thenReturn(Optional.of(entity));

        Optional<ApiDTO> result = service.findById(dto.getId());
        assertThat(result.isPresent(), equalTo(true));
        assertThat(result.get(), equalTo(dto));
    }

    @Test
    public void findAllEmpty(){
        when(dao.findAll()).thenReturn(Collections.emptyList());

        List<ApiDTO> result = service.findAll();

        assertThat(result, hasSize(0));
    }

    @Test
    public void findAll(){
        List<ApiEntity> entities = new ArrayList<>();
        ApiEntity entity = new ApiEntity();
        entity.setId(1);
        entities.add(entity);
        when(dao.findAll()).thenReturn(entities);

        List<ApiDTO> result = service.findAll();

        assertThat(result, hasSize(1));
        assertThat(result.get(0).getId(), equalTo(1));
    }

    @Test
    public void updateIdMustNotBeNull(){
        try {
            service.update(new ApiDTO());
            fail();
        } catch (ApiException e) {
            assertThat(e.getCode(), equalTo(ApiException.ID_MUST_NOT_BE_NULL.getCode()));
        }
    }

    @Test
    public void updateNotFound(){
        ApiDTO dto = new ApiDTO();
        dto.setId(1);

        when(dao.findById(dto.getId())).thenReturn(Optional.empty());

        try {
            service.update(dto);
            fail();
        } catch (ApiException e) {
            assertThat(e.getCode(), equalTo(ApiException.API_NOT_FOUND.getCode()));
        }
    }

    @Test
    public void updateBaseDaoException() throws BaseDaoException {
        ApiDTO dto = new ApiDTO();
        dto.setId(1);

        when(dao.findById(dto.getId())).thenReturn(Optional.of(ConverterUtils.toEntity(dto)));
        when(dao.update(any(ApiEntity.class))).thenThrow(new BaseDaoException(BaseDaoException.GENERIC_PERSISTENCE_ERROR));

        try {
            service.update(dto);
            fail();
        } catch (ApiException e) {
            assertThat(e.getCode(), equalTo(ApiException.API_UPDATE_ERROR.getCode()));
        }
    }

    @Test
    public void update() throws BaseDaoException, ApiException {
        ApiDTO dto = new ApiDTO();
        dto.setId(1);
        dto.setUrl("url");
        dto.setDescription("description");

        ApiEntity entity = ConverterUtils.toEntity(dto);
        entity.setId(1);

        ApiEntity newEntity = ConverterUtils.toEntity(dto);
        newEntity.setUrl("new url");

        when(dao.findById(dto.getId())).thenReturn(Optional.of(entity));
        when(dao.update(any(ApiEntity.class))).thenReturn(newEntity);

        ApiDTO result = service.update(dto);
        assertThat(result, equalTo(dto));
        assertThat(result.getDescription(), equalTo(newEntity.getDescription()));
    }
}
