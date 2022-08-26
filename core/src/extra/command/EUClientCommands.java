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

public class EUCommands {
    public void Command() {
        Log.info("E-U |--> Commands are loading...");
    }
    
    public static String pause (String[] args, Player player) {
        if (player.admin) {
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
        else {
            player.sendMessage("[scarlet] You must be admin.");
        }
    }
    
    public static String timer (String[] args, Player player) {
        
    }
}
