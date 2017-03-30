package com.fluig.identity.swagger.dao.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by paulo.francisco on 29/03/2017.
 */
@Entity
@Table(name = "APIS")
@NamedQueries({
        @NamedQuery(name="ApiEntity.findAll", query="SELECT api FROM ApiEntity api")
})
public class ApiEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

        ApiEntity apiEntity = (ApiEntity) o;

        return id != null ? id.equals(apiEntity.id) : apiEntity.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
