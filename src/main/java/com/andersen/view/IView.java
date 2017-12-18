package com.andersen.view;

import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public interface IView {

    Scanner sc = new Scanner(System.in);

    void create() throws IOException;

    void read() throws IOException;

    void update() throws IOException;

    void delete() throws IOException;

    default Set<Long> getIds() {

        Set<Long> ids = new HashSet<>();
        boolean check = false;
        int numOfSkills = -1;

        while(!check) {
            System.out.println("Enter the number of entities:");
            if (sc.hasNextInt()) {
                numOfSkills = Integer.parseInt(sc.nextLine());
                check = true;
            } else {
                System.out.println("Wrong answer, please repeat");
                sc.next();
                check = false;
            }
        }
        int i = 0;
        while(i != numOfSkills) {
            System.out.println("Enter the entity's id");
            if (sc.hasNextLong()) {
                ids.add(Long.parseLong(sc.nextLine()));
                i += 1;
            } else {
                System.out.println("Wrong answer, please repeat");
                sc.next();
            }
        }

        return ids;
    }
}
