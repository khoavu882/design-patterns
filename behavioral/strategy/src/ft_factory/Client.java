package ft_factory;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/18/25
 * Time: 10:15 AM
 */

public class Client {
    public static void main(String[] args) {
        MetadataStrategyFactory metadataStrategyFactory = new MetadataStrategyFactory();

        // The connection type could come from configuration
        SyncGateway pgSync = new SyncGatewayImpl(ConnectionType.POSTGRESQL, metadataStrategyFactory);
        SyncGateway lakeSync = new SyncGatewayImpl(ConnectionType.DATA_LAKE, metadataStrategyFactory);

        // Example usage remains the same
        Integer connectionId = 1;
        pgSync.getDatabases(connectionId);
        lakeSync.getDatabases(connectionId);

        pgSync.getTables(connectionId, "example_database");
        lakeSync.getTables(connectionId, "example_database");

        pgSync.getColumns(connectionId, "example_database", "example_table");
        lakeSync.getColumns(connectionId, "example_database", "example_table");
    }
}
