package br.com.syszona.syszonazonaazulclienteapp.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "plaque",
        "time",
        "vehicle",
        "vehicle_id",
        "city",
        "expired",
        "time_left"
})
public class ActivePlaque implements Serializable
{

    @JsonProperty("plaque")
    private String plaque;
    @JsonProperty("time")
    private Integer time;
    @JsonProperty("vehicle")
    private String vehicle;
    @JsonProperty("vehicle_id")
    private Integer vehicleId;
    @JsonProperty("city")
    private String city;
    @JsonProperty("expired")
    private Boolean expired;
    @JsonProperty("time_left")
    private String timeLeft;
    private final static long serialVersionUID = -1843363280489228548L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ActivePlaque() {
    }

    /**
     *
     * @param time
     * @param expired
     * @param vehicleId
     * @param vehicle
     * @param timeLeft
     * @param plaque
     * @param city
     */
    public ActivePlaque(String plaque, Integer time, String vehicle, Integer vehicleId, String city, Boolean expired, String timeLeft) {
        super();
        this.plaque = plaque;
        this.time = time;
        this.vehicle = vehicle;
        this.vehicleId = vehicleId;
        this.city = city;
        this.expired = expired;
        this.timeLeft = timeLeft;
    }

    @JsonProperty("plaque")
    public String getPlaque() {
        return plaque;
    }

    @JsonProperty("plaque")
    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    @JsonProperty("time")
    public Integer getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Integer time) {
        this.time = time;
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

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("expired")
    public Boolean getExpired() {
        return expired;
    }

    @JsonProperty("expired")
    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    @JsonProperty("time_left")
    public String getTimeLeft() {
        return timeLeft;
    }

    @JsonProperty("time_left")
    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    @Override
    public String toString() {
        return String.valueOf(getPlaque() +" "+String.valueOf(getTime()/60)+"Hrs "+getVehicle()+" Restante: " + getTimeLeft()+"");
    }

}