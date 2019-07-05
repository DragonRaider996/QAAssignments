import java.util.ArrayList;

public class AsteroidImpact implements ISubject {

    private ArrayList<IObserver> observers;
    private static AsteroidImpact asteroidImpact = null;

    @Override
    public void Attach(IObserver observer) {
        this.observers.add(0,observer);
    }

    @Override
    public void Detach(IObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void NotifyObservers(Asteroid asteroid) {
        for(int i=0;i<observers.size();i++){
            IObserver observer = observers.get(i);
            observer.Update(asteroid);
        }
    }

    private AsteroidImpact(){
        this.observers = new ArrayList<IObserver>();
    }

    public static AsteroidImpact getAsteroidImpact(){
        if(asteroidImpact == null){
            asteroidImpact = new AsteroidImpact();
        }
        return asteroidImpact;
    }
}
