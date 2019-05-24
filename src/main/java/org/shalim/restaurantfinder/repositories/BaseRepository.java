package org.shalim.restaurantfinder.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseRepository {
	private String dbPath;
	
	protected BaseRepository(String dbPath) {
		this.dbPath = dbPath;
	}
	
	protected Connection connect() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:" + dbPath);
	}
	
	protected Statement createStatement() throws SQLException {
		Connection connection = connect();
        return connection.createStatement();
	}
	
	protected PreparedStatement prepareStatement(String sql) throws SQLException {
		return connect().prepareStatement(sql);
	}
}
