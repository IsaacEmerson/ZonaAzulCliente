package br.com.syszona.syszonazonaazulclienteapp.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "user_id",
        "zip_code",
        "number",
        "city",
        "state",
        "street",
        "neighborhood",
        "complement",
        "debit_card",
        "receive_sms",
        "receive_email",
        "birth_date",
        "created_at",
        "updated_at"
})
public class UserData implements Serializable
{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("zip_code")
    private String zipCode;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("street")
    private String street;
    @JsonProperty("neighborhood")
    private String neighborhood;
    @JsonProperty("complement")
    private String complement;
    @JsonProperty("debit_card")
    private Integer debitCard;
    @JsonProperty("receive_sms")
    private Integer receiveSms;
    @JsonProperty("receive_email")
    private Integer receiveEmail;
    @JsonProperty("birth_date")
    private String birthDate;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4823061165048607730L;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("zip_code")
    public String getZipCode() {
        return zipCode;
    }

    @JsonProperty("zip_code")
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @JsonProperty("number")
    public Integer getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(Integer number) {
        this.number = number;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("street")
    public String getStreet() {
        return street;
    }

    @JsonProperty("street")
    public void setStreet(String street) {
        this.street = street;
    }

    @JsonProperty("neighborhood")
    public String getNeighborhood() {
        return neighborhood;
    }

    @JsonProperty("neighborhood")
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    @JsonProperty("complement")
    public String getComplement() {
        return complement;
    }

    @JsonProperty("complement")
    public void setComplement(String complement) {
        this.complement = complement;
    }

    @JsonProperty("debit_card")
    public Integer getDebitCard() {
        return debitCard;
    }

    @JsonProperty("debit_card")
    public void setDebitCard(Integer debitCard) {
        this.debitCard = debitCard;
    }

    @JsonProperty("receive_sms")
    public Integer getReceiveSms() {
        return receiveSms;
    }

    @JsonProperty("receive_sms")
    public void setReceiveSms(Integer receiveSms) {
        this.receiveSms = receiveSms;
    }

    @JsonProperty("receive_email")
    public Integer getReceiveEmail() {
        return receiveEmail;
    }

    @JsonProperty("receive_email")
    public void setReceiveEmail(Integer receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    @JsonProperty("birth_date")
    public String getBirthDate() {
        return birthDate;
    }

    @JsonProperty("birth_date")
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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