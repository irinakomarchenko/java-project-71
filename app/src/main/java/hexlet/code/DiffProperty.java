package hexlet.code;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "key", "type", "oldValue", "newValue", "children" })
public class DiffProperty {
    private final String key;
    private final NodeType type;
    private final Object oldValue;
    private final Object newValue;
    private final List<DiffProperty> children;

    public DiffProperty(String key, Object oldValue, Object newValue, NodeType type, List<DiffProperty> children) {
        this.key = key;
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.children = children;
    }
}
