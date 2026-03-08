package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    UUID getId();

    String getContentType();

    String getSearchTerm();

    String getSearchType();

    String getSearchableName();

    default String getStringRepresentation() {
        String var10000 = this.getSearchableName();
        return var10000 + " — " + this.getSearchType();
    }
}
