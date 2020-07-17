package de.dseelp.discordsystem;

import de.dseelp.discordsystem.core.spring.components.ConsoleService;
import de.dseelp.discordsystem.core.spring.components.ModuleService;
import de.dseelp.discordsystem.core.spring.event.AppFinishedStartedEvent;
import de.dseelp.discordsystem.core.spring.listeners.ApplicationContextInitialized;
import de.dseelp.discordsystem.core.spring.listeners.AppFinishedStartedListener;
import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordBot;
import de.dseelp.discordsystem.utils.console.Console;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class DiscordSystemApplication {
	//@Getter
	//@Setter
	 //public static boolean maintenance = false;

	@Getter
	private static ConfigurableApplicationContext context;

	@Getter
	private static final String version = "0.0.1-ALPHA";

	@Bean
	public DiscordBot discordBot() {
		return new DiscordBot(BotConfig.getToken());
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
		Discord.getBot().stop();
		context.getBean(ModuleService.class).stop();
		context.getBean(ConsoleService.class).stop();
		context.close();
	}

}
