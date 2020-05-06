package hw2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PercolationStats {
    private List<Double> x;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        //validate
        if (N < 0)  throw new IllegalArgumentException("N is less than 0.");
        if (T < 0)  throw new IllegalArgumentException("T is less than 0.");

        PercolationFactory percolationFactory = new PercolationFactory();
        x = new ArrayList<Double>();
        for (int i = 0; i < T; i ++) {
            Percolation experiment = percolationFactory.make(N);
            //随机Row,Col
            Random random = new Random();
            int randomRow = random.nextInt(N);
            int randomCol = random.nextInt(N);

            while (!experiment.percolates()){
                //随机open一个site
                randomRow = random.nextInt(N);
                randomCol = random.nextInt(N);
                experiment.open(randomRow, randomCol);
            }

            x.add( (double)experiment.numberOfOpenSites() / (N*N) );
        }

    }


    // sample mean of percolation threshold
    public double mean() {
        double sum = 0;
        for (double xi: x) {
            sum += xi;
        }
        return sum/x.size();
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double mean = mean();
        double sum = 0;
        for (double xi: x) {
            sum += Math.pow(xi - mean, 2);
        }
        return Math.sqrt( sum / (x.size() - 1) );
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96*stddev()/Math.sqrt(x.size());
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96*stddev()/Math.sqrt(x.size());
    }
}

