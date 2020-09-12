package task;


public class Task {

    private final String id;
    private final String description;
    private final String author;
    private final String name;
    private final int storyPoint;

    public Task(String id, String description, String author, String name, int storyPoint) {
        this.id = id;
        this.description = description;
        this.author = author;
        this.name = name;
        this.storyPoint = storyPoint;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public int getStoryPoint() {
        return storyPoint;
    }

    @Override
    public String toString() {
        return "Task{\n" +
                "id=" + id + "\n" +
                "description=" + description + "\n" +
                "author='" + author + "\n" +
                "name='" + name + "\n" +
                "storyPoint=" + storyPoint + "\n" +
                "}\n";
    }
}
