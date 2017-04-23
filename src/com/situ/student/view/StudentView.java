package com.situ.student.view;

import java.util.List;
import java.util.Scanner;

import com.situ.student.entity.Student;
import com.situ.student.service.IStudentService;
import com.situ.student.service.impl.StudentServiceImpl;

public class StudentView {
	private Scanner scanner = null;
	private IStudentService studentService = null;

	public static void main(String[] args) {
		StudentView studentView = new StudentView();
		studentView.init();
		studentView.showView();
	}

	// 初始化
	private void init() {
		scanner = new Scanner(System.in);
		studentService = new StudentServiceImpl();
	}

	private void showView() {
		while (true) {
			System.out.println("-------学生信息管理系统----------");
			System.out.println("1、添加学生信息 ");
			System.out.println("2、删除学生信息 ");
			System.out.println("3、修改学生信息 ");
			System.out.println("4、查询学生信息 ");
			System.out.println("0、退出程序  ");
			System.out.print("请选择功能: ");
			int type = scanner.nextInt();
			if (type == 0) {
				System.out.println("退出程序！");
				break;
			}

			switch (type) {
			case 1:// 添加学生信息
				addStudentInfos();
				break;
			case 2:// 删除学生信息
				deleteStudentInfos();
				break;
			case 3:// 修改学生信息
				updateStudentInfos();
				break;
			case 4:// 查询学生信息
				showStudentInfos();
				break;

			default:
				break;
			}
		}
	}

	private void addStudentInfos() {
		System.out.println("------添加学生信息------");
		System.out.print("请输入学生姓名： ");
		String name = scanner.next();
		System.out.print("请输入学生年龄： ");
		int age = scanner.nextInt();
		System.out.print("请输入学生性别： ");
		String gender = scanner.next();

		Student student = new Student(name, age, gender);
		boolean result = studentService.add(student);
		if (result) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}

	private void deleteStudentInfos() {
		showStudentInfos();
		System.out.print("请输入要删除的学生的id: ");
		int id = scanner.nextInt();
		boolean result = studentService.delete(id);
		if (result) {
			System.out.println("删除成功！");
		} else {
			System.out.println("删除失败！");
		}
	}

	private void updateStudentInfos() {
		showStudentInfos();
		System.out.print("请输入要修改的学生的id: ");
		int id = scanner.nextInt();
		Student student = studentService.findById(id);
		System.out.println("1、修改学生姓名 2、修改学生年龄 3、修改学生性别");
		int type = scanner.nextInt();
		switch (type) {
		case 1:
			System.out.println("请输入需要修改学生的姓名： ");
			String name = scanner.next();
			student.setName(name);
			break;
		case 2:
			System.out.println("请输入需要修改学生的年龄： ");
			int age = scanner.nextInt();
			student.setAge(age);
			break;
		case 3:
			System.out.println("请输入需要修改学生的性别： ");
			String gender = scanner.next();
			student.setGender(gender);
			break;
		default:
			break;
		}

		boolean result = studentService.updae(student);
		if (result) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
	}

	private void showStudentInfos() {
		System.out.println("------所有学生信息------");
		List<Student> list = studentService.findAll();
		for (Student student : list) {
			System.out.println(student);
		}
	}
}
