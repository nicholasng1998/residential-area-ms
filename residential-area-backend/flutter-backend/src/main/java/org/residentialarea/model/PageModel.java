package org.residentialarea.model;

import lombok.Data;

import java.util.List;

@Data
public class PageModel<T> {
    private List<T> content;
    private int size;
    private int totalPages;
    private int numberOfElements;
    private Long totalElements;

}
