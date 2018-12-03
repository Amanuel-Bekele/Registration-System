package com.waaproject.registrationsystem.business;

import java.util.List;

public class CourseAvailable {

    private String block;

    private List<CourseInBlock> blockCourses;

    public CourseAvailable(){}

    public CourseAvailable(String block, List<CourseInBlock> blockCourses) {
        this.block = block;
        this.blockCourses = blockCourses;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public List<CourseInBlock> getBlockCourses() {
        return blockCourses;
    }

    public void setBlockCourses(List<CourseInBlock> blockCourses) {
        this.blockCourses = blockCourses;
    }
}
