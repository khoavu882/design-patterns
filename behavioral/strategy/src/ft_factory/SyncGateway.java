package ft_factory;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/18/25
 * Time: 9:57 AM
 */

public interface SyncGateway {
    String getDatabases(Integer connectionId);

    String getTables(Integer connectionId, String database);

    String getColumns(Integer connectionId, String database, String table);
}
