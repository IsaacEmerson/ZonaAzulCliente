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
        "total_cards",
        "cards"
})
public class CardList implements Serializable
{

    @JsonProperty("total_cards")
    private Integer totalCards;
    @JsonProperty("cards")
    private List<Card> cards = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8331032071358979685L;

    @JsonProperty("total_cards")
    public Integer getTotalCards() {
        return totalCards;
    }

    @JsonProperty("total_cards")
    public void setTotalCards(Integer totalCards) {
        this.totalCards = totalCards;
    }

    @JsonProperty("cards")
    public List<Card> getCards() {
        return cards;
    }

    @JsonProperty("cards")
    public void setCards(List<Card> cards) {
        this.cards = cards;
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