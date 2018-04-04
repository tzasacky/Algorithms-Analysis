package clustering;

public interface FeatureVector extends Comparable<FeatureVector> {

    public double distance(FeatureVector otherFv);

    public String getLabel();

    public int getM();
    public double get(int i);
    public double getNormalized(int i);

    public void normalize(double[] mins, double[] maxes);

    public FeatureVector clone();
    public void updateCentroid(double[] features, String label);
}
