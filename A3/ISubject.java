public interface ISubject {

    public void Attach(IObserver IObserver);
    public void Detach(IObserver IObserver);
    public void NotifyObservers(Asteroid asteroid);

}
