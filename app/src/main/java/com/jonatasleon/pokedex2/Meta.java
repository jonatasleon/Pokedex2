package com.jonatasleon.pokedex2;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jonatasleon on 24/09/16.
 */
public class Meta {

    @SerializedName("limit")
    private Integer limit;

    @SerializedName("next")
    private String next;

    @SerializedName("offset")
    private Integer offset;

    @SerializedName("total_count")
    private Integer totalCount;


    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
