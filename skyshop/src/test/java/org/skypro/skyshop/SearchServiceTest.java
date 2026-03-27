package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.Collection;
import java.util.UUID;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void search_whenStorageIsEmpty_shouldReturnEmptyList() {
        when(storageService.getAllSearchables()).thenReturn(List.of());
        Collection<SearchResult> results = searchService.search("test");

        assertThat(results).isEmpty();
    }

    @Test
    void search_whenStorageNoMatches_shouldReturnEmptyList() {
        Searchable searchable = createSearchable("Toy");
        when(storageService.getAllSearchables()).thenReturn(List.of());
        Collection<SearchResult> result = searchService.search("Phone");

        assertEquals("Phone", result);
    }

    @Test
    void search_whenMatchFound_shouldReturnOneResult() {
        Searchable searchable = createSearchable("Product");
        when(storageService.getAllSearchables()).thenReturn(List.of());
        Collection<SearchResult> result = searchService.search("Book");

        assertEquals("Notebook", result);
    }

    private Searchable createSearchable(String product) {
        return new Searchable() {
            @Override
            public UUID getId() {
                return UUID.randomUUID();
            }

            @Override
            public String getContentType() {
                return "";
            }

            @Override
            public String getSearchTerm() {
                return "";
            }

            @Override
            public String getSearchType() {
                return "";
            }

            @Override
            public String getSearchableName() {
                return "";
            }
        };
    }
}
