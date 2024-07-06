package com.sinlauncher.app.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.sinlauncher.app.App;

public class Manifest {
    public static final Path PATH  = Paths.get(App.DIR, "manifest_version.json");

    public class Latest {
        public String release;
        public String snapshot;
    }
    
    public class Version {
        public String id;
        public String type;
        public String url;
        public String time;
        public String releaseTime;  
    }

    public Latest latest;
    public Version[] versions;

    public static Manifest readManifest() throws IOException {
        return new Gson().fromJson(Files.readString(PATH), Manifest.class);
    }
}