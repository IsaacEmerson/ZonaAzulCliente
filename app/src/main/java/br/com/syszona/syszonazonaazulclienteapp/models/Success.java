package br.com.syszona.syszonazonaazulclienteapp.models;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "message",
        "http_code"
})
public class Success implements Serializable
{

    @JsonProperty("success")
    private String success;
    @JsonProperty("message")
    private String message;
    @JsonProperty("http_code")
    private Integer httpCode;
    private final static long serialVersionUID = 7079984875245613919L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Success() {
    }

    /**
     *
     * @param message
     * @param httpCode
     * @param success
     */
    public Success(String success, String message, Integer httpCode) {
        super();
        this.success = success;
        this.message = message;
        this.httpCode = httpCode;
    }

    @JsonProperty("success")
    public String getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(String success) {
        this.success = success;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("http_code")
    public Integer getHttpCode() {
        return httpCode;
    }

    @JsonProperty("http_code")
    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

}
