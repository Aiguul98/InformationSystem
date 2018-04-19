package dataPackage;

public enum Rates {
    NONE("Не выбрано"),
    THIRD_PART("0.3"),
    HALF_PART("0.5"),
    FULL_PART("1");

    private final String text;

    Rates(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
