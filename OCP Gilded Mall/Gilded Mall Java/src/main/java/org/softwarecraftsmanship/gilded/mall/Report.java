package org.softwarecraftsmanship.gilded.mall;

import java.math.BigDecimal;
import java.util.Objects;

public class Report {
    private final BigDecimal stockValue;
    private final BigDecimal depreciation;

    public Report(BigDecimal stockValue, BigDecimal depreciation) {
        this.stockValue = stockValue;
        this.depreciation = depreciation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return stockValue == report.stockValue &&
                depreciation == report.depreciation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockValue, depreciation);
    }


}
