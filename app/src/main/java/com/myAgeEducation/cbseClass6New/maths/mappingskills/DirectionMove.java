package com.myAgeEducation.cbseClass6New.maths.mappingskills;

public class DirectionMove
{
    private final int distance;
    private final String direction;

    public DirectionMove(int distance, String direction)
    {
        this.distance = distance;
        this.direction = direction;
    }

    public int getDistance()
    {
        return distance;
    }

    public String getDirection()
    {
        return direction;
    }
}
