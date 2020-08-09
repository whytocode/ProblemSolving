package problems;

public class Josephus {

    public Josephus() {
    }
    public int solveFor(int N,int interval) {
        return josephus(N,interval);
    }

    private int josephus(int n,int interval) {
        if(n == 1) {
            return 1;
        }
        else {
            return (josephus( (n-1),interval) + interval-1)%n +1;
        }
    }
}
