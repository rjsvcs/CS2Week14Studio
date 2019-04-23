package pacman.model;

public class Ghost {
    private Location location;
    private boolean vulnerable;
    private boolean isAlive;

    public Ghost(Location location) {
        this.location = location;
        isAlive = true;
        vulnerable = false;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isVulnerable() {
        return vulnerable;
    }

    public boolean isAlive() {
        return true;
    }

    void setVulnerable() {
        vulnerable = true;
    }

    void setLocation(Location location) {
        this.location = location;
    }

    void setDead() {
        isAlive = false;
    }
}
