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
        "password",
        "confirm_token",
        "created_at",
        "updated_at"
})
public class UserInitialData implements Serializable
{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("confirm_token")
    private String confirmToken;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private final static long serialVersionUID = 3912816456044158477L;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserInitialData() {
    }

    /**
     *
     * @param updatedAt
     * @param id
     * @param confirmToken
     * @param email
     * @param createdAt
     * @param name
     * @param password
     */
    public UserInitialData(Integer id, String name, String email, String password, String confirmToken, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmToken = confirmToken;
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

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("confirm_token")
    public String getConfirmToken() {
        return confirmToken;
    }

    @JsonProperty("confirm_token")
    public void setConfirmToken(String confirmToken) {
        this.confirmToken = confirmToken;
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