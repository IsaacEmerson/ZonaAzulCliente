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
        "cities_id",
        "cities_current",
        "created_at",
        "updated_at"
})
public class User implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("cities_id")
    private Integer citiesId;
    @JsonProperty("cities_current")
    private Integer citiesCurrent;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private final static long serialVersionUID = 7941754445266185113L;

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

    @JsonProperty("cities_id")
    public Integer getCitiesId() {
        return citiesId;
    }

    @JsonProperty("cities_id")
    public void setCitiesId(Integer citiesId) {
        this.citiesId = citiesId;
    }

    @JsonProperty("cities_current")
    public Integer getCitiesCurrent() {
        return citiesCurrent;
    }

    @JsonProperty("cities_current")
    public void setCitiesCurrent(Integer citiesCurrent) {
        this.citiesCurrent = citiesCurrent;
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
