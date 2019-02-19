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

    public Report(double stockValue, double depreciation) {
        this(BigDecimal.valueOf(stockValue), BigDecimal.valueOf(depreciation));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return stockValue.compareTo(report.stockValue) == 0 &&
                depreciation.compareTo(report.depreciation) == 0 ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stockValue, depreciation);
    }

    @Override
    public String toString() {
        return "Report{" +
                "stockValue=" + stockValue +
                ", depreciation=" + depreciation +
                '}';
    }
}
