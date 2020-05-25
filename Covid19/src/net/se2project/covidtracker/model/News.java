package net.se2project.covidtracker.model;

public class News {
    private final int id;
    private final String title;
    private final String url;
    private final String imageUrl;
    private final String sourceMeta;
    private final String datePublic;


    public News(int id, String title, String url, String imageUrl, String sourceMeta, String datePublic) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
        this.sourceMeta = sourceMeta;
        this.datePublic = datePublic;
    }


    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDatePublic() {
        return datePublic;
    }

    public String getSourceMeta() {
        return sourceMeta;
    }

    public int getId() {
        return id;
    }
}
