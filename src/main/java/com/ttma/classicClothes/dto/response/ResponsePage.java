package com.ttma.classicClothes.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponsePage<T>{
    private int pageNo;
    private int pageSize;
    private int totalPage;
    private T items;

}
