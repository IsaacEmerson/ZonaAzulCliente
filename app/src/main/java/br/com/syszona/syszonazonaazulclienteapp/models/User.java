package br.com.syszona.syszonazonaazulclienteapp.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "email",
        "city_id",
        "active",
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
    private Integer cityId;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("cell_phone")
    private Object cellPhone;
    @JsonProperty("cpf")
    private Object cpf;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private final static long serialVersionUID = 8589120119470569956L;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param cityId
     * @param email
     * @param createdAt
     * @param name
     * @param cellPhone
     * @param active
     * @param cpf
     */
    public User(Integer id, String name, String email, Integer cityId, Boolean active, Object cellPhone, Object cpf, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.cityId = cityId;
        this.active = active;
        this.cellPhone = cellPhone;
        this.cpf = cpf;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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
    public Integer getCityId() {
        return cityId;
    }

    @JsonProperty("city_id")
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonProperty("cell_phone")
    public Object getCellPhone() {
        return cellPhone;
    }

    @JsonProperty("cell_phone")
    public void setCellPhone(Object cellPhone) {
        this.cellPhone = cellPhone;
    }

    @JsonProperty("cpf")
    public Object getCpf() {
        return cpf;
    }

    @JsonProperty("cpf")
    public void setCpf(Object cpf) {
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

}