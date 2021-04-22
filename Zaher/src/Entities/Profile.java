package Entities;

public class Profile {
    private int nbr_fomartions;

    public Profile(int nbr_fomartions) {
        this.nbr_fomartions = nbr_fomartions;
    }

    public Profile() {
    }

    public int getNbr_fomartions() {
        return nbr_fomartions;
    }

    public void setNbr_fomartions(int nbr_fomartions) {
        this.nbr_fomartions = nbr_fomartions;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "nbr_fomartions=" + nbr_fomartions +
                '}';
    }
}
