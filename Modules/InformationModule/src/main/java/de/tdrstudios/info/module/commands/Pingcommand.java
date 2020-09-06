package de.tdrstudios.info.module.commands;

import de.dseelp.discordsystem.api.commands.*;
import de.dseelp.discordsystem.utils.console.logging.LogSystem;
import de.tdrstudios.info.module.InformationModule;
import de.tdrstudios.info.module.ping.Ping;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;

public class Pingcommand extends Command {

    private LogSystem logSystem;
    public Pingcommand(){
        super(new RolePermission("info_ping"), "Check the reachablility of a Server", CommandType.GUILD_AND_CONSOLE, "ping","check");
        logSystem = InformationModule.getInstance().getLogSystem();
    }


    @SneakyThrows
    public void execute(CommandSender sender, String[] args, Command command) {

        if(sender instanceof ConsoleCommandSender) {
            logSystem.lineSeperator();
            logSystem.write("Start Ping Progress! ");
            logSystem.write("User -> " + " Console");
            logSystem.write("Host -> " + args[0]);
            logSystem.lineSeperator();

            if(Ping.ping(args[0])) {

                logSystem.write("[Ping] Successfully!");
            }else {
                logSystem.warning("[Ping] Server " + args[0] + " is unreachable!");
            }
        } else if (sender instanceof DiscordGuildCommandSender) {

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Ping");
            eb.addField("Host", args[0] , false);

          //  eb.addField("Host");
            logSystem.lineSeperator();
            logSystem.write("Start Ping Progress! ");
            logSystem.write("User -> " + sender.getName());
            logSystem.write("Host -> " + args[0]);
            logSystem.lineSeperator();

           ;
            if(Ping.ping(args[0])) {
                eb.addField("Status", "Server is reachable ✅" , false);

                logSystem.write("[Ping] Successfully!" + " ✅");
            }else {
                logSystem.warning("[Ping] Server " + args[0] + " is unreachable! ❌");
                eb.addField("Status", "Server is unreachable ❌" , false);
            }
        }



    }
}
