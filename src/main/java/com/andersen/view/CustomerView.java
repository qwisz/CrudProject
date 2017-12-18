package com.andersen.view;

import com.andersen.controller.CustomerController;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class CustomerView implements IView {

    private CustomerController controller = new CustomerController();
    private Scanner sc = new Scanner(System.in);

    public void create() throws IOException {
        System.out.println("Enter the first name:");
        String firstName = sc.nextLine();
        System.out.println("Enter the last name:");
        String lastName = sc.nextLine();
        System.out.println("Enter the address:");
        String address = sc.nextLine();
        Set<Long> projectsId = getIds();

        if (controller.create(firstName, lastName, address, projectsId))
            System.out.println("Customer is created");
        else
            System.out.println("Customer in not created");
    }

    public void read() throws IOException {
        Long id = getId();

        System.out.println(controller.read(id));
    }

    public void update() throws IOException {
        Long id = getId();
        System.out.println("Enter the first name:");
        String firstName = sc.nextLine();
        System.out.println("Enter the last name:");
        String lastName = sc.nextLine();
        System.out.println("Enter the address:");
        String address = sc.nextLine();
        Set<Long> projectsId = getIds();

        if (controller.update(id, firstName, lastName, address, projectsId))
            System.out.println("Customer is updated");
        else
            System.out.println("Customer is not updated");
    }

    public void delete() throws IOException {
        Long id = getId();
        controller.delete(id);
        System.out.println("Customer is deleted");
    }

    private Long getId() throws IOException {
        boolean check = false;
        Long id = null;

        while (!check) {
            System.out.println("Enter the id of the customer");

            if (sc.hasNextLong()) {
                id = Long.parseLong(sc.nextLine());
                if (controller.isExist(id))
                    check = true;
                else {
                    System.out.println("Customer with such id doesn't exist");
                }
            } else {
                System.out.println("Wrong answer, try again");
                sc.next();
            }
        }

        return id;
    }
}
