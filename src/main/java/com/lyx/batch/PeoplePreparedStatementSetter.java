package com.lyx.batch;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;

public class PeoplePreparedStatementSetter implements PreparedStatementSetter {

    public void setValues(PreparedStatement ps) throws SQLException {
        // TODO Auto-generated method stub
        ps.setString(1, "%lyx%");
        ps.setString(2, "%DOE%");
        // ps.setInt(3, 1);
        // ps.setInt(4, 100);
    }
}
