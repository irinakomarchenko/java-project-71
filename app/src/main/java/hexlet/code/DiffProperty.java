package hexlet.code;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

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

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiffProperty that = (DiffProperty) o;
        return Objects.equals(key, that.key)
                && type == that.type
                && Objects.equals(oldValue, that.oldValue)
                && Objects.equals(newValue, that.newValue)
                && Objects.equals(children, that.children);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(key, type, oldValue, newValue, children);
    }

    @Override
    public final String toString() {
        return "DiffProperty{"
                + "key='" + key + '\''
                + ", type=" + type
                + ", oldValue=" + oldValue
                + ", newValue=" + newValue
                + ", children=" + children
                + '}';
    }
}
