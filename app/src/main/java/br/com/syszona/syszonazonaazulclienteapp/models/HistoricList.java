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
        "current_page",
        "data",
        "first_page_url",
        "from",
        "last_page",
        "last_page_url",
        "next_page_url",
        "path",
        "per_page",
        "prev_page_url",
        "to",
        "total"
})
public class HistoricList implements Serializable
{

    @JsonProperty("current_page")
    private Integer currentPage;
    @JsonProperty("data")
    private List<Historic> data = null;
    @JsonProperty("first_page_url")
    private String firstPageUrl;
    @JsonProperty("from")
    private Integer from;
    @JsonProperty("last_page")
    private Integer lastPage;
    @JsonProperty("last_page_url")
    private String lastPageUrl;
    @JsonProperty("next_page_url")
    private Object nextPageUrl;
    @JsonProperty("path")
    private String path;
    @JsonProperty("per_page")
    private Integer perPage;
    @JsonProperty("prev_page_url")
    private Object prevPageUrl;
    @JsonProperty("to")
    private Integer to;
    @JsonProperty("total")
    private Integer total;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2485762196632111932L;

    @JsonProperty("current_page")
    public Integer getCurrentPage() {
        return currentPage;
    }

    @JsonProperty("current_page")
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @JsonProperty("data")
    public List<Historic> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Historic> data) {
        this.data = data;
    }

    @JsonProperty("first_page_url")
    public String getFirstPageUrl() {
        return firstPageUrl;
    }

    @JsonProperty("first_page_url")
    public void setFirstPageUrl(String firstPageUrl) {
        this.firstPageUrl = firstPageUrl;
    }

    @JsonProperty("from")
    public Integer getFrom() {
        return from;
    }

    @JsonProperty("from")
    public void setFrom(Integer from) {
        this.from = from;
    }

    @JsonProperty("last_page")
    public Integer getLastPage() {
        return lastPage;
    }

    @JsonProperty("last_page")
    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    @JsonProperty("last_page_url")
    public String getLastPageUrl() {
        return lastPageUrl;
    }

    @JsonProperty("last_page_url")
    public void setLastPageUrl(String lastPageUrl) {
        this.lastPageUrl = lastPageUrl;
    }

    @JsonProperty("next_page_url")
    public Object getNextPageUrl() {
        return nextPageUrl;
    }

    @JsonProperty("next_page_url")
    public void setNextPageUrl(Object nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("per_page")
    public Integer getPerPage() {
        return perPage;
    }

    @JsonProperty("per_page")
    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    @JsonProperty("prev_page_url")
    public Object getPrevPageUrl() {
        return prevPageUrl;
    }

    @JsonProperty("prev_page_url")
    public void setPrevPageUrl(Object prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    @JsonProperty("to")
    public Integer getTo() {
        return to;
    }

    @JsonProperty("to")
    public void setTo(Integer to) {
        this.to = to;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
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