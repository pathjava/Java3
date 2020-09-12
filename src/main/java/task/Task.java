package task;


public enum  Task {
    INSTANCE;

    public final static String DB_PATH = "C:\\Users\\OlegPC\\IdeaProjects\\TestSpring\\src\\main\\resources\\tasks.json";

    private String id;
    private String description;
    private String author;
    private String name;
    private int storyPoint;

}
