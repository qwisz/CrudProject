package com.andersen.view;

import com.andersen.controller.SkillController;

import java.io.IOException;
import java.util.Scanner;

public class SkillView implements IView {

    private SkillController controller = new SkillController();
    private Scanner sc = new Scanner(System.in);

    public void create() throws IOException {

        System.out.println("Enter the name of the skill");
        if (controller.create(sc.nextLine())) {
            System.out.println("Skill is created");
        } else {
            System.out.println("Skill is not created");
        }

    }

    public void read() throws IOException {
        Long id = getId();
        System.out.println(controller.read(id));
    }

    public void update() throws IOException {

        Long id = getId();
        System.out.println("Enter name of the skill");
        String name = sc.nextLine();

        if (controller.update(id, name))
            System.out.println("Skill is updated");
        else
            System.out.println("Skill isn't updated");
    }

    public void delete() throws IOException {

        Long id = getId();
        controller.delete(id);
        System.out.println("Skill is deleted");
    }

    private Long getId() throws IOException {
        boolean check = false;
        Long id = null;

        while (!check) {
            System.out.println("Enter the id of the skill");

            if (sc.hasNextLong()) {
                id = Long.parseLong(sc.nextLine());
                if (controller.isExist(id))
                    check = true;
                else {
                    System.out.println("Skill with such id doesn't exist");
                }
            } else {
                System.out.println("Wrong answer,repeat please");
                sc.next();
            }
        }

        return id;
    }
}
