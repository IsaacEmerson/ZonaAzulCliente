package br.com.syszona.syszonazonaazulclienteapp.models;

        import java.io.Serializable;
        import java.util.List;
        import com.fasterxml.jackson.annotation.JsonInclude;
        import com.fasterxml.jackson.annotation.JsonProperty;
        import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "total_active_plaques",
        "active_plaques"
})
public class ActivePlaques implements Serializable
{

    @JsonProperty("total_active_plaques")
    private Integer totalActivePlaques;
    @JsonProperty("active_plaques")
    private List<ActivePlaque> activePlaques = null;
    private final static long serialVersionUID = -6163386682513409314L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ActivePlaques() {
    }

    /**
     *
     * @param activePlaques
     * @param totalActivePlaques
     */
    public ActivePlaques(Integer totalActivePlaques, List<ActivePlaque> activePlaques) {
        super();
        this.totalActivePlaques = totalActivePlaques;
        this.activePlaques = activePlaques;
    }

    @JsonProperty("total_active_plaques")
    public Integer getTotalActivePlaques() {
        return totalActivePlaques;
    }

    @JsonProperty("total_active_plaques")
    public void setTotalActivePlaques(Integer totalActivePlaques) {
        this.totalActivePlaques = totalActivePlaques;
    }

    @JsonProperty("active_plaques")
    public List<ActivePlaque> getActivePlaques() {
        return activePlaques;
    }

    @JsonProperty("active_plaques")
    public void setActivePlaques(List<ActivePlaque> activePlaques) {
        this.activePlaques = activePlaques;
    }

}