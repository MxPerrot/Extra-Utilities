package extra.commands;

import arc.Events;
import arc.func.Cons;
import arc.util.CommandHandler;
import arc.util.Log;
import arc.util.Timer;
import arc.util.CommandHandler.CommandRunner;
import mindustry.mod.*;
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

public class Commands {
    public Command() {
        Log.info("E-U |--> Commands loaded.");
    }

    public void registerClientCommands(final CommandHandler handler) {
        handler.<Player>register("pause", "<on/off>", "Pause/Unpause the game.", (arg, player) -> {
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
        });
    }

    public void registerServerCommands(final CommandHandler handler) {
        
    }
}