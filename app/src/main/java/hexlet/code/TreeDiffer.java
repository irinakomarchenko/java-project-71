package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;


public class TreeDiffer {
    public static List<DiffProperty> compareData(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        List<DiffProperty> diff = new ArrayList<>();

        for (String key : keys) {
            if (!map1.containsKey(key)) {
                diff.add(new DiffProperty(key, null, map2.get(key), DiffType.ADDED));
            } else if (!map2.containsKey(key)) {
                diff.add(new DiffProperty(key, map1.get(key), null, DiffType.REMOVED));
            } else {
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);
                if (value1.equals(value2)) {
                    diff.add(new DiffProperty(key, value1, value2, DiffType.UNCHANGED));
                } else {
                    diff.add(new DiffProperty(key, value1, value2, DiffType.CHANGED));
                }
            }
        }
        return diff;
    }
}
