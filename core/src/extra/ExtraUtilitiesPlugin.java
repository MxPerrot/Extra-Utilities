package extra;

import arc.util.*;
import extra.Commands;


public class ExtraUtilitiesPlugin extends mindustry.mod.Plugin {
    public ExtraUtilitiesPlugin() {
        Log.info("|--> Extra-Utilities is loading...");
    }
    public void loadContent() {
        new Commands().load();
    }
}
