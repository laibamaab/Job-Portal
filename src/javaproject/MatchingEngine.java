package javaproject;

import java.util.ArrayList;
import java.util.List;

public class MatchingEngine {
    private List<Job> jobs; // List of all jobs
    private List<Job> jobRecommendations; // Recommendations for the current job seeker
    private Notification notification;

    public MatchingEngine() {
        this.jobs = new ArrayList<>();
        this.jobRecommendations = new ArrayList<>();
    }

    public void addJob(Job job) {
        jobs.add(job);
        System.out.println("Job added: " + job.getTitle());
    }

    public void matchJobs(JobSeeker jobSeeker) {
        System.out.println("Matching jobs for: " + jobSeeker.getName());
        jobRecommendations.clear(); // Clear previous recommendations

        for (Job job : jobs) {
            if (job.isEligible(jobSeeker)) { // Check eligibility
                jobRecommendations.add(job);
            }
        }

        if (jobRecommendations.isEmpty()) {
            System.out.println("No matching jobs found for " + jobSeeker.getName());
        } else {
            System.out.println("Matching jobs for " + jobSeeker.getName() + ":");
            for (Job job : jobRecommendations) {
                System.out.println(" - " + job.getTitle());
                // Automatically apply for the job
                Application application = Application.createApplication(job, jobSeeker);
          }
        }
    }

    public void generateRecommendation() {
        System.out.println("Generating job recommendations:");
        for (Job job : jobRecommendations) {
            System.out.println("Recommended Job: " + job.getTitle());
            notification = notification.createNotification("recommendation", job.getTitle());

        }
    }
}
