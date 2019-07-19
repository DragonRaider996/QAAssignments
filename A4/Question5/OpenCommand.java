public class OpenCommand extends Command {
  @Override
  String getCommand() {
    return "open -f <path> [-create=0/1]";
  }
}
