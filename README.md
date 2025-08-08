## Overview

The **Job Portal** project is a Java-based application that allows job seekers to apply for jobs, employers to post jobs, and the system to manage job applications.
It includes functionality for job creation, application submission, status updates, and notifications — all handled in memory.

## Features

* **Job Management**

  * Create and store job postings.
  * Link job postings to applications.
* **Job Seeker Management**

  * Store job seeker profiles and track their applications.
* **Application Management**

  * Apply for jobs with automatic unique ID generation.
  * View and update application statuses.
  * Predefined statuses: `PENDING`, `APPROVED`, `REJECTED`.
* **Notifications**

  * Alerts for application creation and status changes.

---

## Project Structure

```
Job-Portal/
│
├── build/classes          # Compiled .class files
├── nbproject              # NetBeans project configuration
├── src/javaproject/       # Source code
│   ├── Application.java   # Handles job applications
│   ├── Job.java           # Job posting details
│   ├── JobSeeker.java     # Job seeker details
│   ├── Notification.java  # Notification handling
│   └── ... (other related classes)
├── build.xml              # Apache Ant build script
├── manifest.mf            # Manifest file
└── README.md              # Project documentation
```

---

## Classes and Responsibilities

### **Application**

* Represents a job application.
* Stores application ID, job ID, seeker ID, and status.
* Links jobs and seekers when an application is created.
* Allows viewing and updating application details.
* Maintains a static list of all applications.

### **Job**

* Stores job details such as title, description, and job ID.
* Keeps track of applications for the job.
* Provides methods to add applications.

### **JobSeeker**

* Stores job seeker details and their unique ID.
* Maintains a list of applications submitted by the seeker.

### **Notification**

* Sends notifications when an application is created or updated.
* Can display status updates to the user.

---

## Requirements

* **Java 8** or later
* (Optional) **NetBeans** or any Java IDE for development
* Apache Ant (if building using `build.xml`)

---

## How to Run

1. **Clone the repository**

   ```bash
   git clone https://github.com/yourusername/job-portal.git
   cd job-portal
   ```
2. **Open in NetBeans** (or your preferred Java IDE).
3. **Build and Run** the project using the IDE or via command line:

   ```bash
   ant run
   ```

---

## Example Usage

```java
Job job = new Job("Software Engineer", "Full-time", ...);
JobSeeker seeker = new JobSeeker("Alice", ...);

Application app = Application.createApplication(job, seeker);
app.viewApplicationDetails(app.getApplicationID());
app.updateStatus(app.getApplicationID(), "APPROVED");
```

---

## Notes

* All data is stored in memory; closing the program clears all records.
* Status updates must match one of the allowed statuses.
* Notifications are displayed in the console.
* This project is structured for easy extension — e.g., adding persistence or a user interface.
