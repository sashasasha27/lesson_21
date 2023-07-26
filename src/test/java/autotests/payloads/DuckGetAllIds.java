package autotests.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Accessors(fluent = true)

public class DuckGetAllIds {
    @JsonProperty
    private List<String> allIds;
}
