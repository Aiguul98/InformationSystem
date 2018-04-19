package dataPackage;

public enum Positions {
    NONE("Не выбрано"),
    LECTURER("Преподаватель"),
    SENIOR_LECTURER("Старший преподователь"),
    DOCENT("Доцент"),
    PROFESSOR("Профессор");

    private final String text;
    
    Positions(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
