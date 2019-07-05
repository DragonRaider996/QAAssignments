import java.util.ArrayList;

public class Shield extends BoardComponentDecorator {
    private int shieldHealth;

    public Shield(BoardComponent boardComponent) {
        super(boardComponent);
        shieldHealth = 2;
        GameBoard.Instance().GetAsteroidImpact().Detach(boardComponent);
        GameBoard.Instance().GetAsteroidImpact().Attach(this);
        ArrayList<ArrayList<BoardComponent>> board = GameBoard.Instance().GetBoard();
        Shield shield = this;
        for (int i = 0; i < board.size(); i++) {
            ArrayList<BoardComponent> row = board.get(i);
            int index = -1;
            index = row.indexOf(boardComponent);
            if (index != -1) {
                row.set(index, shield);
            }
        }
        GameBoard.Instance().SetBoard(board);

    }

    @Override
    public void Operation() {
        this.boardComponent.Operation();
    }

    @Override
    public void Add(BoardComponent child) {
        this.boardComponent.Add(child);
    }

    @Override
    public void Remove(BoardComponent child) {
        this.boardComponent.Remove(child);
    }

    @Override
    public void Update(Asteroid asteroid) {
        Square square = (Square) this.boardComponent;
        if(square.equals(asteroid.parent)){
            if(this.shieldHealth > 0) {
                this.shieldHealth = this.shieldHealth - 1;
                if (this.shieldHealth == 0) {
                    ArrayList<ArrayList<BoardComponent>> board = GameBoard.Instance().GetBoard();
                    Shield shield = this;
                    for (int i = 0; i < board.size(); i++) {
                        ArrayList<BoardComponent> row = board.get(i);
                        int index = row.indexOf(shield);
                        if (index != -1) {
                            BoardComponent child = this.boardComponent;
                            row.set(index, child);
                        }
                    }
                    GameBoard.Instance().SetBoard(board);
                    GameBoard.Instance().GetAsteroidImpact().Detach(this);
                    GameBoard.Instance().GetAsteroidImpact().Attach(boardComponent);
                }
            }
        }
    }
}
