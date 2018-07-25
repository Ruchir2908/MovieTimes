
package com.example.caatulgupta.movietimes;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Cast implements Serializable{

    String character;
    int id;
    String name;
    int gender;
    @SerializedName("profile_path")
    String profilePath;
    int order;
    String biography;
}
