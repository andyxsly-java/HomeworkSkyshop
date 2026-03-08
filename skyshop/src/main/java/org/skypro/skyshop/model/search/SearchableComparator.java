package org.skypro.skyshop.model.search;

import java.util.Comparator;

public class SearchableComparator implements Comparator<Searchable> {
    public SearchableComparator() {
    }

    public int compare(Searchable o1, Searchable o2) {
        int lengthCompare = Integer.compare(o2.getSearchableName().length(), o1.getSearchableName().length());
        return lengthCompare != 0 ? lengthCompare : o1.getSearchableName().compareTo(o2.getSearchableName());
    }
}
