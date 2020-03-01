package com.shawncos.springbootcrud.community.dto;


import com.shawncos.springbootcrud.community.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class PaginationDTO {


    private List<QuestionDTO> question;

    private boolean showPrevious;

    private boolean showNext;

    private boolean showFirstPage;

    private boolean showEndPage;

    private Integer page;


    private List<Integer> pageNumber;

    private Integer totalPage;

    private List<Integer> pages = new ArrayList<>(12);

    public void setPagination(Integer totalCount, Integer page, Integer size) {


        Integer total = totalCount / size;
        totalPage = totalCount % size == 0 ? total : total + 1;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        pages.sort((x1, x2) -> {
            if (x1 > x2) {
                return 1;
            }
            return -1;
        });
        this.page = page;
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;
        showPrevious = page != 1;
        showNext = page != totalPage;
        showFirstPage = !pages.contains(1);
        showEndPage = !pages.contains(totalPage);
        System.out.println(showFirstPage);
    }
}
