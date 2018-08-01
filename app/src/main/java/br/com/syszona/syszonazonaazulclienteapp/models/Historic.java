package br.com.syszona.syszonazonaazulclienteapp.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "type",
        "total_cards",
        "vehicle_id",
        "city_id",
        "amount",
        "amount_before",
        "amount_after",
        "user_id",
        "created_at",
        "updated_at"
})
public class Historic implements Serializable
{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("total_cards")
    private Integer totalCards;
    @JsonProperty("vehicle_id")
    private Integer vehicleId;
    @JsonProperty("city_id")
    private Integer cityId;
    @JsonProperty("amount")
    private Integer amount;
    @JsonProperty("amount_before")
    private Integer amountBefore;
    @JsonProperty("amount_after")
    private Integer amountAfter;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6293753499229487294L;

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

    @JsonProperty("total_cards")
    public Integer getTotalCards() {
        return totalCards;
    }

    @JsonProperty("total_cards")
    public void setTotalCards(Integer totalCards) {
        this.totalCards = totalCards;
    }

    @JsonProperty("vehicle_id")
    public Integer getVehicleId() {
        return vehicleId;
    }

    @JsonProperty("vehicle_id")
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    @JsonProperty("city_id")
    public Integer getCityId() {
        return cityId;
    }

    @JsonProperty("city_id")
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @JsonProperty("amount")
    public Integer getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonProperty("amount_before")
    public Integer getAmountBefore() {
        return amountBefore;
    }

    @JsonProperty("amount_before")
    public void setAmountBefore(Integer amountBefore) {
        this.amountBefore = amountBefore;
    }

    @JsonProperty("amount_after")
    public Integer getAmountAfter() {
        return amountAfter;
    }

    @JsonProperty("amount_after")
    public void setAmountAfter(Integer amountAfter) {
        this.amountAfter = amountAfter;
    }

    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
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
    public String toString(){
        String type = "";
        if(getType().equals("U")){
            type = "Usou Cad";
        }else if(getType().equals("T")){
            type = "Credito por cads";
        }else if(getType().equals("C")){
            type = "Comprou créditos";
        }
        String totalCads = "";
        if (getTotalCards()!= null){
            totalCads = getTotalCards().toString();
        }
        return "-- "+type+" --\n Valor Atual(Créditos) : "+getAmount().toString()+"\n Valor Antes(Créditos) : "+getAmountBefore().toString()+"\n Valor Depois(Créditos) : "+getAmountAfter().toString()+"\n  Cads : "+totalCads;
    }

}
