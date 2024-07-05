package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class TreeDiffer {

    public static List<Node> compareData(Map<String, Object> map1, Map<String, Object> map2) {
        List<Node> diff = new ArrayList<>();
        Set<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (!map1.containsKey(key)) {
                diff.add(new Node(key, null, value2, NodeType.ADDED));
            } else if (!map2.containsKey(key)) {
                diff.add(new Node(key, value1, null, NodeType.REMOVED));
            } else if (Objects.equals(value1, value2)) {
                diff.add(new Node(key, value1, value2, NodeType.UNCHANGED));
            } else if (value1 instanceof Map && value2 instanceof Map) {
                @SuppressWarnings("unchecked")
                List<Node> children = compareData((Map<String, Object>) value1, (Map<String, Object>) value2);
                diff.add(new Node(key, null, null, NodeType.COMPOUND, children));
            } else {
                diff.add(new Node(key, value1, value2, NodeType.CHANGED));
            }
        }

        return diff;
    }
}

