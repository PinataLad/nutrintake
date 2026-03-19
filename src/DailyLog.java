
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class DailyLog{
    //private ArrayList<Food> foods = new ArrayList<>();
    //private ArrayList<DailyGoal> goalLog = new ArrayList<>();

    /*
    //Adds food to array list
    public void addFood(Food food){
        foods.add(food);
    }
    public void addGoal(DailyGoal goal) {
        goalLog.add(goal);
    }

     */

    //Calculates the total sum of the Calories column from the foods table
    public int getTotalCalories() {

        try (Connection conn = DatabaseManager.connect()) {

            String sql = "SELECT SUM(calories) AS total from foods";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            if(resultSet.next()) {
                return resultSet.getInt("total");
            }

            return 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //prints the total calories
    public void checkCalories() {

        int total = 0;

        total = getTotalCalories();

        System.out.println("Total Calories: " + total);

    }

}



    /*
    //Calculates the total calorie amount of current food input
    public int getTotalCalories(){
        int total = 0;
        for(Food food : foods){
            total += food.getCalories();
        }
        return total;
    }



    //Calculates the total protein amount of current food input
    public int getTotalProteins(){
        int total = 0;
        for(Food food : foods){
            total += food.getProteins();
        }
        return total;
    }

    //Calculates the total sugar amount of current food input
    public int getTotalSugars(){
        int total = 0;
        for(Food food : foods){
            total += food.getSugars();
        }
        return total;
    }

    public String checkCalories(){

        int total = 0;

        DailyGoal goal = goalLog.get(0);

        total = goal.getCaloriesGoal() - getTotalCalories();

        if(total > 0){
            return "You are under your limit by " + total;
        } else if(total <= 0){
            return "You are over or at your limit by" + total;
        }

        return null;

    }

    public String checkProteins(){

        int total = 0;

        DailyGoal goal = goalLog.get(0);

        total = goal.getProteinGoal() - getTotalProteins();

        if(total > 0){
            return "You are under your limit by " + total;
        } else if(total <= 0){
            return "You are over or at your limit by" + total;
        }

        return null;

    }

    public String checkSugars(){

        int total = 0;

        DailyGoal goal = goalLog.get(0);

        total = goal.getSugarGoal() - getTotalSugars();

        if(total > 0){
            return "You are under your limit by " + total;
        } else if(total <= 0){
            return "You are over or at your limit by" + total;
        }

        return null;

    }

    //Prints log of all foods input, plus the totals for all input food
    public void printLog(){
        for(Food food : foods){
            System.out.println(food.getAll());
            System.out.println("");
        }

        DailyGoal goal = goalLog.get(0);
        System.out.println(goal.getAllGoals());

        System.out.println("");

        System.out.println("Goal Log:");
        System.out.println("Calorie Goal: " + checkCalories());
        System.out.println("Protein Goal: " + checkProteins());
        System.out.println("Sugar Goal: " + checkSugars());

        System.out.println("");

        System.out.println("Daily Totals:");
        System.out.println("Calories: " + getTotalCalories());
        System.out.println("Proteins: " + getTotalProteins());
        System.out.println("Sugars: " + getTotalSugars());

        System.out.println("");
    }
}

     */
