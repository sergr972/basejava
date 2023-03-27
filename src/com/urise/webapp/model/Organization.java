package com.urise.webapp.model;

import com.urise.webapp.Util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.urise.webapp.Util.DateUtil.NOW;
import static com.urise.webapp.Util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private String webSite;
    private String name;
    private List<Period> periods = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, String webSite, Period... periods) {
        this(name, webSite, Arrays.asList(periods));
    }

    public Organization(String name, String webSite, List<Period> periods) {
        this.name = name;
        this.webSite = webSite;
        this.periods = periods;
    }

    public String getName() {
        return name;
    }

    public String getWebSite() {
        return webSite;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(webSite, that.webSite)
                && name.equals(that.name)
                && periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(webSite, name, periods);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name= ' " + name + '\'' +
                ", webSite='" + webSite + '\'' +
                ", periods=" + periods +
                '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String title;
        private String description;

        public Period() {
        }

        public Period(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Period(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
            return startDate.equals(period.startDate)
                    && endDate.equals(period.endDate)
                    && title.equals(period.title)
                    && Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }

        @Override
        public String toString() {
            return "Period(" + startDate + ',' + endDate + ',' + title + ',' + description + ')';
        }
    }
}
