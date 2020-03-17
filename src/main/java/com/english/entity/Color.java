package com.english.entity;

public enum Color {
    PRIMARY("primary"),
    SECONDARY("secondary"),
    SUCCESS("success"),
    DANGER("danger"),
    WARNING("warning"),
    INFO("info"),
    DARK("dark"),
    LIGHT("light");

    private String fieldName;

    Color(String fieldName) {
        this.fieldName = fieldName;
    }

    public static Color valueOf() {
        int i = randColor();
        switch (i){
            case 1: return PRIMARY;
            case 2: return SECONDARY;
            case 3: return SUCCESS;
            case 4: return DANGER;
            case 5: return WARNING;
            case 6: return INFO;
            case 7: return DARK;
        }
        return LIGHT;
    }

    public String getFieldName() {
        return fieldName;
    }

    private static int randColor() {
        return 1 + (int) (Math.random() * 7);
    }

}
