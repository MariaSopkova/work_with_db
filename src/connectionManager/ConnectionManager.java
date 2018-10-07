package connectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
    /*protected String url;
    protected String login;
    protected String password;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }*/

    Connection getConnection() throws SQLException;
}
