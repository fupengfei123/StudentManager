package com.situ.student.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sql.util.JdbcUtil;
import com.situ.student.dao.IStudentDao;
import com.situ.student.entity.Student;

public class StudentDaoMySqlImpl implements IStudentDao {

	@Override
	public boolean add(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = JdbcUtil.getConnection();
		String sql = "insert into student(name,age,gender) values(?,?,?);";
		boolean result = false;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getGender());
			preparedStatement.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(connection, preparedStatement);
		}

		return result;
	}

	@Override
	public boolean delete(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = JdbcUtil.getConnection();
		String sql = "delete from student where id=?;";
		boolean result = false;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(connection, preparedStatement);
		}

		return result;
	}

	@Override
	public boolean updae(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		connection = JdbcUtil.getConnection();
		String sql = "update student set name=?,age=?,gender=? where id=?";
		boolean result = false;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getGender());
			preparedStatement.setInt(4, student.getId());
			preparedStatement.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(connection, preparedStatement);
		}

		return result;
	}

	@Override
	public Student findById(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Student student = null;
		connection = JdbcUtil.getConnection();
		String sql = "select * from student where id=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int studentId = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				student = new Student(studentId, name, age, gender);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(connection, preparedStatement);
		}

		return student;
	}

	@Override
	public List<Student> findAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<Student>();

		connection = JdbcUtil.getConnection();
		String sql = "select * from student;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			Student student = null;
			while (resultSet.next()) {
				int studentId = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				student = new Student(studentId, name, age, gender);
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(connection, preparedStatement);
		}

		return list;
	}

}
