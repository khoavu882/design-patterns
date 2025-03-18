package ft_factory;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/18/25
 * Time: 10:00 AM
 */

public class MetadataStrategyFactory {
    private final Map<ConnectionType, MetadataStrategy> strategies;

    public MetadataStrategyFactory() {
        // Using EnumMap for better performance with enum keys
        strategies = new EnumMap<>(ConnectionType.class);

        // Map each connection type to its appropriate strategy
        strategies.put(ConnectionType.DATA_LAKE, new DataLakeStrategy());
        strategies.put(ConnectionType.POSTGRESQL, new DatabaseStrategy());
        strategies.put(ConnectionType.MYSQL, new DatabaseStrategy());
        strategies.put(ConnectionType.ORACLE, new DatabaseStrategy());
    }

    public MetadataStrategy getStrategy(ConnectionType type) {
        MetadataStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported connection type: " + type);
        }
        return strategy;
    }
}
