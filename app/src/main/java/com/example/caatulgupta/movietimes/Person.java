
package com.example.caatulgupta.movietimes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Person {

    String biography;
    String birthday;
    String deathday;
    Long id;
    @SerializedName("imdb_id")
    String imdbId;
    String name;
    @SerializedName("place_of_birth")
    String placeOfBirth;
    @SerializedName("profile_path")
    String profilePath;

}
