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
        "name",
        "uf",
        "week",
        "seller_fee",
        "point_of_sale_rate",
        "created_at",
        "updated_at"
})
public class City implements Serializable
{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("uf")
    private String uf;
    @JsonProperty("week")
    private String week;
    @JsonProperty("seller_fee")
    private String sellerFee;
    @JsonProperty("point_of_sale_rate")
    private String pointOfSaleRate;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3404399807893127256L;

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

    @JsonProperty("uf")
    public String getUf() {
        return uf;
    }

    @JsonProperty("uf")
    public void setUf(String uf) {
        this.uf = uf;
    }

    @JsonProperty("week")
    public String getWeek() {
        return week;
    }

    @JsonProperty("week")
    public void setWeek(String week) {
        this.week = week;
    }

    @JsonProperty("seller_fee")
    public String getSellerFee() {
        return sellerFee;
    }

    @JsonProperty("seller_fee")
    public void setSellerFee(String sellerFee) {
        this.sellerFee = sellerFee;
    }

    @JsonProperty("point_of_sale_rate")
    public String getPointOfSaleRate() {
        return pointOfSaleRate;
    }

    @JsonProperty("point_of_sale_rate")
    public void setPointOfSaleRate(String pointOfSaleRate) {
        this.pointOfSaleRate = pointOfSaleRate;
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