
import java.util.ArrayList;

// Implementation of the Abstract Factory pattern's interface
public class AsteroidGameFactory implements IAsteroidGameFactory
{
	@Override
	public BoardComponent MakeSquare()
	{
		return new Square();
	}

	@Override
	public BoardComponent MakeBuilding()
	{
		Building building = new Building();
		return building;
	}

	@Override
	public Asteroid MakeAsteroid(int height)
	{
		Asteroid asteroid = new Asteroid(height);
		return asteroid;
	}

	@Override
	public BoardComponent MakeShield(BoardComponent boardComponent) {
		Shield shield = new Shield(boardComponent);
		return shield;
	}
	
	@Override
	public ArrayList<ArrayList<BoardComponent>> MakeBoard(int height, int width)
	{
		ArrayList<ArrayList<BoardComponent>> board = new ArrayList<>();
		for (int i = 0; i < height; i++)
		{
			// Make an array for each row.
			ArrayList<BoardComponent> row = new ArrayList<>();
			// Add squares equal to the width to the row.
			for (int j = 0; j < width; j++)
			{
				Square square = (Square) MakeSquare();
				GameBoard.Instance().GetAsteroidImpact().Attach(square);
				row.add(square);
			}
			board.add(row);
		}
		return board;
	}
	
	@Override
	public Command MakeCommand(String commandLine)
	{
		String commandToMake;
		String[] args = null;
		String commandLineArgs = "";
		if (commandLine.contains(" "))
		{
			commandToMake = commandLine.split(" ")[0];
			commandLineArgs = commandLine.substring(commandToMake.length() + 1);
			args = commandLineArgs.split(" ");
		}
		else
		{
			commandToMake = commandLine;
		}
		switch (commandToMake.toUpperCase())
		{
			case "INIT":
			{
				return new InitializeBoardCommand(GameBoard.Instance(), args);
			}
			case "SPAWN_ASTEROID":
			{
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				BoardComponent square = GameBoard.Instance().GetBoard().get(y).get(x);
				return new SpawnAsteroidCommand(square, args);
			}
			case "TICK":
			{
				return new TickCommand(GameBoard.Instance(), args);
			}
			case "START_GAME":
			{
				return new StartGameCommand(GameBoard.Instance(), args);
			}
			case "SPAWN_BUILDING":
			{
				int y = Integer.parseInt(args[1]);
				int x = Integer.parseInt(args[0]);
				BoardComponent boardComponent = GameBoard.Instance().GetBoard().get(x).get(y);
				GameBoard.Instance().IncrementBuildingCount();
				return new SpawnBuildingCommand(boardComponent, args);
			}
			case "SPAWN_SHIELD":
			{
				int y = Integer.parseInt(args[0]);
				int x = Integer.parseInt(args[1]);
				BoardComponent boardComponent = GameBoard.Instance().GetBoard().get(x).get(y);
				return new SpawnShieldCommand(boardComponent, args);
			}
		}
		return null;
	}
}
