package testdata.enums;

import lombok.Getter;

@Getter
public enum RowsCountOption {
    _5_ROWS("5 rows"),
    _10_ROWS("10 rows"),
    _20_ROWS("20 rows"),
    _25_ROWS("25 rows"),
    _50_ROWS("50 rows"),
    _100_ROWS("100 rows");

    private final String optionName;

    RowsCountOption(String optionName) {
        this.optionName = optionName;
    }
}
