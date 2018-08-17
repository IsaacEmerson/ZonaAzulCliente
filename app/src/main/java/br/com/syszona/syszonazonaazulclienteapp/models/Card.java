package br.com.syszona.syszonazonaazulclienteapp.models;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
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
        "type",
        "used",
        "minutes",
        "amount",
        "user_id",
        "city_id",
        "rate_id",
        "vehicle_id",
        "created_at",
        "updated_at"
})
public class Card implements Serializable
{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("used")
    private Boolean used;
    @JsonProperty("minutes")
    private Integer minutes;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("city_id")
    private Integer cityId;
    @JsonProperty("rate_id")
    private Integer rateId;
    @JsonProperty("vehicle_id")
    private Integer vehicleId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8759811461711023182L;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("used")
    public Boolean getUsed() {
        return used;
    }

    @JsonProperty("used")
    public void setUsed(Boolean used) {
        this.used = used;
    }

    @JsonProperty("minutes")
    public Integer getMinutes() {
        return minutes;
    }

    @JsonProperty("minutes")
    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    @JsonProperty("amount")
    public String getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("city_id")
    public Integer getCityId() {
        return cityId;
    }

    @JsonProperty("city_id")
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @JsonProperty("rate_id")
    public Integer getRateId() {
        return rateId;
    }

    @JsonProperty("rate_id")
    public void setRateId(Integer rateId) {
        this.rateId = rateId;
    }

    @JsonProperty("vehicle_id")
    public Integer getVehicleId() {
        return vehicleId;
    }

    @JsonProperty("vehicle_id")
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
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

    @Override
    public String toString() {
        return String.valueOf(getId())+"- Tempo: " + String.valueOf(getMinutes()/60) + "Hrs  Preço: " + getAmount()+" R$" ;
    }

}
