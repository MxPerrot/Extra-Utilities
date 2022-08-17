package auto;

import arc.Events;
import arc.util.*;
import arc.util.CommandHandler;
import arc.util.Log;
import arc.util.Strings;
import mindustry.*;
import mindustry.game.EventType;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.net.Administration;
import static arc.util.Log.info;

public class AutoPlugin extends Plugin{
    private int playerCount = 0;
    //private String playtime = ;

    @Override
    public void init(){
        playtime = Core.settings.getString("playtime", playtime);

        Events.on(EventType.WorldLoadEvent.class, event -> {
            if (playerCount == 0 && Vars.state.ServerPaused == false) {
                String message = Strings.format("Auto-Pause : ON");
                Vars.state.serverPaused = true;
            }
        });
        Events.on(EventType.PlayerJoin.class, event -> {
            playerCount -= 1;
            if (playerCount == 0 && Vars.state.serverPaused == false) {
                String message = Strings.format("Auto-Pause : OFF");
                Vars.state.serverPaused = false;
            }
            playerCount += 1;
        });
        Events.on(EventType.PlayerLeave.class, event -> {
            playerCount -= 1;
            if (playerCount == 0 && Vars.state.serverPaused == false) {
                String message = Strings.format("Auto-Pause : ON");
                Vars.state.serverPaused = false;
            }
        });
    }

    @Override
    public void registerClientCommands(CommandHandler handler){
        handler.<Player>register("playtime", "Get your playtime on the server.", (arg, player) -> {
            player.sendMessage("You have", playtime, "playtime on this server");
        });
    }
    private void save(){
        Core.settings.put("playtime", playtime);
    }
}
