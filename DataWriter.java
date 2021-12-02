import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

public class DataWriter extends DataConstants {
    
    /**
     * updates the student.json file with new information by parsing through the arraylist of students and turning them into json objects
     */
    public static void saveStudents() { 
        UserList students = UserList.getUserList();
        ArrayList<Student> s = students.getStudents();
        JSONArray JSONstudents = new JSONArray();

        for(int i = 0; i < s.size(); i++) { 
            JSONstudents.add(getStudentJSON(s.get(i)));
        }

        try(FileWriter file = new FileWriter(STUDENT_FILE_NAME)) { 
            file.write(JSONstudents.toJSONString());
            file.flush();
        } catch(IOException e)  { 
            e.printStackTrace();
        }
    }

    /**
     * creates a json object of a student object with the relevant attributes and objects
     * @param student a student from the application's list of student users to be replicated through a json object
     * @return a json object representing the student
     */
    public static JSONObject getStudentJSON(Student student) { 
        JSONObject studentDetails = new JSONObject();
        Resume resume = student.getResume();
        Education education = resume.getEducation();
        ArrayList<Experience> experiences = resume.getExperiences();

        studentDetails.put(STUDENT_LASTNAME, student.getLastName());
        studentDetails.put(STUDENT_FIRSTNAME, student.getFirstName());
        studentDetails.put(STUDENT_PASSWORD, student.getPassword());
        studentDetails.put(STUDENT_PHONE, student.getPhoneNumber());
        studentDetails.put(STUDENT_ID, student.getID().toString());
        studentDetails.put(STUDENT_MAJOR, education.getMajor());
        studentDetails.put(STUDENT_MINOR, education.getMinor());
        studentDetails.put(STUDENT_CLASS_STANDING, student.getClassStanding());
        studentDetails.put(STUDENT_EMAIL, student.getEmail());
        studentDetails.put(STUDENT_APPLIED_LISTINGS, student.getAppliedListingsJSON());

        JSONArray reviewsJSON = new JSONArray();
        ArrayList<Review> reviews = student.getReviews();
        for (int i = 0; i < student.getReviews().size(); i++) {
            JSONObject reviewJO = new JSONObject();
            Review review = reviews.get(i);
            reviewJO.put(REVIEW_AUTHOR, review.getAuthor());
            reviewJO.put(REVIEW_DATE, review.getDate());
            reviewJO.put(REVIEW_RATING, review.getRating());
            reviewJO.put(REVIEW_COMMENT, review.getComment());
            reviewsJSON.add(reviewJO);
        }

        studentDetails.put(STUDENT_REVIEWS, (JSONArray)reviewsJSON);


        JSONObject resumeDetails = new JSONObject();
        resumeDetails.put(RESUME_NAME, resume.getName());
        resumeDetails.put(RESUME_HOMETOWN, resume.getHometown());
        
        JSONObject educationDetails = new JSONObject();
        educationDetails.put(RESUME_SCHOOL, education.getSchool());
        educationDetails.put(RESUME_MAJOR, education.getMajor());
        educationDetails.put(RESUME_MINOR, education.getMinor());
        educationDetails.put(RESUME_GPA, education.getGpa());
        educationDetails.put(RESUME_GRADUATION_YEAR, education.getGraduationYear());
        educationDetails.put(RESUME_CLASS_STANDING, education.getClassStanding());
        educationDetails.put(RESUME_RELEVANT_COURSEWORK, education.getCoursework());
        resumeDetails.put(RESUME_EDUCATION, educationDetails);

        resumeDetails.put(RESUME_AWARDS, resume.getAwards());
        resumeDetails.put(RESUME_SCHOLARSHIPS, resume.getScholarships());

        JSONArray experienceJSON = new JSONArray();
        JSONObject experienceDetails = new JSONObject();
        //if(experienceJSON != null) { 
            for(int i = 0; i < experiences.size(); i++) { 
                experienceDetails = new JSONObject();
                Experience experience = experiences.get(i);
                experienceDetails.put(RESUME_TITLE, experience.getTitle());
                experienceDetails.put(RESUME_EMPLOYER, experience.getEmployer());
                experienceDetails.put(RESUME_LOCATION, experience.getLocation());
                experienceDetails.put(RESUME_START_DATE, experience.getStartDate());
                experienceDetails.put(RESUME_END_DATE, experience.getEndDate());
                experienceDetails.put(RESUME_RESPONSIBILITIES, experience.getResponsibilities());
                experienceJSON.add(experienceDetails);
            //}
        }
        resumeDetails.put(RESUME_EXPERIENCES, (JSONArray)experienceJSON);

        studentDetails.put(STUDENT_RESUME, resumeDetails);
        return studentDetails;
    }

    /**
     * updates the employer.json file with new information by parsing through the arraylist of employers and turning them into json objects
     */
    public static void saveEmployers() { 
        UserList employers = UserList.getUserList();
        ArrayList<Employer> s = employers.getEmployers();
        JSONArray JSONemployer = new JSONArray();

        for(int i = 0; i < s.size(); i++) { 
            JSONemployer.add(getEmployerJSON(s.get(i)));
        }

        try(FileWriter file = new FileWriter(EMPLOYER_FILE_NAME)) { 
            file.write(JSONemployer.toJSONString());
            file.flush();
        } catch(IOException e)  { 
            e.printStackTrace();
        }
    }

    /**
     * creates a json object of an employer object with the relevant attributes and objects
     * @param employer an employer from the application's list of employer users to be replicated through a json object
     * @return a json object representing the employer
     */
    public static JSONObject getEmployerJSON(Employer employer) { 
        JSONObject employerDetails = new JSONObject();
        employerDetails.put(EMPLOYER_LASTNAME, employer.getLastName());
        employerDetails.put(EMPLOYER_FIRSTNAME, employer.getFirstName());
        employerDetails.put(EMPLOYER_ID, employer.getID().toString());
        employerDetails.put(EMPLOYER_COMPANY, employer.getCompany());
        employerDetails.put(EMPLOYER_COMPANYMISSION, employer.getMission());
        employerDetails.put(EMPLOYER_PASSWORD, employer.getPassword());
        employerDetails.put(EMPLOYER_EMAIL, employer.getEmail());
        employerDetails.put(EMPLOYER_LISTINGS, employer.getListingsJSON());
        return employerDetails;
    }

    /**
     * updates the administrator.json file with new information by parsing through the arraylist of administrators and turning them into json objects
     */
    public static void saveAdministrators() { 
        UserList admins = UserList.getUserList();
        ArrayList<Administrator> s = admins.getAdmin();
        JSONArray JSONAdmin = new JSONArray();

        for(int i = 0; i < s.size(); i++) { 
            JSONAdmin.add(getAdminJSON(s.get(i)));
        }

        try(FileWriter file = new FileWriter(ADMIN_FILE_NAME)) { 
            file.write(JSONAdmin.toJSONString());
            file.flush();
        } catch(IOException e)  { 
            e.printStackTrace();
        }
    }

    /**
     * creates a json object of an administrator object with the relevant attributes and objects
     * @param admin an administrator from the application's list of admin users to be replicated through a json object
     * @return a json object representing the administrator
     */
    public static JSONObject getAdminJSON(Administrator admin) {
         JSONObject adminDetails = new JSONObject();
         adminDetails.put(ADMIN_LASTNAME, admin.getLastName());
         adminDetails.put(ADMIN_FIRSTNAME, admin.getFirstName());
         adminDetails.put(ADMIN_ID, admin.getID().toString());
         adminDetails.put(ADMIN_PASSWORD, admin.getPassword());
         adminDetails.put(ADMIN_EMAIL, admin.getEmail());
         adminDetails.put(ADMIN_JOBTITLE, admin.getJobTitle());
         return adminDetails;
    }

    /**
     * updates the internshiplisting.json file with new information by parsing through the arraylist of posted internships and turning them into json objects
     */
    public static void saveInternshipList() { 
        InternshipList internships = InternshipList.getInstance();
        ArrayList<InternshipListing> s = internships.getInternshipList();
        JSONArray JSONInternships = new JSONArray();

        for(int i = 0; i < s.size(); i++) { 
            JSONInternships.add(getInternJsonObject(s.get(i)));
        }

        try(FileWriter file = new FileWriter(LISTING_FILE_NAME)) { 
            file.write(JSONInternships.toString());
            file.flush();
        } catch(IOException e) { 
            e.printStackTrace();
        }
    }

    /**
     * creates a json object of an internshiplisting object with the relevant attributes and objects
     * @param listing an internship listing from the application's list of internships to be replicated through a json object
     * @return a json object representing the listing
     */
    public static JSONObject getInternJsonObject(InternshipListing listing) {
        JSONObject listingDetails = new JSONObject();
        listingDetails.put(LISTING_ID, listing.getID().toString());
        listingDetails.put(LISTING_TITLE, listing.getTitle());
        listingDetails.put(LISTING_COMPANY, listing.getCompany());
        listingDetails.put(LISTING_STATUS, listing.getStatus());
        listingDetails.put(LISTING_LOCATION, listing.getLocation());
        listingDetails.put(LISTING_DURATION, listing.getDuration());
        listingDetails.put(LISTING_LOWER_PAY, listing.getLowerPay());
        listingDetails.put(LISTING_HIGHER_PAY, listing.getHigherPay());
        listingDetails.put(LISTING_EMPLOYER_ID, listing.getEmployerID().toString());
        //listingDetails.put(LISTING_APPLICATION_CLOSE_DATE, listing.getApplicationCloseDate());
        listingDetails.put(LISTING_DUTIES, listing.getDuties());
        listingDetails.put(LISTING_REQUIREMENTS, listing.getRequirements());
        listingDetails.put(LISTING_APPLICANTS, listing.getApplicantsString());
        if (listing.getSelectedCandidateUsingUUID() != null)
            listingDetails.put(LISTING_STUDENT_ID, listing.getSelectedCandidateString());
        return listingDetails;
   }
}
