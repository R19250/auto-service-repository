package ProjectAuto.Objects;

public class Order {
    private int orderID;
    private String name;
    private String email;
    private String spz;
    private String brand;
    private String model;
    private String date;
    private String time;
    private String note;

    public Order(int orderID, String name, String email, String spz, String brand, String model, String date, String time) {
        this.orderID = orderID;
        this.name = name;
        this.email = email;
        this.spz = spz;
        this.brand = brand;
        this.model = model;
        this.date = date;
        this.time = time;
    }

    public Order(int orderID, String name, String email, String spz, String brand, String model, String date, String time, String note) {
        this.orderID = orderID;
        this.name = name;
        this.email = email;
        this.spz = spz;
        this.brand = brand;
        this.model = model;
        this.date = date;
        this.time = time;
        this.note = note;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSpz() {
        return spz;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
