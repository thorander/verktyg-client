package style.gui.test.take;

public class TTest {

    private String title;
    private String description;
    private int time;

    public TTest(String title, String description, int time){
        this.setTitle(title);
        this.setDescription(description);
        this.setTime(time);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
