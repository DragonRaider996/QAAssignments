public abstract class BoardComponentDecorator extends BoardComponent {
    protected BoardComponent boardComponent;

    public BoardComponentDecorator(BoardComponent boardComponent) {
        this.boardComponent = boardComponent;
    }

}
