package autotests.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DuckListMes {
    @JsonProperty
    private List<String> value;
}
