package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final String webSite;
    private final String name;
    private final List<Period> periods;

    public Organization(String webSite, String name, List<Period> periods) {
        Objects.requireNonNull(webSite, "webSite must not be null");
        Objects.requireNonNull(name, "name must not be null");
        this.webSite = webSite;
        this.name = name;
        this.periods = periods;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getName() {
        return name;
    }

    public List<Period> getPeriod() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!webSite.equals(that.webSite)) return false;
        if (!name.equals(that.name)) return false;
        return periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        int result = webSite.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + periods.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "webSite='" + webSite + '\'' +
                ", name='" + name + '\'' +
                ", periods=" + periods +
                '}';
    }

    static class Period {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

        public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Period period = (Period) o;

            if (!startDate.equals(period.startDate)) return false;
            if (!endDate.equals(period.endDate)) return false;
            if (!title.equals(period.title)) return false;
            return Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            result = 31 * result + title.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }
    }
}
