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
        "addresses",
        "total_addresses"
})
public class AddressesList implements Serializable
{

    @JsonProperty("addresses")
    private List<Address> addresses = null;
    @JsonProperty("total_addresses")
    private Integer totalAddresses;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -446367508451012423L;

    @JsonProperty("addresses")
    public List<Address> getAddresses() {
        return addresses;
    }

    @JsonProperty("addresses")
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @JsonProperty("total_addresses")
    public Integer getTotalAddresses() {
        return totalAddresses;
    }

    @JsonProperty("total_addresses")
    public void setTotalAddresses(Integer totalAddresses) {
        this.totalAddresses = totalAddresses;
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
