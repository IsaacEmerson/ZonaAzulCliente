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
        "total_plaques",
        "plaques"
})
public class PlaquesList implements Serializable
{

    @JsonProperty("total_plaques")
    private Integer totalPlaques;
    @JsonProperty("plaques")
    private List<Plaque> plaques = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -433723749581090941L;

    @JsonProperty("total_plaques")
    public Integer getTotalPlaques() {
        return totalPlaques;
    }

    @JsonProperty("total_plaques")
    public void setTotalPlaques(Integer totalPlaques) {
        this.totalPlaques = totalPlaques;
    }

    @JsonProperty("plaques")
    public List<Plaque> getPlaques() {
        return plaques;
    }

    @JsonProperty("plaques")
    public void setPlaques(List<Plaque> plaques) {
        this.plaques = plaques;
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