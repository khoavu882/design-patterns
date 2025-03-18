package ft_factory;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/18/25
 * Time: 9:59 AM
 */

public class SyncGatewayImpl implements SyncGateway {

    private ConnectionType connectionType;
    private MetadataStrategyFactory metadataStrategyFactory;

    public SyncGatewayImpl(ConnectionType connectionType, MetadataStrategyFactory metadataStrategyFactory) {
        this.connectionType = connectionType;
        this.metadataStrategyFactory = metadataStrategyFactory;
    }

    @Override
    public String getDatabases(Integer connectionId) {
        return metadataStrategyFactory.getStrategy(connectionType).getDatabases(connectionId);
    }

    @Override
    public String getTables(Integer connectionId, String database) {
        return metadataStrategyFactory.getStrategy(connectionType).getTables(connectionId, database);
    }

    @Override
    public String getColumns(Integer connectionId, String database, String table) {
        return metadataStrategyFactory.getStrategy(connectionType).getColumns(connectionId, database, table);
    }
}
