import entity.Todo;
import jakarta.persistence.*;
import java.util.Scanner;


public class Main {

    public static void main(String args[]){


        System.out.println("<Welcome>\n");

        boolean on = true;
        int userChoice=0;
        String userChoiceString;
        String action, delNum;
        String title = null ;
        int i = 0;


        //Scanner
        Scanner myObj = new Scanner(System.in);


        //User makes title which creates todo object
        System.out.println("Create a title for todolist:");
        title = myObj.nextLine();
        System.out.println("Title is: " + title);
        TodoList mainTodo = new TodoList(title);
        System.out.println("\n");

        //Give user options
        while (on == true) {

            System.out
                    .println("Please select one of the following \n"
                            + "[1]Add to todo list\n"
                            + "[2]Print todo list\n"
                            + "[3]Quit Program\n");
            userChoiceString = myObj.nextLine();
            userChoice = Integer.parseInt(userChoiceString);

            switch (userChoice) {
                // ----------------------------------------------------------------------------
                case 1:

                    System.out.println("You've selected [1]Add to todo list");
                    System.out.println("Add a todo:");
                    action = myObj.nextLine();

                    mainTodo.addTodo(action);

                    addToSQL(action, i);

                    System.out.println("Action to complete: " + action);

                    System.out.println("\n");
                    i++;
                    break;

                // ----------------------------------------------------------------------------
                case 2:
                    System.out.println("You've selected [3]Print todo list");
                    System.out.println(mainTodo.toString());
                    break;
                // ----------------------------------------------------------------------------
                case 3:
                    on = false;

                    break;
                // ----------------------------------------------------------------------------

                default:
                    System.out.println("Invalid Choice, Please try again.");

            }


        }




        //---------------------------------------------------------------

        //---------------------------------------------------------------
    }

    public static void addToSQL(String task, int number){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();



        try {
            transaction.begin();

            Todo monday = new Todo();

            monday.setTask(task);
            monday.setId(number);
            entityManager.persist(monday);

            transaction.commit();

        } finally {
            if(transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
