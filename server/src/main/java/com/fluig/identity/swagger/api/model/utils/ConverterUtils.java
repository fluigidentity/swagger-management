package com.fluig.identity.swagger.api.model.utils;

import com.fluig.identity.swagger.dao.model.ApiEntity;
import com.fluig.identity.swagger.service.model.ApiDTO;

public final class ConverterUtils {
	private ConverterUtils() {
		super();
	}

	public static ApiDTO toDto(ApiEntity entity) {
		ApiDTO dto = null;
		if (entity != null) {
			dto = new ApiDTO();
			dto.setId(entity.getId());
			dto.setUrl(entity.getUrl());
			dto.setDescription(entity.getDescription());
		}
		return dto;
	}

	public static ApiEntity toEntity(ApiDTO dto) {
		ApiEntity entity = null;
		if (dto != null) {
			entity = new ApiEntity();
			entity.setId(dto.getId());
			entity.setUrl(dto.getUrl());
			entity.setDescription(dto.getDescription());
		}
		return entity;
	}

}
