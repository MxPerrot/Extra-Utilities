package extra;

import arc.Events;
import arc.func.Cons;
import arc.util.CommandHandler;
import arc.util.Log;
import arc.util.Timer;
import arc.util.CommandHandler.CommandRunner;

import mindustry.mod.Mod;
import mindustry.game.EventType;
import mindustry.gen.Call;
import mindustry.gen.Groups;
import mindustry.gen.Player;
import mindustry.net.Administration;
import static mindustry.Vars.Player;
import static mindustry.Vars.NetServer;
import static mindustry.Vars.NetClient;


public class ExtraUtilitiesPlugin extends mindustry.mod.Plugin {

    public ExtraUtilitiesPlugin() {
        Log.info("|--> Extra-Utilities is loading...");
    }

    public void init(){
        Events.on(EventType.WorldLoadEvent.class, e -> {
            if (Vars.state.serverPaused == false && Groups.player.size() == 0) {
                Vars.state.serverPaused = true;
                Call.sendMessage("[Auto-Pause] [green]ON");
            }
        });
        Events.on(EventType.PlayerJoin.class, e -> {
            if (Vars.state.serverPaused == true && Groups.player.size() == 1) {
                Vars.state.serverPaused = false;
                Call.sendMessage("[Auto-Pause] [red]OFF");
            }
        });
        Events.on(EventType.PlayerLeave.class, e -> {
            if (Vars.state.serverPaused == false && Groups.player.size()-1 == 0) {
                Vars.state.serverPaused = true;
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
            if (arg[0].equals("on") && Vars.state.serverPaused == false) {
                Vars.state.serverPaused = true;
                Call.sendMessage("[Pause] [green]ON");
                else {
                    player.sendMessage("[scarlet]Server is already paused.");
                }
            }
            if (arg[0].equals("off") && Vars.state.serverPaused == true) {
                    Vars.state.serverPaused = false;
                    Call.sendMessage("[Pause] [red]OFF");
                else {
                    player.sendMessage("[scarlet]Server is already unpaused.");
                }
            }
        });
    }
}