
package com.example.caatulgupta.movietimes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieCredits {

    @SerializedName("cast")
    List<Movie> movieCast;
    Long id;

}
