package by.training.jwd.finalproject.controller.command;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@code CommandProvider} class represents command provider.
 *
 * @author Sergei Klimovich
 * @version 1.0
 */
public class CommandProvider {
	private static final Logger logger = LogManager.getLogger(CommandProvider.class);

	private CommandProvider() {
	}

	/**
	 * Define command.
	 *
	 * @param commandName the command name
	 * @return the optional of created command
	 */
	public static Optional<ActionCommand> defineCommand(String commandName) {
		Optional<ActionCommand> currentCommand;
		if (commandName != null && !commandName.trim().isEmpty()) {
			try {
				CommandName currentType = CommandName.valueOf(commandName.toUpperCase());
				currentCommand = Optional.of(currentType.getCommand());
			} catch (IllegalArgumentException e) {
				logger.log(Level.ERROR, "Incorrect command type", commandName, e);
				currentCommand = Optional.empty();
			}
		} else {
			currentCommand = Optional.empty();
		}
		return currentCommand;
	}
}
