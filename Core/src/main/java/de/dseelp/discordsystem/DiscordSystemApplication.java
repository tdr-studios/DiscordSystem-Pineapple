package de.dseelp.discordsystem;

import de.dseelp.discordsystem.api.BotConfig;
import de.dseelp.discordsystem.api.Discord;
import de.dseelp.discordsystem.api.DiscordBot;
import de.dseelp.discordsystem.core.spring.components.ConsoleService;
import de.dseelp.discordsystem.core.spring.components.ModuleService;
import de.dseelp.discordsystem.core.spring.event.AppFinishedStartedEvent;
import de.dseelp.discordsystem.core.spring.listeners.AppFinishedStartedListener;
import de.dseelp.discordsystem.core.spring.listeners.ApplicationContextInitialized;
import de.dseelp.discordsystem.utils.console.ConsoleSystem;
import lombok.Getter;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class DiscordSystemApplication {

	@Getter
	private static ConfigurableApplicationContext context;

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
		application.setBannerMode(Banner.Mode.OFF);
		context = application.run();
		context.publishEvent(new AppFinishedStartedEvent(context));
	}

	public static void stopServices() {
		if (context != null) {
			ModuleService moduleService = context.getBean(ModuleService.class);
			if (moduleService != null) moduleService.stop();
			Discord.getBot().stop();
			ConsoleService consoleService = context.getBean(ConsoleService.class);
			if (consoleService != null) {
				consoleService.stop();
			}
			context.close();
		}
	}

}
