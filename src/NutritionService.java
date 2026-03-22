import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class NutritionService {
    public void addFood(String name, double calories, double protein, double sugar) {

        try (Connection conn = DatabaseManager.connect()) {

            String sql = "INSERT INTO foods(name, calories, protein, sugar) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDouble(2, calories);
            stmt.setDouble(3, protein);
            stmt.setDouble(4, sugar);

            stmt.executeUpdate();

            String logsql = "INSERT INTO food_logs(date) VALUES (?)";
            PreparedStatement logstmt = conn.prepareStatement(logsql);
            logstmt.setString(1, LocalDate.now().toString());
            logstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addDailyGoal(double caloriesGoal, double proteinGoal, double sugarGoal) {

        try (Connection conn = DatabaseManager.connect()) {

            String sql = "INSERT INTO daily_goals(calories, proteins, sugars) VALUES (?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, caloriesGoal);
            stmt.setDouble(2, proteinGoal);
            stmt.setDouble(3, sugarGoal);

            stmt.executeUpdate();
        }

        catch (Exception e) {
            System.out.println("Exception in addDailyGoal");
        }
    }

    public void addWeekly(double totalCalories, double totalProtein, double totalSugar) {

        try (Connection conn = DatabaseManager.connect()) {

            String sql = "INSERT INTO weekly_logs(logDate, calories, protein, sugar) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, LocalDate.now().toString());
            stmt.setDouble(2, totalCalories);
            stmt.setDouble(3, totalProtein);
            stmt.setDouble(4, totalSugar);

            stmt.executeUpdate();

        }

        catch (Exception e) {
            System.out.println("Exception in addWeekly");
        }

    }

    public void deleteDaily() {

        try (Connection conn = DatabaseManager.connect()) {

            String sql = "DELETE FROM foods";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteWeekly() {

        try (Connection conn = DatabaseManager.connect()) {

            String sql = "DELETE FROM weekly_logs";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void saveToWeekly(){
        DailyLog dailyLog = new DailyLog();

        double calories = dailyLog.getCalories();
        double protein = dailyLog.getProteins();
        double sugar = dailyLog.getSugars();

        addWeekly(calories, protein, sugar);

    }

    public void printWeeklyLog(){
        try (Connection connection = DatabaseManager.connect()){

            String sql = """
                    SELECT
                    SUM(calories) AS total_calories,
                    SUM(protein) AS total_proteins,
                    SUM(sugar) AS total_sugar
                    FROM weekly_logs
                    WHERE logDate >= date('now', '-6 days')
                    """;
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                System.out.println("Weekly Total:");
                System.out.println("Calories:" + rs.getDouble("total_calories"));
                System.out.println("Protein:" + rs.getDouble("total_proteins"));
                System.out.println("Sugar:" + rs.getDouble("total_sugar"));
            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
