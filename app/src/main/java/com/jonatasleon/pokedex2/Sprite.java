package com.jonatasleon.pokedex2;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jonatasleon on 21/09/16.
 */
public class Sprite {
    @SerializedName("name")
    private String name;
    @SerializedName("resource_uri")
    private String resourceUri;

    public Sprite(String name, String resourceUri) {
        this.name = name;
        this.resourceUri = resourceUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceUri() {
        return resourceUri.substring(1);
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }
}
