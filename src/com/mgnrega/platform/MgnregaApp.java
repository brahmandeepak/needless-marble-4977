package com.mgnrega.platform;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import com.mgnrega.bean.*;
import com.mnrega.dao.MgInterImpl;

public class MgnregaApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		try {

			MgInterImpl mig = new MgInterImpl();

			String log = "";

			System.out.println("\n ⚜⚜⚜⚜⚜ MGNREGA(The Mahatma Gandhi National Rural Employment Guarantee Act) ⚜⚜⚜⚜⚜ ");
			System.out.println();
			System.out.println();

			System.out.println("Press 1 for Login as a BDO.");
			System.out.println("Press 2 for Login as a GPM.");

			String x = sc.next();

			if (x.equals("1")) {
				System.out.println("Enter userName: ");
				String username = sc.next();
				System.out.println("Enter password: ");
				String password = sc.next();

				if (username.equalsIgnoreCase("BDO") && password.equalsIgnoreCase("BDO")) {
					log = "1";
				} else {
					System.out.println("Incorrect username or password.....");
				}
			} else if (x.equals("2")) {

				System.out.println("Enter Email Id Of GPM: ");
				String username = sc.next();
				System.out.println("Enter Password: ");
				String password = sc.next();

				if (mig.loginAsGpm(username, password)) {
					log = "2";
				} else {
					System.out.println("Incorrect Username OR Password.....");
				}
			}

			if (log.equals("1")) {

				while (true) {

					System.out.println("\n Press 1 for create a project");
					System.out.println("\n press 2 for View List Of project");
					System.out.println("\n prees 3 for creating Gram Panchayat Member");
					System.out.println("\n press 4 for View all the GPM");
					System.out.println("\n press 5 for Allocate project to GPM");
					System.out.println("\n Press 6 See List of Employee working on that Project and their wages.");
					System.out.println("\n press 0 for Exit");

					String bd = sc.next();

					if (bd.equals("1")) {
						try {
							System.out.println("Enter project ID :");
							int projid = sc.nextInt();

							System.out.println("Enter project Name :");
							sc.nextLine();
							String projname = sc.nextLine();

							System.out.println("Enter the project description :");
							String projdesc = sc.nextLine();

							System.out.println("Enter the project duration (Number of months) :");
							int duration = sc.nextInt();

							System.out.println("Enter the project starting date in the form of yyyy-mm-dd :");
							String date = sc.next();

							System.out.println();
							System.out.println();
							System.out.println("<------------------------------------------------------->");
							System.out.println(mig.addProject(projid, projname, projdesc, duration, date));
							System.out.println("<------------------------------------------------------->");
							System.out.println();
							System.out.println();

						} catch (InputMismatchException e) {
							System.out.println("<------------------------------------------------------->");
							System.out.println("✖✖✖✖---Please Enter Valid Data---✖✖✖✖");
							System.out.println("<------------------------------------------------------->");
							break;
						}

					} else if (bd.equals("2")) {

						List<Project> pp = mig.viewListOfProject();

						for (Project p : pp) {
							System.out.println("<------------------------------------------------------->");
							System.out.println("Project Id is            : " + p.getProjid());
							System.out.println("Project name is          : " + p.getProjname());
							System.out.println("Project description is   : " + p.getProjdesc());
							System.out.println("Project duration is      : " + p.getPro_duration());
							System.out.println("Project starting date is : " + p.getProj_startdate());
							System.out.println("<------------------------------------------------------->");
						}

					} else if (bd.equals("3")) {
						try {
							System.out.println("Enter GPM name :");
							String gpmname = sc.next();

							System.out.println("Enter the GPM Mobile :");
							String gpmmobile = sc.next();

							System.out.println("Enter the GPM email address :");
							String gpmemail = sc.next();

							System.out.println("Enter the village name :");
							String gpmvill = sc.next();

							System.out.println("Enter the GPM aadhar no. :");
							String gpmaadhar = sc.next();

							System.out.println("Enter the GPM password :");
							String password = sc.next();

							System.out.println("<------------------------------------------------------->");
							System.out.println(mig.addGPM(gpmname, gpmmobile, gpmemail, gpmvill, gpmaadhar, password));
							System.out.println("<------------------------------------------------------->");

						} catch (InputMismatchException e) {
							System.out.println("<------------------------------------------------------->");
							System.out.println("✖✖✖✖---Please Enter Valid Data---✖✖✖✖");
							System.out.println("<------------------------------------------------------->");
							break;
						}
					} else if (bd.equals("4")) {

						List<Gpm> gg = mig.viewAllGPM();

						for (Gpm g : gg) {
							System.out.println("<------------------------------------------------------->");
							System.out.println("GPM Id is           : " + g.getgpmid());
							System.out.println("GPM name is         : " + g.getGpmname());
							System.out.println("GPM Mobile no  is   : " + g.getGpmmobile());
							System.out.println("GPM email  is       : " + g.getGpmemail());
							System.out.println("GPM village name is : " + g.getGpmvill());
							System.out.println("GPM Aadhar no is    : " + g.getGpmaadhar());
							System.out.println("<------------------------------------------------------->");

						}

					} else if (bd.equals("5")) {
						try {
							System.out.println("\n Enter the project Id in which you want to Allocate the GPM :");
							int projid = sc.nextInt();

							System.out.println("\n Enter the GPM Id whom you want to Allocate in the above project :");
							int gpmid = sc.nextInt();

							System.out.println("<------------------------------------------------------->");
							System.out.println(mig.allocateProjectToGpm(projid, gpmid));
							System.out.println("<------------------------------------------------------->");

						} catch (InputMismatchException e) {
							System.out.println("<------------------------------------------------------->");
							System.out.println("✖✖✖✖---Please Enter Valid Data---✖✖✖✖");
							System.out.println("<------------------------------------------------------->");
							break;
						}

					} else if (bd.equals("6")) {

						System.out.println(
								"Enter project name to View total number of days Employee worked in a project and also their wages.");
						sc.nextLine();
						String proj = sc.nextLine();

						List<EmpProj> ep = mig.listOfEmpOnProject(proj);
						// System.out.println(ep);
						for (EmpProj em : ep) {
							System.out.println("<------------------------------------------------------->");
							System.out.println(" project name is            : " + em.getProjname());
							System.out.println(" employee name is           : " + em.getEmpname());
							System.out.println(" employee address is        : " + em.getEmpadd());
							System.out.println(" employee mobile is         : " + em.getEmpmobile());
							System.out.println(" total days employee worked : " + em.getTotal_days());
							System.out.println(" employee wages is          : " + em.getEmpwages());

							System.out.println("<------------------------------------------------------->");

						}

					} else if (bd.equals("0")) {
						System.out.println("<-------\n Thanks for visting..... See You Later... 😊--------->");
						break;
					} else {
						System.out.println("<------------------------------------------------------->");
						System.out.println("\n ❌❌❌❌❌---Invalid choice---❌❌❌❌❌");
						System.out.println("<------------------------------------------------------->");
					}

				}

			} else if (log.equals("2")) {

				while (true) {

					System.out.println("\n Press 1 for create a Employee");
					System.out.println("\n press 2 for View the details of employee");
					System.out.println("\n prees 3 for Assign employee to a project");
					System.out.println(
							"\n press 4 for View total number of days Employee worked in a project and also their wages.");
					System.out.println("\n press 0 for Exit");

					int bd = sc.nextInt();

					if (bd == 1) {
						try {
							System.out.println("Enter Employee Name :");
							sc.nextLine();
							String name = sc.nextLine();

							System.out.println("Enter Employee Mobile :");

							String mobile = sc.nextLine();

							System.out.println("Enter Employee email :");
							String email = sc.nextLine();

							System.out.println("Enter Employee address :");
							String address = sc.nextLine();

							System.out.println("Enter Employee Aadhar no. :");
							String aadhar = sc.nextLine();

							System.out.println("Enter Employee Daily Wages :");

							int empwages = sc.nextInt();

							System.out.println("Enter Total Days Employee Worked :");
							int total_days = sc.nextInt();

							System.out.println("Enter Employee password :");
							sc.nextLine();
							String password = sc.nextLine();

							System.out.println(mig.addEmployee(name, mobile, email, address, aadhar, empwages,
									total_days, password));

						} catch (InputMismatchException e) {
							System.out.println("<------------------------------------------------------->");
							System.out.println("✖✖✖✖---Please Enter Valid Data---✖✖✖✖");
							System.out.println("<------------------------------------------------------->");
							break;
						}

					} else if (bd == 2) {

						System.out.println("Enter the email of employee to see details of employee...");
						String email = sc.next();
						Employee em = mig.viewEmployeeDetail(email);
						System.out.println("<------------------------------------------------------->");
						System.out.println(" The Employee ID is             : " + em.getEmpid());
						System.out.println(" The Employee Name is           : " + em.getEmpname());
						System.out.println(" The Employee Email is          : " + em.getEmpemail());
						System.out.println(" The Employee Mobile no.is      : " + em.getEmpmobile());
						System.out.println(" The Employee Address is        : " + em.getEmpadd());
						System.out.println(" The Employee Aadhar Number is  : " + em.getEmpaadhar());
						System.out.println(" The Employee Wages is          : " + em.getEmpwages());
						System.out.println(" The Total Days Employee Worked : " + em.getTotal_days());
						System.out.println(" The Employee Password is       : " + em.getPassword());
						System.out.println("<------------------------------------------------------->");

					} else if (bd == 3) {
						try {
							System.out.println("Enter the project Id : ");
							int projid = sc.nextInt();

							System.out.println("Enter the employee Id you want to assign in the above project :");
							int empid = sc.nextInt();
							System.out.println("<------------------------------------------------------->");
							System.out.println(mig.assignEmployeeToProject(projid, empid));
							System.out.println("<------------------------------------------------------->");

						} catch (InputMismatchException e) {
							System.out.println("<------------------------------------------------------->");
							System.out.println("✖✖✖✖---Please Enter Valid Data---✖✖✖✖");
							System.out.println("<------------------------------------------------------->");
							break;
						}
					} else if (bd == 4) {
						try {
							System.out.println(
									"Enter Employee ID to View total number of days Employee worked in a project and also their wages");
							int empid = sc.nextInt();

							List<EmpProj> lst = mig.viewTotalEmpWorkedInProject(empid);
							System.out.println("\n The Employee Id is      : " + lst.get(0).getEmpid());
							System.out.println("\n And The Employee Name is: " + lst.get(0).getEmpname());
							System.out.println("\n Projects on which '" + lst.get(0).getEmpname()
									+ "' Worked are Shown Below... :) ");
							System.out.println("<------------------------------------------------------->");
							for (EmpProj e : lst) {
								System.out.println(" The Project Name is                    : " + e.getProjname());
								System.out.println(" The Total wages are                    : " + e.getEmpwages());
								System.out.println(" The Total Days the employee worked are : " + e.getTotal_days());
								System.out.println("<------------------------------------------------------->");

							}
						} catch (InputMismatchException e) {
							System.out.println("<------------------------------------------------------->");
							System.out.println("✖✖✖✖---Please Enter Valid Data---✖✖✖✖");
							System.out.println("<------------------------------------------------------->");
						}

					} else if (bd == 0) {
						System.out.println("<-------\n Thanks for visting..... See You Later... 😊--------->");
						break;
					} else {
						System.out.println("<------------------------------------------------------->");
						System.out.println("\n❌❌❌❌❌---Invalid choice---❌❌❌❌❌");
						System.out.println("<------------------------------------------------------->");
					}

				}

			}

			else {
				System.out.println("<------------------------------------------------------->");
				System.out.println("❌❌❌❌❌---Invalid choice---❌❌❌❌❌");
				System.out.println("<------------------------------------------------------->");
			}

		} catch (InputMismatchException e) {
			System.out.println("<------------------------------------------------------->");
			System.out.println("❌❌❌❌❌---Invalid choice---❌❌❌❌❌");
			System.out.println("<------------------------------------------------------->");
		}

	}
}