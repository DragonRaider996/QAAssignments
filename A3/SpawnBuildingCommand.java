public class SpawnBuildingCommand extends Command {

    public SpawnBuildingCommand(Object receiver, String[] args) {
        super(receiver, args);
    }

    @Override
    public void Execute() {
        BoardComponent boardComponent = (BoardComponent) receiver;
        IAsteroidGameFactory factory = GameBoard.Instance().GetFactory();
        System.out.println("Spawning building at (" + args[0] + "," + args[1] + ")");
        boardComponent.Add(factory.MakeBuilding());
    }
}
