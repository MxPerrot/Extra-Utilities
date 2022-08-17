package auto;

import arc.*;
import arc.Events;
import arc.util.*;
import arc.util.CommandHandler;
import arc.util.Log;
import arc.util.Strings;
import mindustry.*;
import mindustry.game.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.core.*;
import mindustry.core.GameState.*;
import mindustry.net.Administration.*;
import static arc.util.Log.info;

public class AutoPlugin extends Plugin{
    private int playerCount = 0;

    @Override
    public void init(){
        Events.on(EventType.WorldLoadEvent.class, event -> {
            if (playerCount == 0 && Vars.state.serverPaused == false) {
                String message = Strings.format("[Auto-Pause] [green]ON");
                Vars.state.serverPaused = true;
            }
        });
        Events.on(EventType.PlayerJoin.class, event -> {
            if (playerCount == 0 && Vars.state.serverPaused == false) {
                String message = Strings.format("[Auto-Pause] [red]OFF");
                Vars.state.serverPaused = false;
            }
            playerCount += 1;
        });
        Events.on(EventType.PlayerLeave.class, event -> {
            playerCount -= 1;
            if (playerCount == 0 && Vars.state.serverPaused == false) {
                String message = Strings.format("[Auto-Pause] [green]ON");
                Vars.state.serverPaused = false;
            }
        });
    }
    @Override
    public void registerClientCommands(CommandHandler handler) {
        handler.<Player>register("pause", "<on/off>", "Pause/Unpause the game.", (args, player) -> {
            if (arg.length == 0) {
                player.sendMessage("[scarlet]Error: Argument 'on' or 'off' required.");
            }
            if (arg[0].equals("on")) {
                Vars.state.serverPaused = true;
                String message = Strings.format("[Pause] [green]ON");
            }
            if (arg[0].equals("off")){
                Vars.state.serverPaused = false;
                String message = Strings.format("[Pause] [red]OFF");
            }
        });
    } 
}
