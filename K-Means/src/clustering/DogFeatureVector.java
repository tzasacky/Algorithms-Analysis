package clustering;

public class DogFeatureVector implements FeatureVector {

    private final String breedName;

    private final double weight;
    private final double height;

    private double normalizedWeight;
    private double normalizedHeight;

    private String label;

    /**
     * Initializes a dog feature vector.
     * @param breedName - the dog's breed
     * @param weight - the dog's weight in pounds
     * @param height - the dog's height in inches
     * @param label - the dog's size label
     */
    public DogFeatureVector(String breedName, double weight, double height, String label) {
        this.breedName = breedName;

        this.weight = weight;
        this.height = height;

        this.normalizedWeight = -1;
        this.normalizedHeight = -1;

        this.label = label;
    }

    /**
     * Initializes a centroid.
     * @param other - the feature vector to base the centroid on
     */
    public DogFeatureVector(DogFeatureVector other) {
        this.breedName = null;

        this.weight = other.getWeight();
        this.height = other.getHeight();

        this.normalizedWeight = other.getNormalizedWeight();
        this.normalizedHeight = other.getNormalizedHeight();

        this.label = other.getLabel();
    }

    @Override
    public FeatureVector clone() {
        return new DogFeatureVector(this);
    }

    @Override
    public void updateCentroid(double[] features, String label) {
        assert(features.length == 2);

        this.normalizedWeight = features[0];
        this.normalizedHeight = features[1];

        this.label = label;
    }

    public String getBreedName() {
        return this.breedName;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getHeight() {
        return this.height;
    }

    public double getNormalizedWeight() {
        return this.normalizedWeight;
    }

    public double getNormalizedHeight() {
        return this.normalizedHeight;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public int getM() {
        return 2;
    }

    public void normalize(double[] mins, double[] maxes) {
        this.normalizedWeight = (this.weight - mins[0]) / (maxes[0] - mins[0]);
        this.normalizedHeight = (this.height - mins[1]) / (maxes[1] - mins[1]);
    }

    // Allows for treating the dog feature vector as an actual vector
    @Override
    public double get(int i) {
        switch (i) {
            case 0:
                return this.weight;
            case 1:
                return this.height;
            default:
                return 0.0;
        }
    }

    // Allows for treating the dog feature vector as an actual vector
    @Override
    public double getNormalized(int i) {
        switch (i) {
            case 0:
                return this.normalizedWeight;
            case 1:
                return this.normalizedHeight;
            default:
                return 0.0;
        }
    }

    @Override
    public double distance(FeatureVector otherFv) {
        if (!(otherFv instanceof DogFeatureVector)) {
            return Double.MAX_VALUE;
        }

        DogFeatureVector that = (DogFeatureVector)otherFv;
        return Math.sqrt(Math.pow(this.normalizedWeight - that.getNormalizedWeight(), 2) +
                         Math.pow(this.normalizedHeight - that.getNormalizedHeight(), 2));
    }

    public String toString() {
        if (this.breedName == null) {
            // It's a centroid
            return String.format("(%.3f,  %.3f) - %s", this.normalizedWeight, this.normalizedHeight, this.label);
        } else {
            // It's a real dog data point
            return String.format("(%.0f, %.0f) - %s (%s)", this.weight, this.height, this.breedName, this.label);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof DogFeatureVector)) return false;
        DogFeatureVector that = (DogFeatureVector) other;
        return this.breedName == that.getBreedName();
    }

    @Override
    public int compareTo(FeatureVector other) {
        if (!(other instanceof DogFeatureVector)) return -1;
        DogFeatureVector that = (DogFeatureVector) other;
        return this.breedName.compareTo(that.getBreedName());
    }
}
