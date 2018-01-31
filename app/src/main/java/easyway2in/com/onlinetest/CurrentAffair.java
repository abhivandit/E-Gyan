package easyway2in.com.onlinetest;

public class CurrentAffair {
    private int id;
    private String description,image_link;

    public CurrentAffair(int id, String image_link, String description) {
        this.description = description;
        this.image_link = image_link;
        this.id=id;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {

        this.image_link = image_link;
    }
}
