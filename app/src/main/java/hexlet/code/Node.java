package hexlet.code;

import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class Node {
    private final String key;
    private final NodeType type;
    private final Object oldValue;
    private final Object newValue;
    private final List<Node> children;

    public Node(String key, Object oldValue, Object newValue, NodeType type) {
        this(key, oldValue, newValue, type, null);
    }

    public Node(String key, Object oldValue, Object newValue, NodeType type, List<Node> children) {
        this.key = key;
        this.type = type;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.children = children;
    }

    public static List<DiffProperty> toDiffProperties(List<Node> nodes) {
        return nodes.stream()
                .map(node -> new DiffProperty(
                        node.getKey(),
                        node.getOldValue(),
                        node.getNewValue(),
                        node.getType(),
                        node.getChildren() != null ? toDiffProperties(node.getChildren()) : null
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(key, node.key)
                && type == node.type
                && Objects.equals(oldValue, node.oldValue)
                && Objects.equals(newValue, node.newValue)
                && Objects.equals(children, node.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, type, oldValue, newValue, children);
    }

    @Override
    public String toString() {
        return "Node{"
                +  "key='" + key + '\''
                +  ", type=" + type
                +  ", oldValue=" + oldValue
                +  ", newValue=" + newValue
                +  ", children=" + children
                +  '}';
    }
}
