package com.pranv.cruddemp;

import com.pranv.cruddemp.dao.StudentDAO;
import com.pranv.cruddemp.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CruddempApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddempApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			Scanner in = new Scanner(System.in);
//			boolean flag = true;
//			String response;
//			while(flag){
//				System.out.println("Do you want to create new student? Y or N");
//				response = in.next().trim();
//
//				if (response.equalsIgnoreCase("Y")) {
//					System.out.println("Enter First Name of Student");
//					String fName = in.next().trim();
//					System.out.println("Enter lasr Name of Student");
//					String lName = in.next().trim();
//					System.out.println("Enter email of Student");
//					String email = in.next().trim();
//					createStudent(studentDAO,fName,lName,email);
//				}
//				else if (response.equalsIgnoreCase("N")){
//					System.out.println("Adios");
//					flag = false;
//				}
//				else{
//					System.out.println("Invalid response");
//				}
//			}

//			readStudent(StudentDAO);

		//	queryAllStudents(studentDAO);

			//queryByLastName(studentDAO);

			//updateStudent(studentDAO);

			//deleteStudent(studentDAO);

			// deleteAllStudents(studentDAO);

		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int count = studentDAO.deleteAll();
		System.out.println(count + " students are deleted from DB");

	}

	private void deleteStudent(StudentDAO studentDAO) {

		studentDAO.delete(4);
	}

	private void updateStudent(StudentDAO studentDAO) {

		Student tempStudent = studentDAO.findById(2);

		tempStudent.setLastName("Reddy");

		studentDAO.update(tempStudent);


	}

	private void createStudent(StudentDAO studentDAO, String fName, String lName, String email) {

		//create Student Object
		System.out.println("Creating New Student");
		Student tempStudent = new Student(fName,lName,email);

		//save the student in DB
		System.out.println("Saving the student in DB");
		studentDAO.save(tempStudent);

		//display id of generated student
		System.out.println("Student created successfully. Generated ID: "+ tempStudent.getId());

		Student student = studentDAO.findById(tempStudent.getId());
		System.out.println("Created Student:" + student);


	}

	private void queryAllStudents(StudentDAO studentDAO){
		List<Student> studentList = studentDAO.findAll();

		for (Student student: studentList){
			System.out.println(student);
		}
	}

	private void queryByLastName(StudentDAO studentDAO){
		Scanner in  = new Scanner(System.in);
		System.out.print("Enter Last Name: ");
		String lastName = in.next().trim();
		System.out.println();

		List<Student> tempStudents = studentDAO.findByLastName(lastName);

		if (tempStudents.isEmpty()){
			System.out.println("nothing");
		}
		else{
		for (Student student : tempStudents){
			System.out.println(student);
			}
		}
	}
}
