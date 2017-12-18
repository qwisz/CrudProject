package com.andersen.view;

import com.andersen.controller.TeamController;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class TeamView implements IView {

    private TeamController controller = new TeamController();
    private Scanner sc = new Scanner(System.in);

    public void create() throws IOException {

        System.out.println("Enter the name:");
        String name = sc.nextLine();

        Set<Long> ids = getIds();

        if (controller.create(name, ids))
            System.out.println("Team is created");
        else
            System.out.println("Team is not created");

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

        if (controller.update(id, name, ids)) {
            System.out.println("Team is created");
        } else {
            System.out.println("Team is not created");
        }
    }

    public void delete() throws IOException {
        Long id = getId();
        controller.delete(id);
        System.out.println("Team is deleted");
    }

    private Long getId() throws IOException {
        boolean check = false;
        Long id = null;

        while (!check) {
            System.out.println("Enter the id of the team");

            if (sc.hasNextLong()) {
                id = Long.parseLong(sc.nextLine());
                if (controller.isExist(id))
                    check = true;
                else {
                    System.out.println("Team with such id doesn't exist");
                }
            } else {
                System.out.println("Wrong answer,repeat please");
                sc.next();
            }
        }

        return id;
    }
}
