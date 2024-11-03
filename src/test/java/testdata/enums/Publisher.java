package testdata.enums;

import lombok.Getter;

@Getter
public enum Publisher {

    O_REILLY("O'Reilly Media"),
    NO_STARCH_PRESS("No Starch Press");

    private final String name;

    Publisher(String name) {
        this.name = name;
    }

}