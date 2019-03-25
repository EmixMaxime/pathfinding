public interface Step {
    /**
     * Mark the step as seen
     */
    void mark();


    /**
     * Get if the step had already been seen
     * @return true if the step had already been seen
     */
    boolean isMarked();

    /**
     * Get the Value of the Step
     * @returnIf value is empty it's the path, else it can be a wall, the start or the end.
     */
    String getValue();

    /**
     * Compare 2 steps
     * @param stepCompare
     * @return true if the step are the same
     */
    boolean equals(Step stepCompare);



}
