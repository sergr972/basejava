package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ABlockOfCodeSql<T> {
    T execute(PreparedStatement preparedStatement) throws SQLException;
}
