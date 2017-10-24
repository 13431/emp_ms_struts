package ognl;

public class Person {

    private String name;

    private Job job;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Job job) {
        this.name = name;
        this.job = job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Job getJob() {
        return job;
    }
}
