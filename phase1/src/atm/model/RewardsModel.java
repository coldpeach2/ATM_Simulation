package atm.model;
import java.io.IOException;
import java.util.Date;

public class RewardsModel  {

    private final long id;
    private double balance;
    private final Date creationDate;
    private int points;


    public RewardsModel(long id, double balance, Date creationDate, int points) {
        this.id = id;
        this.balance = balance;
        this.creationDate = creationDate;
        this.points = points;
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getPoints() {
        return points;
    }

    public String toCSVRowString() {
        return id + "," + balance + "," + creationDate + "," + points;
    }

    public static RewardsModel fromCSVRowString(String row) throws IOException {
        String[] cells = row.split(",");
        if (cells.length != 4) throw new IOException("Incorrect number of cells in a row!");
        Date creationDate = new Date(Long.parseLong(cells[2]));
        return new RewardsModel(Long.parseLong(cells[0]), // user id "0" -> 0
                Double.parseDouble(cells[1]),
                creationDate,
                Integer.parseInt(cells[3]));
    }

}


