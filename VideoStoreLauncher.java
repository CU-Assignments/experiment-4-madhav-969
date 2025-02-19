class Video {
    private String title;
    private boolean checkedOut;
    private double averageRating;
    private int numRatings;

    public Video(String title) {
        this.title = title;
        this.checkedOut = false;
        this.averageRating = 0.0;
        this.numRatings = 0;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void checkOut() {
        checkedOut = true;
    }

    public void returnVideo() {
        checkedOut = false;
    }

    public void receiveRating(int rating) {
        numRatings++;
        averageRating =rating/numRatings;
    }
}

class VideoStore {
    private Video[] videos;
    private int numVideos;

    public VideoStore() {
        videos = new Video[10];
        numVideos = 0;
    }

    public void addVideo(String title) {
        if (numVideos < videos.length) {
            videos[numVideos] = new Video(title);
            numVideos++;
        } else {
            System.out.println("Store is full.");
        }
    }

    public void checkOut(String title) {
        for (int i = 0; i < numVideos; i++) {
            if (videos[i].getTitle().equals(title)) {
                if (!videos[i].isCheckedOut()) {
                    videos[i].checkOut();
                    System.out.println(title + " checked out.");
                } else {
                    System.out.println(title + " is already checked out.");
                }
                return;
            }
        }
        System.out.println(title + " not found.");
    }

    public void returnVideo(String title) {
        for (int i = 0; i < numVideos; i++) {
            if (videos[i].getTitle().equals(title)) {
                if (videos[i].isCheckedOut()) {
                    videos[i].returnVideo();
                    System.out.println(title + " returned.");
                } else {
                    System.out.println(title + " is already in the store.");
                }
                return;
            }
        }
        System.out.println(title + " not found.");
    }

    public void receiveRating(String title, int rating) {
        for (int i = 0; i < numVideos; i++) {
            if (videos[i].getTitle().equals(title)) {
                videos[i].receiveRating(rating);
                return;
            }
        }
        System.out.println(title + " not found.");
    }

    public void listInventory() {
        System.out.println("Inventory:");
        for (int i = 0; i < numVideos; i++) {
            System.out.println(videos[i].getTitle() + " (" + (videos[i].isCheckedOut() ? "Checked Out" : "In Store") + ")");
            System.out.println("  Rating: " + videos[i].getAverageRating());
        }
    }
}

public class VideoStoreLauncher {
    public static void main(String[] args) {
        VideoStore store = new VideoStore();

        // Add videos
        store.addVideo("The Matrix");
        store.addVideo("Godfather II");
        store.addVideo("Star Wars Episode IV: A New Hope");

        // Give ratings
        store.receiveRating("The Matrix", 5);
        store.receiveRating("The Matrix", 4);
        store.receiveRating("Godfather II", 5);
        store.receiveRating("Godfather II", 5);
        store.receiveRating("Star Wars Episode IV: A New Hope", 4);
        store.receiveRating("Star Wars Episode IV: A New Hope", 5);

        // Rent and return videos
        store.checkOut("The Matrix");
        store.returnVideo("The Matrix");
        store.checkOut("Godfather II");
        store.returnVideo("Godfather II");
        store.checkOut("Star Wars Episode IV: A New Hope");
        store.returnVideo("Star Wars Episode IV: A New Hope");

        // List inventory after "Godfather II" has been rented out
        store.checkOut("Godfather II");
        store.listInventory();
    }
}