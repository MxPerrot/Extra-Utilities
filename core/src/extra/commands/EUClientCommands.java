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
    
    public static void Command() {
        Log.info("E-U |--> Commands are loading...");
    }
    public static void init() {
        Events.on(EventType.WorldLoadEvent.class, e -> {
            boolean running = false;
        });
    }
    
    public static void pause(String[] arg, Player player) {
        if (!player.admin) {
            player.sendMessage("[scarlet] You must be admin.");
            return;
        }
            if (arg[0].equals("on")) {
                if (state.serverPaused == false) {
                    state.serverPaused = true;
                    Call.sendMessage("[#bebebe]Server [green]paused [#bebebe]by [#ffffff] " + player.name + ".");
                }
                else if (state.serverPaused == true) {
                    player.sendMessage("[scarlet]Server is already paused.");
                }
            }
            if (arg[0].equals("off")) {
                if (state.serverPaused == true) {
                    state.serverPaused = false;
                    Call.sendMessage("[#bebebe]Server [red]unpaused [#bebebe]by [#ffffff] " + player.name + ".");
                }
                else if (state.serverPaused == false) {
                    player.sendMessage("[scarlet]Server is already unpaused.");
                }
            }
            if (!(arg[0].equals("on") || arg[0].equals("off"))) {
                player.sendMessage("[scarlet]Need argument 'on' or 'off'.");
            }
        }
    
    public static void timer(String[] args, Player player) {
        if (args[0].equals("start")) {
            if (running = false) {
                running = true;
                player.sendMessage("[#bebebe]T'a vraiment cru ça allait marcher ? :heee:");
            }
            else if (running = true) {
                player.sendMessage("[scarlet]Timer is already running.");
            }
        }
        if (args[0].equals("stop") && running == true) {
            if (running = true) {
                running = false;
                player.sendMessage("[#bebebe]T'a vraiment cru ça allait marcher ? :heee:");
            }
            else if (running = false) {
                player.sendMessage("[scarlet]Timer is already stopped.");
            }
        }
        if (args[0].equals("reset")) {
            running = false;
            player.sendMessage("[#bebebe]T'a vraiment cru ça allait marcher ? :heee:");
            //player.sendMessage("[#bebebe]Timer was [blue]reset []by [#ffffff]" + player.name + ".");
        }
        if (!(args[0].equals("start") || args[0].equals("stop") || args[0].equals("reset"))) {
            player.sendMessage("[scarlet]Need argument 'start', 'stop' or 'reset'");
            return;
        }
    }
}