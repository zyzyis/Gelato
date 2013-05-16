/*
 * Copyright (C) Qatar Computing Research Institute, 2013.
 * All rights reserved.
 */

package qa.qcri.nadeef.tools;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.jooq.SQLDialect;

/**
 * Configuration object for JDBC connection.
 */
public class DBConfig {
    private String userName;
    private String password;
    private String url;
    private SQLDialect dialect;

    //<editor-fold desc="Builder pattern">
    /**
     * Builder pattern to build a <code>DBConfig</code> class.
     */
    public static class Builder {
        private String userName = "tester";
        private String password = "tester";
        private String url = "localhost/unittest";
        private SQLDialect dialect = SQLDialect.POSTGRES;

        public Builder username(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder dialect(SQLDialect dialect) {
            this.dialect = dialect;
            return this;
        }

        public DBConfig build() {
            return new DBConfig(userName, password, url, dialect);
        }
    }
    //</editor-fold>

    //<editor-fold desc="Constructor">
    /**
     * Constructor.
     * @param userName DB user name.
     * @param password DB password.
     * @param url DB connection URL.
     */
    public DBConfig(String userName, String password, String url) {
        this(userName, password, url, SQLDialect.POSTGRES);
    }

    /**
     * Constructor.
     * @param userName DB user name.
     * @param password DB password.
     * @param url DB connection URL.
     * @param dialect SQL dialect.
     */
    public DBConfig(String userName, String password, String url, SQLDialect dialect) {
        Preconditions.checkArgument(
                !Strings.isNullOrEmpty(userName) &&
                !Strings.isNullOrEmpty(password) &&
                !Strings.isNullOrEmpty(url)
        );

        this.userName = userName;
        this.password = password;
        this.url = url;
        this.dialect = dialect;
    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public SQLDialect getDialect() {
        return dialect;
    }
    //</editor-fold>
}