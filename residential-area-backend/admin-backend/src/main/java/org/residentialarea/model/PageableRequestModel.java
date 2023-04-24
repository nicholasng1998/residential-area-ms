package org.residentialarea.model;

import lombok.Data;

@Data
public class PageableRequestModel {
    private int pageSize;
    private int pageNumber;
}
