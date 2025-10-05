package com.smartcampus.core;

import com.smartcampus.data.model.Destination;
import java.util.*;

public class Pathfinding {

    // Simple A* pathfinding assuming 2D grid, but since no grid, use straight line for now
    // For demo, return a list of points from start to end

    public static List<double[]> findPath(Destination start, Destination end) {
        List<double[]> path = new ArrayList<>();
        // For simplicity, just add start and end, or interpolate points
        path.add(new double[]{start.getX(), start.getY()});
        path.add(new double[]{end.getX(), end.getY()});
        return path;
    }

    // More advanced: A* on a grid, but need grid data
    // For now, keep simple
}
