package br.com.syszona.syszonazonaazulclienteapp.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonIgnoreProperties({"role"})

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "email",
        "city_id",
        "street_preference_id",
        "active",
        "pdv",
        "cell_phone",
        "cpf",
        "created_at",
        "updated_at"
})
public class User implements Serializable
{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("city_id")
    private Object cityId;
    @JsonProperty("street_preference_id")
    private Object streetPreferenceId;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("pdv")
    private Boolean pdv;
    @JsonProperty("cell_phone")
    private String cellPhone;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6357433533050009905L;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("city_id")
    public Object getCityId() {
        return cityId;
    }

    @JsonProperty("city_id")
    public void setCityId(Object cityId) {
        this.cityId = cityId;
    }

    @JsonProperty("street_preference_id")
    public Object getStreetPreferenceId() {
        return streetPreferenceId;
    }

    @JsonProperty("street_preference_id")
    public void setStreetPreferenceId(Object streetPreferenceId) {
        this.streetPreferenceId = streetPreferenceId;
    }

    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonProperty("pdv")
    public Boolean getPdv() {
        return pdv;
    }

    @JsonProperty("pdv")
    public void setPdv(Boolean pdv) {
        this.pdv = pdv;
    }

    @JsonProperty("cell_phone")
    public String getCellPhone() {
        return cellPhone;
    }

    @JsonProperty("cell_phone")
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    @JsonProperty("cpf")
    public String getCpf() {
        return cpf;
    }

    @JsonProperty("cpf")
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}