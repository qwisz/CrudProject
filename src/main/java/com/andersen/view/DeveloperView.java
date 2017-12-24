package com.andersen.view;

import com.andersen.controller.DeveloperController;
import com.andersen.controller.SkillController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

public class DeveloperView implements IView {

    private DeveloperController controller = new DeveloperController();
    private SkillController scont = new SkillController();
    private Scanner sc = new Scanner(System.in);

    public void create() throws IOException {

        System.out.println("Enter the first name:");
        String firstName = sc.nextLine();
        System.out.println("Enter the last name:");
        String lastName = sc.nextLine();
        System.out.println("Enter speciality:");
        String speciality = sc.nextLine();
        Set<Long> skillsId = getIds();
        for (Long idd : skillsId) {
            if (!scont.isExist(idd)) {
                System.out.println("Skills with one of such ids doesn't exist");
                return;
            }
        }
        BigDecimal salary = getSalary();

        if (controller.create(firstName, lastName, speciality, skillsId, salary))
            System.out.println("Developer is created");
        else
            System.out.println("Developer is not created");
    }

    public void read() throws IOException {
        Long id = getId();

        System.out.println(controller.read(id));
    }

    public void update() throws IOException {
        Long id = getId();

        System.out.println("Enter the name:");
        String firstName = sc.nextLine();
        System.out.println("Enter the last name:");
        String lastName = sc.nextLine();
        System.out.println("Enter speciality:");
        String speciality = sc.nextLine();

        Set<Long> skillsId = getIds();

        for (Long idd : skillsId) {
            if (!scont.isExist(idd)) {
                System.out.println("Skills with one of such ids doesn't exist");
                return;
            }
        }

        BigDecimal salary = getSalary();

        if (controller.update(id, firstName, lastName, speciality, skillsId, salary)) {
            System.out.println("Developer is updated");
        } else {
            System.out.println("Developer is not updated");
        }
    }

    public void delete() throws IOException {
        Long id = getId();
        controller.delete(id);
        System.out.println("Developer is deleted");
    }

    private Long getId() throws IOException {
        boolean check = false;
        Long id = null;

        while (!check) {
            System.out.println("Enter the id of the developer");

            if (sc.hasNextLong()) {
                id = Long.parseLong(sc.nextLine());
                if (controller.isExist(id))
                    check = true;
                else {
                    System.out.println("Developer with such id doesn't exist");
                }
            } else {
                System.out.println("Wrong answer, try again");
                sc.next();
            }
        }

        return id;
    }

    private BigDecimal getSalary() {
        boolean check = false;
        BigDecimal salary = null;

        while (!check) {
            System.out.println("Enter the salary:");
            if (sc.hasNextBigDecimal()) {
                salary = new BigDecimal(sc.nextLine());
                check = true;
            } else {
                System.out.println("Wrong answer, please repeat");
                sc.next();
                check = false;
            }
        }
        return salary;
    }
}
