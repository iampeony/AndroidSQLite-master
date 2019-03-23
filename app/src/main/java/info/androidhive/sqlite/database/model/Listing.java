package info.androidhive.sqlite.database.model;

public class Listing {
    public static final String TABLE_NAME = "listings";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STUDENT = "student";
    public static final String COLUMN_COURSE = "course";
    public static final String COLUMN_PRIORITY = "priority";

    private int id;
    private String student;
    private String course;
    private String priority;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_STUDENT + " TEXT,"
                    + COLUMN_COURSE + " TEXT,"
                    + COLUMN_PRIORITY + " TEXT"
                    + ")";

    public Listing() {
    }

    public Listing(int id, String student, String course, String priority) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
