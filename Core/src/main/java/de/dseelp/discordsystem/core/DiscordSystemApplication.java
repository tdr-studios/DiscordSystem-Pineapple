package de.dseelp.discordsystem.core;

import de.dseelp.discordsystem.core.spring.components.ConsoleService;
import de.dseelp.discordsystem.core.spring.components.ModuleService;
import de.dseelp.discordsystem.core.spring.event.AppFinishedStartedEvent;
import de.dseelp.discordsystem.core.spring.listeners.ApplicationContextInitialized;
import de.dseelp.discordsystem.core.spring.listeners.AppFinishedStartedListener;
import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordBot;
import de.dseelp.discordsystem.api.commands.CommandSystem;
import de.dseelp.discordsystem.utils.EventManager;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DiscordSystemApplication {

	@Getter
	private static ConfigurableApplicationContext context;

	@Bean
	public DiscordBot discordBot() {
		System.out.println("Creating DC Bot");
		return new DiscordBot(BotConfig.getToken(), new EventManager(), new CommandSystem());
	}

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(DiscordSystemApplication.class);
		application.addListeners(
				new ApplicationContextInitialized(),
				new AppFinishedStartedListener());
		application.setAdditionalProfiles("sqlite");
		context = application.run();
		context.publishEvent(new AppFinishedStartedEvent(context));
	}

	public static void shutdown() {
		context.publishEvent(new AppFinishedStartedEvent(context));
		context.getBean(ModuleService.class).stop();
		Discord.getBot().stop();
		context.getBean(ConsoleService.class).stop();
		context.close();
	}

}
