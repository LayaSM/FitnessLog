import java.util.*;

// Exercise class representing individual exercise activities
class Exercise {
    private String exerciseType;
    private Date dateTime;
    private int duration;
    private int caloriesBurned;

    public Exercise(String exerciseType, Date dateTime, int duration, int caloriesBurned) {
        this.exerciseType = exerciseType;
        this.dateTime = dateTime;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }
    public String getExerciseType() {
        return exerciseType;
    }
    public Date getDateTime() {
        return dateTime;
    }
    public int getDuration() {
        return duration;
    }
    public int getCaloriesBurned() {
        return caloriesBurned;
    }
}

// Fitness Log class for logging exercise activities
class FitnessLog {
    private List<Exercise> exercises;
    public FitnessLog() {
        this.exercises = new ArrayList<>();
    }
    public void logExercise(String exerciseType, Date dateTime, int duration, int caloriesBurned) {
        Exercise exercise = new Exercise(exerciseType, dateTime, duration, caloriesBurned);
        exercises.add(exercise);
    }
    public List<Exercise> getExercises() {
        return exercises;
    }
}

// Calorie Tracker class for tracking cumulative calorie expenditure
class CalorieTracker {
    private int totalCalories;
    public CalorieTracker() {
        this.totalCalories = 0;
    }
    public void trackCalories(int calories) {
        totalCalories += calories;
    }
    public int getTotalCalories() {
        return totalCalories;
    }
}

// Time Tracker class for tracking exercise session durations
class TimeTracker {
    private Map<String, Integer> activityDurations;
    public TimeTracker() {
        this.activityDurations = new HashMap<>();
    }
    public void trackTime(String activityType, int duration) {
        activityDurations.put(activityType, duration);
    }
    public Map<String, Integer> getActivityDurations() {
        return activityDurations;
    }
}

// Fitness Summary class for generating weekly summaries
class FitnessSummary {
    // Generate weekly summary based on provided data
    public void generateWeeklySummary(List<Exercise> exercises, CalorieTracker calorieTracker, TimeTracker timeTracker) {
        // Logic to generate weekly summary
        System.out.println("Weekly Summary:");
        // Display exercises
        System.out.println("Exercises:");
        for (Exercise exercise : exercises) {
            System.out.println("Type: " + exercise.getExerciseType() + ", Date: " + exercise.getDateTime());
        }
        // Display total calories burned
        int totalCalories = calorieTracker.getTotalCalories();
        System.out.println("Total Calories Burned: " + totalCalories);
        // Display activity durations
        System.out.println("Activity Durations:");
        Map<String, Integer> activityDurations = timeTracker.getActivityDurations();
        for (Map.Entry<String, Integer> entry : activityDurations.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " minutes");
        }

        if (totalCalories > 500) {
            System.out.println("\nWow! You're on fire! Keep up the amazing work and continue pushing yourself!");
        } else if (totalCalories > 250) {
            System.out.println("\nGreat job! You're making steady progress. Keep up the consistency!");
        } else {
            System.out.println("\nEvery step counts! Stay motivated and keep moving towards your goals!");
        }
    }
}

class User {
    private String username;
    private String password;
    private FitnessLog fitnessLog;
    private CalorieTracker calorieTracker;
    private TimeTracker timeTracker;
    private FitnessSummary fitnessSummary;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.fitnessLog = new FitnessLog();
        this.calorieTracker = new CalorieTracker();
        this.timeTracker = new TimeTracker();
        this.fitnessSummary = new FitnessSummary();
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public FitnessLog getFitnessLog() {
        return fitnessLog;
    }
    public CalorieTracker getCalorieTracker() {
        return calorieTracker;
    }
    public TimeTracker getTimeTracker() {
        return timeTracker;
    }
    public FitnessSummary getFitnessSummary() {
        return fitnessSummary;
    }
}

public class FitnessApp {
    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Fitness Log!");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    createAccount(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            User currentUser = users.get(username);
            System.out.println("Login successful. Welcome, " + username + "!");

            // Redirect to dashboard
            showDashboard(scanner, currentUser);
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose another username.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User newUser = new User(username, password);
        users.put(username, newUser);
        System.out.println("Account created successfully. You can now login.");
    }

    private static void showDashboard(Scanner scanner, User currentUser) {
        while (true) {
            System.out.println("\nDashboard");
            System.out.println("1. Log Exercise");
            System.out.println("2. Track Calories");
            System.out.println("3. Monitor Time");
            System.out.println("4. View Fitness Summary");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    logExercise(scanner, currentUser);
                    break;
                case 2:
                    trackCalories(scanner, currentUser);
                    break;
                case 3:
                    monitorTime(scanner, currentUser);
                    break;
                case 4:
                    viewFitnessSummary(currentUser);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void logExercise(Scanner scanner, User currentUser) {
        try {
            System.out.print("Enter exercise type: ");
            String exerciseType = scanner.nextLine();
            System.out.print("Enter duration (minutes): ");
            int duration = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter calories burned: ");
            int caloriesBurned = scanner.nextInt();
            scanner.nextLine(); 
    
            Date dateTime = new Date(); 
            currentUser.getFitnessLog().logExercise(exerciseType, dateTime, duration, caloriesBurned);
            currentUser.getCalorieTracker().trackCalories(caloriesBurned);
            currentUser.getTimeTracker().trackTime(exerciseType, duration);
            System.out.println("Exercise logged successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for duration and calories burned.");
            scanner.nextLine(); 
        }
    }

    private static void trackCalories(Scanner scanner, User currentUser) {
        try {
            System.out.print("Enter calories burned: ");
            int caloriesBurned = scanner.nextInt();
            scanner.nextLine(); 
            currentUser.getCalorieTracker().trackCalories(caloriesBurned);
            System.out.println("Calories tracked successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for calories burned.");
            scanner.nextLine();
        }
    }

    private static void monitorTime(Scanner scanner, User currentUser) {
        try {
            System.out.print("Enter exercise type: ");
            String exerciseType = scanner.nextLine();
            System.out.print("Enter duration (minutes): ");
            int duration = scanner.nextInt();
            scanner.nextLine(); 
            currentUser.getTimeTracker().trackTime(exerciseType, duration);
            System.out.println("Time monitored successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for duration.");
            scanner.nextLine(); 
        }
    }

    private static void viewFitnessSummary(User currentUser) {
        currentUser.getFitnessSummary().generateWeeklySummary(currentUser.getFitnessLog().getExercises(),
                currentUser.getCalorieTracker(), currentUser.getTimeTracker());
    }

}