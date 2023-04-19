package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static com.urise.webapp.exception.StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new com.urise.webapp.exception.StorageException(e);
    }
}
