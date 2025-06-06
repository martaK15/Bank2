package banka;

public class CList {
   private Type type;
    private double buy;
   private double sell;

    public CList(Type type, double buy, double sell) {
        this.type = type;
        this.buy = buy;
        this.sell = sell;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }
}
