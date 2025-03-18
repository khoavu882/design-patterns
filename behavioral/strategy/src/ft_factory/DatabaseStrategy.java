package ft_factory;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/18/25
 * Time: 10:03 AM
 */

public class DatabaseStrategy implements MetadataStrategy {
    @Override
    public String getDatabases(Integer connectionId) {
        String databases = "Databases wih connection type: " + ConnectionType.POSTGRESQL;
        System.out.println(databases);
        return databases;
    }

    @Override
    public String getTables(Integer connectionId, String database) {
        String tables = "Tables wih connection type: " + ConnectionType.POSTGRESQL;
        System.out.println(tables);
        return tables;
    }

    @Override
    public String getColumns(Integer connectionId, String database, String table) {
        String columns = "Columns wih connection type: " + ConnectionType.POSTGRESQL;
        System.out.println(columns);
        return columns;
    }
}
