package extra;

import arc.util.*;
import extra.Commands;

public class ExtraUtilitiesPlugin extends Plugin {
    public ExtraUtilitiesPlugin() {
        Log.info("|--> Extra-Utilities is loading...");
    }
    public void init() {
        //TODO Remove after official update
        Events.on(EventType.WorldLoadEvent.class, e -> {
            if (state.serverPaused == false && Groups.player.size() == 0) {
                state.serverPaused = true;
                Log.info("[E-U] |--> The server was automatically paused.");
            }
        });
        Events.on(EventType.PlayerJoin.class, e -> {
            if (state.serverPaused == true && Groups.player.size() == 1) {
                state.serverPaused = false;
                Log.info("[E-U] |--> The server was automatically unpaused.");
                Call.sendMessage("[#bebebe]Server was [red]unpaused.");
            }
        });
        Events.on(EventType.PlayerLeave.class, e -> {
            if (state.serverPaused == false && Groups.player.size()-1 == 0) {
                state.serverPaused = true;
                Log.info("[E-U] |--> The server was automatically paused.");
            }
        });
    }
    
    @Override
    public void registerClientCommands(final CommandHandler handler) {
        command.registerClientCommands(handler);
    }
    @Override
    public void registerServerCommands(final CommandHandler handler} {
        command.registerServerCommands(handler);
    }
}