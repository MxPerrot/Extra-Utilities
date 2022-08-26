package extra.commands;

import java.util.*;
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
    boolean running = false;
    float s = 0;
    float m = 0;
            
    public void Command() {
        Log.info("E-U |--> Commands are loading...");
        
        while (running = true) {
            sleep(1000);
            s++;
            while (s > 59) {
                float s = (s - 60);
                m++;
            }
        }
    }
    public void init() {
        Events.on(EventType.WorldLoadEvent.class, e -> {
            boolean running = false;
            float s = 0;
            float m = 0;
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
        if (args[0].equals("start") && running == false) {
            boolean running = true;
            setObjectives("[#bebebe]Timer :[#ffffff] \n" + m + ":" + s);
            }
        if (args[0].equals("stop") && running == true) {
            boolean running = false;
        }
        if (args[0].equals("reset")) {
            boolean running = false;
            int s = 0;
            int m = 0;
        }
        if (!(args[0].equals("start") || args[0].equals("stop") || args[0].equals("reset"))) {
            player.sendMessage("[scarlet]Need argument 'start', 'stop' or 'reset'");
            return;
        }
    }
}