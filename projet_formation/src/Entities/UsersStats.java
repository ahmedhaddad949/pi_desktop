package Entities;

public class UsersStats {
    private String roles;
    private int total  ;

    public UsersStats(String roles, int total) {
        this.roles = roles;
        this.total = total;
    }

    public UsersStats() {
    }

    @Override
    public String toString() {
        return "UsersStats{" +
                "roles='" + roles + '\'' +
                ", total=" + total +
                '}';
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
