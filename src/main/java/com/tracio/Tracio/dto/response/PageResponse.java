package com.tracio.Tracio.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
public class PageResponse<T> {
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    @Builder.Default
    private List<T> data = Collections.emptyList();
}
