package jdbc.entity;

public class Product {
    private String name;
    private String info;
    private double cost;

    public Product() {
    }

    public Product(String name, String info, double cost) {
        this.name = name;
        this.info = info;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "\nname = '" + name + '\'' +
                ", \ninfo = '" + info + '\'' +
                ", \ncost = " + cost +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
