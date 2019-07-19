import java.util.HashMap;

public class Help {
	private HashMap<String, Command> commands;

	public Help() {
		commands = new HashMap<>();
		commands.put("print", new PrintCommand());
		commands.put("open", new OpenCommand());
		commands.put("close", new CloseCommand());
	}

	public String getHelp(String command) {
		if (command != null && command.length() != 0) {
			Command commandToExecute = commands.get(command);
			return commandToExecute.getCommand();
		}
		return listAllCommands();
	}

	public String listAllCommands() {
		return "Commands: print, open, close";
	}
}