package javaproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class JobSeeker {
    private int userID;
    private String name;
    private String email;
    private String phone;
    private String location;
    private Education education;
    private Experience experience;
    private Skills skills;
    private Notification notification;
    private Application application;
    private Job job1, job2, job3;
    private MatchingEngine matchingengine;
    private List<Education> educationList = new ArrayList<>();
    private List<Experience> experienceList = new ArrayList<>();
    private List<Skills> skillsList = new ArrayList<>();
    private static AtomicInteger idGenerator = new AtomicInteger(0);
    private static List<JobSeeker> jobSeekersList = new ArrayList<>();
    private List<Application> applications = new ArrayList<>();

    public JobSeeker(){}

    public JobSeeker(String name, String email, String phone, String location, List<Education> education, List<Skills> skills,
            List<Experience> experience) {
        this.userID = idGenerator.getAndIncrement();
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.educationList = education;
        this.experienceList = experience;
        this.skillsList = skills;

    }

    public int getJobSeekerID() {
        return userID;
    }

    public List<Application> getApplications() {
        return applications;
    }
    
    public List<Education> getEducation() {
        return educationList;
    }

    public List<Skills> getSkills() {
        return skillsList;
    }

    public List<Experience> getExperience(){
        return experienceList;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addApplication(Application application) {
        applications.add(application);
    }

    public void applyForJob() {
        Scanner scanner = new Scanner(System.in);
        job1 = new SoftwareDeveloperJobs();
        job2 = new SoftwareDeveloperJobs();
        job3 = new SoftwareDeveloperJobs();
        job1.viewAllJob();
        job2.viewAllJob();
        job3.viewAllJob();
        System.out.println("Enter the Job ID you want to apply for:");
        int selectedJobID = scanner.nextInt();
        scanner.nextLine();
        Job selectedJob = null;
        for (Job job : Job.jobList) {
            if (job.getJobID() == selectedJobID) {
                selectedJob = job;
                break;
            }
        }
        if (selectedJob == null) {
            System.out.println("Job with ID " + selectedJobID + " not found.");
            return;
        }
        Application application = Application.createApplication(selectedJob, this);
        notification = notification.createNotification("application", selectedJob.getTitle());
    }

    public void createProfile() {
        System.out.println("\n\n---------------------------------------Users Profile Section---------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String u_name = scanner.nextLine();
        System.out.println("Enter your email: ");
        String u_email = scanner.nextLine();
        System.out.println("Enter your phone number: ");
        String u_phone = scanner.nextLine();
        System.out.println("Enter your location: ");
        String u_location = scanner.nextLine();
        System.out.println("\n\n---------------------------------------Education Section---------------------------------------");
        education = new Education();
        education.addEducation();
        System.out.println("\n\n---------------------------------------Experience Section---------------------------------------");
        experience = new Experience();
        experience.addExperience();
        System.out.println("\n\n---------------------------------------Skills Section---------------------------------------");
        skills = new Skills();
        skills.addSkill();
        JobSeeker job_Seeker = new JobSeeker(u_name, u_email, u_phone, u_location, education.getEducationList(), skills.getSkillLists(), experience.getExperienceList());
        jobSeekersList.add(job_Seeker);
        notification = new Notification("Your Profile Successfully Created");
    }

    public void updateProfile(int job_seeker_id) {
        for (JobSeeker job_seeker : jobSeekersList) {
            if (job_seeker.getJobSeekerID() == job_seeker_id) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter your name: ");
                String u_name = scanner.nextLine();
                System.out.println("Enter your email: ");
                String u_email = scanner.nextLine();
                System.out.println("Enter your phone number: ");
                String u_phone = scanner.nextLine();
                System.out.println("Enter your location: ");
                String u_location = scanner.nextLine();
                job_seeker.setName(u_name);
                job_seeker.setLocation(u_location);
                job_seeker.setPhone(u_phone);
                job_seeker.setEmail(u_email);
                System.out.println("Profile updated successfully for: " + u_name);
                return;
            }
        }
        notification = new Notification("User record not found with ID: " + job_seeker_id);
    }

    public void viewProfile(int job_seeker_id) {
        for (JobSeeker job_Seeker : jobSeekersList) {
            if (job_Seeker.getJobSeekerID() == job_seeker_id) {
                System.out.println("User ID: " + job_Seeker.getJobSeekerID());
                System.out.println("Name: " + job_Seeker.getName());
                System.out.println("Email: " + job_Seeker.getEmail());
                System.out.println("Phone: " + job_Seeker.getPhone());
                System.out.println("Location: " + job_Seeker.getLocation());
                return;
            }
        }
        System.out.println("Jobseeker record not found with ID: " + job_seeker_id);
    }

    public void deleteJobSeeker(int job_seeker_id) {
        for (JobSeeker job_Seeker : jobSeekersList) {
            if (job_Seeker.getJobSeekerID() == job_seeker_id) {
                jobSeekersList.remove(job_Seeker);
                notification = new Notification("Jobseeker deleted: " + job_Seeker.getName());
                return;
            }
        }
        System.out.println("Jobseeker record not found with ID: " + job_seeker_id);
    }

    public void profile_Application() {
        Scanner scanner = new Scanner(System.in);
        String account_choice;
        System.out.println("Do you have an account? (y/n)");
        account_choice = scanner.nextLine();
        if (account_choice.equalsIgnoreCase("y")) {
            System.out.println("Enter your ID: ");
            int job_seeker_id = scanner.nextInt();
            scanner.nextLine();
            for (JobSeeker job_seeker : jobSeekersList) {
                if (job_seeker.getJobSeekerID() == job_seeker_id) {
                    int choice;
                    do {
                        System.out.println("1: Find Job");
                        System.out.println("2: Manage Profile");
                        System.out.println("3: Exit");
                        System.out.print("Choose an option: ");
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                            case 1:
                                System.out.println("\n\n---------------------------------------Job Application---------------------------------------");
                                findJob(job_seeker);
                                break;
                            case 2:
                                manage_profile(job_seeker_id);
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } while (choice != 3);
                    return;
                }
            }
            System.out.println("Profile record not found with ID: " + job_seeker_id);
        } else {
            System.out.println("Create a user profile.");
            createProfile();
            
        }
    }

    private void manage_profile(int job_seeker_id) {
        int choice;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Manage Profile:");
            System.out.println("1: Profile Section");
            System.out.println("2: Education Section");
            System.out.println("3: Experience Section");
            System.out.println("4: Skills Section");
            System.out.println("5: Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    manageProfileSection(scanner, job_seeker_id);
                    break;
                case 2:
                    manageEducationSection(scanner);
                    break;
                case 3:
                    manageExperienceSection(scanner);
                    break;
                case 4:
                    manageSkillsSection(scanner);
                    break;
                case 5:
                    System.out.println("Exiting profile management.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
   
    private void findJob(JobSeeker jobSeeker) {
        int choice, applicationId;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Find Job:");
            System.out.println("1: Apply for a Job");
            System.out.println("2: View all Jobs");
            System.out.println("3: See Recomendations");
            System.out.println("4: View Application");
            System.out.println("5: Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    applyForJob();
                    break;
                case 2:
                    job1 = new SoftwareDeveloperJobs();
                    job2 = new SoftwareDeveloperJobs();
                    job3 = new SoftwareDeveloperJobs();
                    job1.viewAllJob();
                    job2.viewAllJob();
                    job3.viewAllJob();
                    break;
                case 3:
                    matchingengine = new MatchingEngine();
                    matchingengine.matchJobs(jobSeeker);
                    matchingengine.generateRecommendation();
                    break;
                case 4:
                    System.out.print("Enter Your application Id: ");
                    matchingengine = new MatchingEngine();
                    applicationId = scanner.nextInt();
                    scanner.nextLine();
                    application.viewApplicationDetails(applicationId);
                    break;
                case 5:
                    System.out.println("Exiting profile management.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void manageProfileSection(Scanner scanner, int job_seeker_id) {
        int select;
        do {
            System.out.println("Profile Section:");
            System.out.println("1: Update Profile");
            System.out.println("2: View Profile");
            System.out.println("3: Delete Profile");
            System.out.println("4: Exit");
            System.out.print("Choose an option: ");
            select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1:
                    updateProfile(job_seeker_id);
                    break;
                case 2:
                    viewProfile(job_seeker_id);
                    break;
                case 3:
                    deleteJobSeeker(job_seeker_id);
                    break;
                case 4:
                    System.out.println("Exiting profile section.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (select != 4);
    }

    private void manageEducationSection(Scanner scanner) {
        System.out.println("Education Section:");
        int select, education_id;
        do {
            System.out.println("1: Add education. ");
            System.out.println("2: Update education. ");
            System.out.println("3: View education. ");
            System.out.println("4: Delete education. ");
            System.out.println("5: Exit. ");
            System.out.print("Choose an option: ");
            select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1:
                    education.addEducation();
                    break;
                case 2:
                    education.viewAllEducation();
                    System.out.println("Please Enter your EducationID: ");
                    education_id = scanner.nextInt();
                    education.updateEducation(education_id);
                    break;
                case 3:
                    education.viewAllEducation();
                    break;
                case 4:
                    education.viewAllEducation();
                    System.out.println("Please Enter your EducationID: ");
                    education_id = scanner.nextInt();
                    education.deleteEducation(education_id);
                    break;
                case 5:
                    System.out.println("Exiting Education Section.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (select != 5);
    }

    private void manageExperienceSection(Scanner scanner) {
        System.out.println("Experience Section:");
        int select, experience_id;
        do {
            System.out.println("1: Add experience. ");
            System.out.println("2: Update experience. ");
            System.out.println("3: View experience. ");
            System.out.println("4: Delete experience. ");
            System.out.println("5: Exit. ");
            System.out.print("Choose an option: ");
            select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1:
                    experience.addExperience();
                    break;
                case 2:
                    experience.viewAllExperience();
                    System.out.println("Please Enter your ExperienceID: ");
                    experience_id = scanner.nextInt();
                    experience.updateExperience(experience_id);
                    break;
                case 3:
                    experience.viewAllExperience();    
                    break;
                case 4:
                    experience.viewAllExperience();
                    System.out.println("Please Enter your ExperienceID: ");
                    experience_id = scanner.nextInt();
                    experience.deleteExperience(experience_id);
                    break;
                case 5:
                    System.out.println("Exiting Experience Section.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (select != 5);
    }

    private void manageSkillsSection(Scanner scanner) {
        System.out.println("Skills Section:");
        int select, skill_id;
        do {
            System.out.println("1: Add skill. ");
            System.out.println("2: Update skill. ");
            System.out.println("3: View skill. ");
            System.out.println("4: Delete skill. ");
            System.out.println("5: Exit. ");
            System.out.print("Choose an option: ");
            select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1:
                    skills.addSkill();
                    break;
                case 2:
                    skills.viewSkillDetails();
                    System.out.println("Please Enter your skillID: ");
                    skill_id = scanner.nextInt();
                    skills.updateSkill(skill_id);
                    break;
                case 3:
                    skills.viewSkillDetails();
                    break;
                case 4:
                    skills.viewSkillDetails();
                    System.out.println("Please Enter your skillID: ");
                    skill_id = scanner.nextInt();
                    skills.deleteSkill(skill_id);
                    break;
                case 5:
                    System.out.println("Exiting skill Section.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (select != 5);
    }
}
