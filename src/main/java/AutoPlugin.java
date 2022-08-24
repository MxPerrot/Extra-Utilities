package auto;

import arc.*;
import arc.Events;
import arc.Core;
import arc.files.Fi;
import arc.math.Mathf;
import arc.struct.ObjectMap;
import arc.struct.ObjectSet;
import arc.struct.Seq;
import arc.util.*;
import arc.util.CommandHandler;
import arc.util.Strings;
import arc.util.Log;
import arc.util.Timer;
import mindustry.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.core.*;
import mindustry.core.NetClient;
import mindustry.net.Administration.*;
import mindustry.content.Blocks;
import mindustry.game.*;
import mindustry.game.EventType;
import mindustry.game.Gamemode;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.gen.Groups;
import mindustry.gen.Player;
import mindustry.maps.Map;
import mindustry.net.Packets.*;
import static mindustry.Vars.*;
import static mindustry.Vars.content;
import static mindustry.Vars.maps;
import static mindustry.Vars.netServer;
import static mindustry.Vars.state;
import static mindustry.Vars.world;
import static arc.util.Log.info;


public class AutoPlugin extends Plugin{
    private int playerCount = 0;

    public AutoPlugin(){
        Log.info("Auto-Pause plugin is loading...");
    }

    public void init(){
        Events.on(EventType.WorldLoadEvent.class, event -> {
            if (playerCount == 0 && Vars.state.serverPaused == false) {
                String message = Strings.format("[Auto-Pause] [green]ON");
                Vars.state.serverPaused = true;
            }
        });
        Events.on(EventType.PlayerJoin.class, event -> {
            if (playerCount == 0 && Vars.state.serverPaused == true) {
                String message = Strings.format("[Auto-Pause] [red]OFF");
                Vars.state.serverPaused = false;
            }
            playerCount += 1;
        });
        Events.on(EventType.PlayerLeave.class, event -> {
            playerCount -= 1;
            if (playerCount == 0 && Vars.state.serverPaused == false) {
                String message = Strings.format("[Auto-Pause] [green]ON");
                Vars.state.serverPaused = true;
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
                if (Vars.state.serverPaused == false) {
                    Vars.state.serverPaused = true;
                    String message = Strings.format("[Pause] [green]ON");
                }
                else {
                    player.sendMessage("[scarlet]Server is already paused.");
                }
            }
            if (arg[0].equals("off")){
                if (Vars.state.serverPaused == true) {
                    Vars.state.serverPaused = false;
                    String message = Strings.format("[Pause] [red]OFF");
                }
                else {
                    player.sendMessage("[scarlet]Server is already unpaused.");
                }
            }
        });
    } 
}
