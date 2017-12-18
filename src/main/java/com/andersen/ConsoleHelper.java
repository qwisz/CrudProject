package com.andersen;

import com.andersen.view.*;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleHelper {

    private SkillView skillView = new SkillView();
    private DeveloperView developerView = new DeveloperView();
    private TeamView teamView = new TeamView();
    private ProjectView projectView = new ProjectView();
    private CompanyView companyView = new CompanyView();
    private CustomerView customerView = new CustomerView();
    private Scanner sc = new Scanner(System.in);


    public void menu() throws IOException {

        boolean isExit = false;
        int pick = -1;
        int secondPick = -1;
        boolean isExitSecond = false;

        while (!isExit) {
            System.out.println();
            startMenu();
            System.out.println();
            System.out.println("Enter the number of menu");
            if (sc.hasNextInt()) {
                pick = Integer.parseInt(sc.nextLine());
                switch (pick) {
                    case 1:
                        switchMenu(skillView);
                        break;
                    case 2:
                        switchMenu(developerView);
                        break;
                    case 3:
                        switchMenu(teamView);
                        break;
                    case 4:
                        switchMenu(projectView);
                        break;
                    case 5:
                        switchMenu(companyView);
                        break;
                    case 6:
                        switchMenu(customerView);
                        break;
                    case 7:
                        isExit = true;
                        break;
                    default:
                        System.out.println("Wrong answer, try again");
                        break;
                }
            }
            else {
                System.out.println("Wrong answer,repeat please");
                sc.next();
            }
        }
    }

    private void startMenu() {
        System.out.println("1. Skill");
        System.out.println("2. Developer");
        System.out.println("3. Team");
        System.out.println("4. Project");
        System.out.println("5. Company");
        System.out.println("6. Customer");
        System.out.println("7. Exit");
    }

    private void secondMenu() {
        System.out.println("1. Create");
        System.out.println("2. Read");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("5. Back");
    }

    private void switchMenu(IView view) throws IOException {
        boolean isExitSecond = false;
        int secondPick = -1;

        while (!isExitSecond) {
            System.out.println();
            secondMenu();
            System.out.println();
            System.out.println("Enter the number of menu");
            if (sc.hasNextInt()) {
                secondPick = Integer.parseInt(sc.nextLine());
                switch (secondPick) {
                    case 1:
                        view.create();
                        break;
                    case 2:
                        view.read();
                        break;
                    case 3:
                        view.update();
                        break;
                    case 4:
                        view.delete();
                        break;
                    case 5:
                        isExitSecond = true;
                        break;
                    default:
                        System.out.println("Wrong answer, try again");
                        break;
                }
            } else {
                System.out.println("Wrong answer, try to enter the number of menu again please");
                sc.next();
            }
        }
    }
}
