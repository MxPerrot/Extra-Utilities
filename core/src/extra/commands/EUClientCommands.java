package extra.commands;

import arc.*;
import arc.func.*;
import arc.util.*;
import mindustry.core.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.net.*;
import static mindustry.Vars.player;
import static mindustry.Vars.state;
import static mindustry.Vars.netServer;
import static mindustry.Vars.netClient;

public class EUClientCommands {
    public static void Command(String[] args) {
        boolean isTimerActive;
        Log.info("E-U |--> Commands are loading...");
    }
    public void init() {
        Events.on(EventType.WorldLoadEvent.class, e -> {
            boolean isTimerActive = false;
        });
    }
    
    public static void pause(String[] args, Player player) {
        if (!player.admin) {
            player.sendMessage("[scarlet] You must be admin.");
            return;
        }
            if (args[0].equals("on")) {
                if (state.serverPaused == false) {
                    state.serverPaused = true;
                    Call.sendMessage("[#bebebe]Server [green]paused [#bebebe]by [#ffffff] " + player.name + ".");
                }
                else if (state.serverPaused == true) {
                    player.sendMessage("[scarlet]Server is already paused.");
                }
            }
            if (args[0].equals("off")) {
                if (state.serverPaused == true) {
                    state.serverPaused = false;
                    Call.sendMessage("[#bebebe]Server [red]unpaused [#bebebe]by [#ffffff] " + player.name + ".");
                }
                else if (state.serverPaused == false) {
                    player.sendMessage("[scarlet]Server is already unpaused.");
                }
            }
            if (!(args[0].equals("on") || args[0].equals("off"))) {
                player.sendMessage("[scarlet]Need argument 'on' or 'off'.");
            }
        }
    
    public static void timer(String[] args, Player player) {
        if (args[0].equals("start")) {
            if (isTimerActive.equals(false)) {
                boolean isTimerActive = true;
                player.sendMessage("[#bebebe]T'a vraiment cru ça allait marcher ? :heee:");
            }
            else if (isTimerActive.equals(true)) {
                player.sendMessage("[scarlet]Timer is already running.");
            }
        }
        if (args[0].equals("stop")) {
            if (isTimerActive.equals(true)) {
                boolean isTimerActive = false;
                player.sendMessage("[#bebebe]T'a vraiment cru ça allait marcher ? :heee:");
            }
            else if (isTimerActive.equals(false)) {
                player.sendMessage("[scarlet]Timer is already stopped.");
            }
        }
        if (args[0].equals("reset")) {
            boolean isTimerActive = false;
            player.sendMessage("[#bebebe]T'a vraiment cru ça allait marcher ? :heee:");
            //player.sendMessage("[#bebebe]Timer was [blue]reset []by [#ffffff]" + player.name + ".");
        }
        if (!(args[0].equals("start") || args[0].equals("stop") || args[0].equals("reset"))) {
            player.sendMessage("[scarlet]Need argument 'start', 'stop' or 'reset'");
            return;
        }
    }
}