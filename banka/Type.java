package banka;

public enum Type {
    EUR("EUR"),
    RSD("RSD"),
    USD("USD");


    private String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

