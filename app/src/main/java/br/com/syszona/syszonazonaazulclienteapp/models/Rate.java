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
        "vehicle",
        "vehicle_id",
        "minutes",
        "rate"
})
public class Rate implements Serializable
{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("vehicle")
    private String vehicle;
    @JsonProperty("vehicle_id")
    private Integer vehicleId;
    @JsonProperty("minutes")
    private Integer minutes;
    @JsonProperty("rate")
    private String rate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1937243715237258939L;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("vehicle")
    public String getVehicle() {
        return vehicle;
    }

    @JsonProperty("vehicle")
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    @JsonProperty("vehicle_id")
    public Integer getVehicleId() {
        return vehicleId;
    }

    @JsonProperty("vehicle_id")
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    @JsonProperty("minutes")
    public Integer getMinutes() {
        return minutes;
    }

    @JsonProperty("minutes")
    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    @JsonProperty("rate")
    public String getRate() {
        return rate;
    }

    @JsonProperty("rate")
    public void setRate(String rate) {
        this.rate = rate;
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