package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

abstract public class ListSection extends AbstractSection {
    private final List<String> items;

    protected ListSection(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        Objects.requireNonNull(items, "items must not be null");
        return items;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}
