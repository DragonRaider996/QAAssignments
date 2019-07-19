public class PrintCommand extends Command {
  @Override
  String getCommand() {
    return "print -f <path> [-colour=0/1] [-two-sided=0/1]";
  }
}
