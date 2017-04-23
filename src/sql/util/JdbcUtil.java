package sql.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	private static final String url;
	private static final String userName;
	private static final String password;
	private static final String driverClass;
	
	static {
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("./src/db.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			properties.load(fileInputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		url = properties.getProperty("url");
		userName = properties.getProperty("userName");
		password = properties.getProperty("password");
		driverClass = properties.getProperty("driverClass");
		
		// 1、加载驱动
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 2、获取连接对象
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	// 关闭连接，后打开的先关闭
	public static void close(Connection connection, Statement statement) {
		if (statement != null) {
			try {
				statement.close();
				statement = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 关闭连接，后打开的先关闭
	public static void close(Connection connection, Statement statement,ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
				resultSet = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
				statement = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
