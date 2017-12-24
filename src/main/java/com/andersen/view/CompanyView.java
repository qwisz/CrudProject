package com.andersen.view;

import com.andersen.controller.CompanyController;
import com.andersen.controller.ProjectController;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class CompanyView implements IView {

    private CompanyController controller = new CompanyController();
    private ProjectController pc = new ProjectController();
    private Scanner sc = new Scanner(System.in);

    public void create() throws IOException {

        System.out.println("Enter the name:");
        String name = sc.nextLine();

        Set<Long> ids = getIds();

        for (Long idd : ids) {
            if (!pc.isExist(idd)) {
                System.out.println("Projects with such ids doesn't exist");
                return;
            }
        }

        if (controller.create(name, ids))
            System.out.println("Company is created");
        else
            System.out.println("Company is not created");
    }

    public void read() throws IOException {
        Long id = getId();
        System.out.println(controller.read(id));
    }

    public void update() throws IOException {

        Long id = getId();
        System.out.println("Enter the name:");

        String name = sc.nextLine();
        Set<Long> ids = getIds();

        for (Long idd : ids) {
            if (!pc.isExist(idd)) {
                System.out.println("Projects with such ids doesn't exist");
                return;
            }
        }

        if (controller.update(id, name, ids)) {
            System.out.println("Company is created");
        } else {
            System.out.println("Company is not created");
        }
    }

    public void delete() throws IOException {
        Long id = getId();
        controller.delete(id);
        System.out.println("Company is deleted");
    }

    private Long getId() throws IOException {
        boolean check = false;
        Long id = null;

        while (!check) {
            System.out.println("Enter the id of the company");

            if (sc.hasNextLong()) {
                id = Long.parseLong(sc.nextLine());
                if (controller.isExist(id))
                    check = true;
                else {
                    System.out.println("Company with such id doesn't exist");
                }
            } else {
                System.out.println("Wrong answer,repeat please");
                sc.next();
            }
        }

        return id;
    }
}
