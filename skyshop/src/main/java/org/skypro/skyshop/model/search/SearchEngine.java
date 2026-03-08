package org.skypro.skyshop.model.search;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> items = new HashSet();

    public SearchEngine() {
    }

    public void add(Searchable item) {
        this.items.add(item);
    }

    public Set<Searchable> search(String searchTerm) {
        return (Set)this.items.stream().filter((item) -> item.getSearchTerm().toLowerCase().contains(searchTerm.toLowerCase())).collect(Collectors.toCollection(() -> new TreeSet(new SearchableComparator())));
    }
}
