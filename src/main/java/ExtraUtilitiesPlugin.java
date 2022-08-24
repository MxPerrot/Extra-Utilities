package extra;

import arc.Events;
import arc.func.Cons;
import arc.util.CommandHandler;
import arc.util.Log;
import arc.util.Timer;
import arc.util.CommandHandler.CommandRunner;

import mindustry.mod.Mod;
import mindustry.game.EventType;
import mindustry.core.GameState;
import mindustry.gen.Call;
import mindustry.gen.Groups;
import mindustry.gen.Player;
import mindustry.net.Administration;
import static mindustry.Vars.player;
import static mindustry.Vars.state;
import static mindustry.Vars.netServer;
import static mindustry.Vars.netClient;


public class ExtraUtilitiesPlugin extends mindustry.mod.Plugin {

    public ExtraUtilitiesPlugin() {
        Log.info("|--> Extra-Utilities is loading...");
    }

    public void init(){
        Events.on(EventType.WorldLoadEvent.class, e -> {
            if (state.serverPaused == false && Groups.player.size() == 0) {
                state.serverPaused = true;
                Call.sendMessage("[Auto-Pause] [green]ON");
            }
        });
        Events.on(EventType.PlayerJoin.class, e -> {
            if (state.serverPaused == true && Groups.player.size() == 1) {
                state.serverPaused = false;
                Call.sendMessage("[Auto-Pause] [red]OFF");
            }
        });
        Events.on(EventType.PlayerLeave.class, e -> {
            if (state.serverPaused == false && Groups.player.size()-1 == 0) {
                state.serverPaused = true;
                Call.sendMessage("[Auto-Pause] [green]ON");
            }
        });
    }

    @Override
    public void registerClientCommands(CommandHandler handler) {
        handler.<Player>register("pause", "<on/off>", "Pause/Unpause the game.", (arg, player) -> {
            if (arg.length == 0) {
                player.sendMessage("[scarlet]Error: Argument 'on' or 'off' required.");
            }
            if (arg[0].equals("on") && state.serverPaused == false) {
                state.serverPaused = true;
                Call.sendMessage("[Pause] [green]ON");
            }
            else {
                player.sendMessage("[scarlet]Server is already paused.");
            }
            if (arg[0].equals("off") && state.serverPaused == true) {
                state.serverPaused = false;
                Call.sendMessage("[Pause] [red]OFF");
            }
            else {
                player.sendMessage("[scarlet]Server is already unpaused.");
            }
        });
    }
}