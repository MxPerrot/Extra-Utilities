package extra;

import arc.Core;
import arc.files.Fi;
import arc.math.Mathf;
import arc.struct.ObjectMap;
import arc.struct.ObjectSet;
import arc.struct.Seq;
import arc.util.CommandHandler;
import arc.util.Log;
import arc.util.Timer;

import mindustry.mod.Mod;
import mindustry.core.NetClient;
import mindustry.game.*;
import mindustry.net.Administration;
import static mindustry.Vars;


public class ExtraUtilitiesPlugin extends mindustry.mod.Plugin {

    public ExtraUtilitiesPlugin() {
        Log.info("|--> Extra-Utilities is loading...");
    }

    public void init(){
        Events.on(EventType.WorldLoadEvent.class, e -> {
            if (state.serverPaused == false && Groups.player.size() == 0) {
                state.serverPaused = true;
                String message = Strings.format("[Auto-Pause] [green]ON");
            }
        });
        Events.on(EventType.PlayerJoin.class, e -> {
            if (state.serverPaused == true && Groups.player.size() == 1) {
                state.serverPaused = false;
                String message = Strings.format("[Auto-Pause] [red]OFF");
            }
        });
        Events.on(EventType.PlayerLeave.class, e -> {
            if (state.serverPaused == false && Groups.player.size()-1 == 0) {
                state.serverPaused = true;
                String message = Strings.format("[Auto-Pause] [green]ON");
            }
        });
    }

    @Override
    public void registerClientCommands(CommandHandler handler) {
        handler.<Player>register("pause", "<on/off>", "Pause/Unpause the game.", (arg, player) -> {
            if (arg.length == 0) {
                player.sendMessage("[scarlet]Error: Argument 'on' or 'off' required.");
            }
            if (arg[0].equals("on")) {
                if (state.serverPaused == false) {
                    state.serverPaused = true;
                    String message = Strings.format("[Pause] [green]ON");
                }
                else {
                    player.sendMessage("[scarlet]Server is already paused.");
                }
            }
            if (arg[0].equals("off")){
                if (state.serverPaused == true) {
                    state.serverPaused = false;
                    String message = Strings.format("[Pause] [red]OFF");
                }
                else {
                    player.sendMessage("[scarlet]Server is already unpaused.");
                }
            }
        });
    } 
}
