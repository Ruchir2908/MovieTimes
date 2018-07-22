
package com.example.caatulgupta.movietimes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TVCategory {

    Long page;
    @SerializedName("results")
    List<TV> shows;
    @SerializedName("total_pages")
    Long totalPages;
    @SerializedName("total_results")
    Long totalResults;

}
