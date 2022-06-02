package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBodyPojo {

    private String status;
    private DummyApiDataPojo data;
    private String message;

    public ResponseBodyPojo() {
    }

    public ResponseBodyPojo(String status, DummyApiDataPojo data, String message) {
        super();
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyApiDataPojo getData() {
        return data;
    }

    public void setData(DummyApiDataPojo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseBodyPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
