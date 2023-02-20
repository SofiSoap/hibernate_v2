import entity.Todo;
import jakarta.persistence.*;

public class Main {

    public static void main(String args[]){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();


        

        //---------------------------------------------------------------
        try {
            transaction.begin();

            int i = 0;
            //put i in ID

           Todo monday = new Todo();

            monday.setTask("Run");
            monday.setId(0);
            entityManager.persist(monday);

            transaction.commit();

        } finally {
            if(transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }
        //---------------------------------------------------------------
    }
}
