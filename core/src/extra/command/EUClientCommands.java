package extra.commands.client;

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
    public void Command() {
        Log.info("E-U |--> Commands are loading...");
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
    }
    
    public static void timer(String[] args, Player player) {
        player.sendMessage("Soon?");
    }
}
