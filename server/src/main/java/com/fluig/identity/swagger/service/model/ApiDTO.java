package com.fluig.identity.swagger.service.model;

import java.io.Serializable;

/**
 * Created by paulo.francisco on 29/03/2017.
 */
public class ApiDTO implements Serializable {
    private Integer id;

    private String url;

    private String description;

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiDTO apiDTO = (ApiDTO) o;

        return id != null ? id.equals(apiDTO.id) : apiDTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
