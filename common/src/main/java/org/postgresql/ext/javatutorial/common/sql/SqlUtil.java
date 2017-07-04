/*
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */


package org.postgresql.ext.javatutorial.common.sql;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Collectors;


public class SqlUtil {
    private static final String PROPERTIES_FILE = "dbconnection.properties";
    private static final Properties PROPERTIES = new Properties();
    static {
        URL propertiesUrl = SqlUtil.class.getClassLoader().getResource(PROPERTIES_FILE);
        if(null == propertiesUrl) {
            throw new RuntimeException("You require a properties file for the connection");
        }

        try(InputStream is = propertiesUrl.openStream()) {
            PROPERTIES.load(is);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface SqlExceptionConsumer<T> {
        void accept(T consumer) throws SQLException;
    }

    public static void connection(SqlExceptionConsumer<Connection> consumer) throws IOException, SQLException {
        try(
                Connection connection = DriverManager.getConnection("jdbc:postgresql:", PROPERTIES)
        ) {
            consumer.accept(connection);
        }
    }

    public static void executeSqlFile(String sqlFile) throws IOException, SQLException {
        URL sqlFileUrl = SqlUtil.class.getClassLoader().getResource(sqlFile);
        if(null == sqlFileUrl) {
            throw new RuntimeException("SQL file not found");
        }

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(sqlFileUrl.openStream()))) {
            executeSql(reader.lines().collect(Collectors.joining()));
        }
    }

    public static void executeSql(String sql) throws IOException, SQLException {
        connection(c -> {
            try(Statement statement = c.createStatement()) {
                statement.execute(sql);
            }
        });
    }
}
